package main

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.br.criptografias.RSACLASS
import main.br.criptografias.Simetrica

val rsa:RSACLASS = RSACLASS()
val Simetrica = Simetrica()




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

            post("/criptografia") {
                Simetrica.crypt("makeavel", "12345678")
            }

            get("/oi") {
                Simetrica.crypt("makeavel", "12345678")
            }


            
        }//fim_routing

    }.start(wait = true)

}