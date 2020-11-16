package main.dinamica
import main.br.criptografias.RSACLASS
import main.br.criptografias.rsaretorno
import main.rsa

fun main(){
    var teste : RSACLASS = RSACLASS()
    var ola : rsaretorno = teste.RSA("oi")


    println(ola.chaveprivada)
    println(ola.chavepublicaE)
    println(ola.mensagem)

    //teste.RSA("oi")



}