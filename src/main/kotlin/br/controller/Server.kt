package br.controller

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
import main.br.login.User
import main.br.login.Login
import java.math.BigInteger
import br.core.Aes
import main.br.dinamica.*


val rsa:RSACLASS = RSACLASS()
val Simetrica = CaesarCipher()
val contas: Login = Login()
val banco : banco = banco()
var criptografia = Aes()

data class respostacriptoRSA(val chavePrivada: BigInteger,val chavePublica : BigInteger)

data class respostadesencriptoRSA(val id: String, val senha : String, val chavePublica : BigInteger,val chaveN : BigInteger,val chavePrivada: BigInteger)

data class respostadesencriptoAES(val id : String,val senha: String, var chave: String, val mensagem : String)

data class respostacriptoAES(val id : String,var chave: String, val senha : String, val mensagem : String)


fun main(){


    embeddedServer( Netty, 3333 ){

        routing {

            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }

            get("/usu"){
                call.respond(contas.contas)
            }

            post("/loga"){
                val novaConta : User = call.receive<User>()
                var verifica = contas.contas.firstOrNull{it.usuario == novaConta.usuario} // retorna o primeiro usuario que atende a condição
                    if(verifica == null){ //se n existir, ele cria
                        val UsuarioCadastro : User = User(usuario = novaConta.usuario, senha = novaConta.senha)
                        contas.contas.add(UsuarioCadastro)
                        call.respond(HttpStatusCode.Created,UsuarioCadastro)
                    }else{// se existir, retorna erro 409
                        call.respond(HttpStatusCode.Conflict,"Nome de usuário já cadastrado")
                    }

            }

            post("/usuario/cripto/RSA"){
                var teste : AsymModel = call.receive<AsymModel>()
                var  modelo : AsymModel = AsymModel(id = teste.id,senha = teste.senha,mensagem = teste.mensagem)

                var verifica = contas.contas.firstOrNull{it.id == modelo.id && it.senha == modelo.senha}

                    if(verifica != null){
                        modelo.criptografar()
                        banco.assimetricos.add(modelo)
                        banco.mensagenscriptografadasRSA.add(guardarRSA(modelo.id,modelo.senha,modelo.chavePublica,modelo.chaveN,modelo.chavePrivada,modelo.mensagemCriptografada))
                        call.respond(HttpStatusCode.OK, respostacriptoRSA(modelo.chavePrivada,modelo.chavePublica))
                        //call.respond(HttpStatusCode.OK,"Conta existente, mensagem criptografada com o RSA")
                    }else{
                        call.respond(HttpStatusCode.NotFound,"Conta inexistente ou dados inválidos")
                    }

            }

            get("/mensagensCriptoRSA"){
                call.respond(banco.mensagenscriptografadasRSA)
            }

            post("/usuario/decripto/RSA"){
                val requisicao : respostadesencriptoRSA = call.receive<respostadesencriptoRSA>()
                var batata : AsymModel? = banco.assimetricos.firstOrNull{(requisicao.id == it.id &&
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
                val requisicao : SymModel = call.receive<SymModel>()

                var criptografia = Aes()

                var verifica = contas.contas.firstOrNull{it.id == requisicao.id && it.senha == requisicao.senha}

                if(verifica != null){
                    var mensagem : String? = criptografia.encrypt(requisicao.mensagem,requisicao.chave)

                    if (mensagem != null) {
                        banco.simetricos.add(requisicao)
                        banco.mensagenscriptografadasAES.add(guardarAES(requisicao.id,requisicao.chave,mensagem))
                        call.respond(HttpStatusCode.OK, "Mensagem Criptografa")

                    }
                }else{
                    call.respond(HttpStatusCode.NotFound,"Conta inexistente ou dados inválidos")
                }

            }

            get("/mensagensCriptoAES"){
                call.respond(banco.mensagenscriptografadasAES)
            }

            post("/usuario/decripto/AES"){
                val requisicao : respostadesencriptoAES = call.receive<respostadesencriptoAES>()


                var cenoura : SymModel? = banco.simetricos.firstOrNull{(requisicao.id == it.id &&
                        requisicao.chave == it.chave &&
                        requisicao.senha == it.senha)}


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