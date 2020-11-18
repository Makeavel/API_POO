package main.br

import br.core.Aes

fun main(){
//     //esse é um método que não usa classe
//        val reader = Scanner(System.`in`)
//
//         print("Digite o texto a ser criptografado: ")
//         var entrada = readLine()!!
//
//         RSA(entrada)
//     //************************************************************
    var Aes = Aes()
    var teste: String? = Aes.encrypt("ygor","ola")

    println(teste)
    println("**")

    var teste2 : String? = teste?.let{Aes.decrypt(teste, "ola")}
    println(teste2)
    //***************************************************************



}
