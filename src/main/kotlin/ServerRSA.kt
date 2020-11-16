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
import main.br.criptografias.Simetrica
import main.dinamica.banco
import main.dinamica.guardar
import main.dinamica.modeloAssimetrica
import main.dinamica.modeloSimetrica
import main.login.Pessoa
import main.login.login
import java.math.BigInteger

val rsa:RSACLASS = RSACLASS()
val Simetrica = Simetrica()
val contas:login = login()
val banco : banco = banco()

data class respostacripto(val chavePrivada: BigInteger,val chaveN : BigInteger,val chavePublica : BigInteger)

data class respostadesencripto(val id: String, val senha : String, val chavePublica : BigInteger,val chaveN : BigInteger,val chavePrivada: BigInteger)


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
                banco.mensagenscriptografadas.add(guardar(modelo.id,modelo.senha,modelo.chavePublica,modelo.chaveN,modelo.chavePrivada,modelo.mensagemCriptografada))

                call.respond(HttpStatusCode.OK,respostacripto(modelo.chavePrivada,modelo.chaveN,modelo.chavePublica))

            }

            get("/chaves"){
                call.respond(banco.mensagenscriptografadas)
            }

            post("/usuario/decripto/RSA"){
                val requisicao : respostadesencripto = call.receive<respostadesencripto>()
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

//            post("/usuario/cripto/AES"){
//               val msg :modeloSimetrica = call.receive<modeloSimetrica>()
//                call.respond(msg)
//            }

            post("/criptografia") {
                Simetrica.crypt("makeavel", "12345678")
            }

            get("/oi") {
                Simetrica.crypt("makeavel", "12345678")
            }


            
        }//fim_routing

    }.start(wait = true)

}