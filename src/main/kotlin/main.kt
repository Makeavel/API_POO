package main
import main.br.criptografias.RSA
import java.util.Scanner

fun main(){
    // esse é um método que não usa classe
    val reader = Scanner(System.`in`)

    print("Digite o texto a ser criptografado: ")
    var entrada = readLine()!!

    RSA(entrada)



}
