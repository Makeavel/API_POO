package br.generico.banco

import br.generico.banco.Banco.Banco
import br.generico.banco.Banco.Movimentacao.deposito
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val banco = Banco()

fun main(){


    embeddedServer( Netty, 3333 ){

        routing {

            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            // Adicionar os Metodos aqui

            get(path = "/Contas"){
                call.respondText("<h1>Show<h1>", ContentType.Text.Html)
            }

            /*
            val get = get("/contas") {
                call.respond(banco.criarConta)
            }

            post("/contas") {
                val novaConta = call.receive<Conta>()
                banco.Contas.add(novaConta)
            }


            post(path =  "/deposito"){
                val request = call.receive<deposito>()
                call.respond("Depósito efetuado com sucesso!")
            }

            post(path =  "/saque"){
                val request = call.receive<saque>()
                call.respond("Saque efetuado com sucesso!")
            }

            post(path =  "/transferencia"){
                val request = call.receive<transferencia>()
                call.respond("Transferencia efetuada com sucesso!")
            }
            */



        }//fim_routing

    }.start(wait = true)

}