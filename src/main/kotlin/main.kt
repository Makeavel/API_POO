package main
import main.br.criptografias.testeRSA
import java.math.BigInteger
import java.util.Scanner

fun main(){

    val reader = Scanner(System.`in`)

    print("Digite o texto a ser criptografado: ")
    var entrada = readLine()!!

    testeRSA(entrada)


}
