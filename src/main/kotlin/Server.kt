package main

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(){


    embeddedServer( Netty, 3333 ){

        routing {

            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }

            
        }//fim_routing

    }.start(wait = true)

}