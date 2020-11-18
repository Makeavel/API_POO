package main

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.br.criptografias.RSACLASS
import main.br.criptografias.CaesarCipher
import main.login.Pessoa
import main.login.login
import java.math.BigInteger
import br.criptografias.Aes
import main.dinamica.*


val rsa:RSACLASS = RSACLASS()
val Simetrica = CaesarCipher()
val contas:login = login()
val banco : banco = banco()
var criptografia = Aes()

data class respostacriptoRSA(val chavePrivada: BigInteger,val chaveN : BigInteger,val chavePublica : BigInteger)

data class respostadesencriptoRSA(val id: String, val senha : String, val chavePublica : BigInteger,val chaveN : BigInteger,val chavePrivada: BigInteger)

data class respostadesencriptoAES(val id : String,val senha: String, var chave: String, val mensagem : String)

data class respostacriptoAES(val id : String,var chave: String, val mensagem : String)


fun main(){


    embeddedServer( Netty, 3333 ){

        routing {

            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            get("/info") {
                call.respondText("API que recebe um texto, encripta e desencripta")
            }
            post("/entrada"){
                val palavra = call.receive<String>()
                call.respond(palavra)
                rsa.RSA(palavra)

            }

            get("/encriptar"){
                    call.respond(rsa.msg_encrypt)

            }
            get("/desencriptar"){
                call.respond(rsa.msg_desencrypt)

            }
            get("/usu"){
                call.respond(contas.contas)
            }

            post("/loga"){
                val novaConta : Pessoa = call.receive<Pessoa>()
                var verifica = contas.contas.firstOrNull{it.usuario == novaConta.usuario} // retorna o primeiro usuario que atende a condição
                    if(verifica == null){ //se n existir, ele cria
                        val UsuarioCadastro : Pessoa = Pessoa(usuario = novaConta.usuario, senha = novaConta.senha)
                        contas.contas.add(UsuarioCadastro)
                        call.respond(HttpStatusCode.Created,UsuarioCadastro)
                    }else{// se existir, retorna erro 409
                        call.respond(HttpStatusCode.Conflict,"Nome de usuário já cadastrado")
                    }

            }

            post("/usuario/cripto/RSA"){
                var teste : modeloAssimetrica = call.receive<modeloAssimetrica>()
                var  modelo : modeloAssimetrica = modeloAssimetrica(id = teste.id,senha = teste.senha,mensagem = teste.mensagem)

                modelo.criptografar()
                banco.assimetricos.add(modelo)
                banco.mensagenscriptografadasRSA.add(guardarRSA(modelo.id,modelo.senha,modelo.chavePublica,modelo.chaveN,modelo.chavePrivada,modelo.mensagemCriptografada))

                call.respond(HttpStatusCode.OK,respostacriptoRSA(modelo.chavePrivada,modelo.chaveN,modelo.chavePublica))

            }

            get("/mensagensCriptoRSA"){
                call.respond(banco.mensagenscriptografadasRSA)
            }

            post("/usuario/decripto/RSA"){
                val requisicao : respostadesencriptoRSA = call.receive<respostadesencriptoRSA>()
                var batata : modeloAssimetrica? = banco.assimetricos.firstOrNull{(requisicao.id == it.id &&
                        requisicao.chaveN == it.chaveN &&
                        requisicao.chavePrivada == it.chavePrivada &&
                        requisicao.chavePublica == it.chavePublica&&
                        requisicao.senha == it.senha)}


                if(batata != null){
                        val msgdesencrip : String = batata.descriptografar()
                        call.respond(msgdesencrip)
                    }else{
                        call.respond(HttpStatusCode.NotFound,"verifique as credenciais")
                    }
            }

            post("/usuario/cripto/AES"){
                val requisicao : modeloSimetrica = call.receive<modeloSimetrica>()

                var criptografia = Aes()

                var mensagem : String? = criptografia.encrypt(requisicao.mensagem,requisicao.chave)

                if (mensagem != null) {
                    banco.simetricos.add(requisicao)
                    banco.mensagenscriptografadasAES.add(guardarAES(requisicao.id,requisicao.chave,mensagem))
                    call.respond(HttpStatusCode.OK, "Mensagem Criptografa")

                }

            }

            get("/mensagensCriptoAES"){
                call.respond(banco.mensagenscriptografadasAES)
            }

            post("/usuario/decripto/AES"){
                val requisicao : respostadesencriptoAES = call.receive<respostadesencriptoAES>()


                println(requisicao.chave)
                println(requisicao.id)
                println(requisicao.mensagem)


                var cenoura : modeloSimetrica? = banco.simetricos.firstOrNull{(requisicao.id == it.id &&
                        requisicao.chave == it.chave /*&&
                        requisicao.senha == it.senha*/)}


                if(cenoura != null){
                    val msgdesencripAES : String? = criptografia.decrypt(requisicao.mensagem,requisicao.chave)
                    if (msgdesencripAES != null) {
                        call.respond(msgdesencripAES)
                    }
                }else{
                    call.respond(HttpStatusCode.NotFound,"verifique as credenciais")
                }
            }



            
        }//fim_routing

    }.start(wait = true)

}