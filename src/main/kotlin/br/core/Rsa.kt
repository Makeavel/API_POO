package main.br.criptografias

import java.math.BigInteger
import java.security.SecureRandom


data class rsaretorno (val chavepublicaE : BigInteger,
                       val chaveN: BigInteger,
                       val mensagem : MutableList<BigInteger>,
                       val chaveprivada : BigInteger)


open class RSACLASS {
    // declaração das listas
    val msg_asc: MutableList<BigInteger> = mutableListOf() // string em forma de ASCII
    val msg_encrypt: MutableList<BigInteger> = mutableListOf() // string encriptada
    val msg_desencrypt: MutableList<BigInteger> = mutableListOf() // string desencriptada
    //val numeros: IntArray = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)


    fun RSA(entrada : String) : rsaretorno {
        //criação das variáveis
        val r = SecureRandom()
        val p = BigInteger(16/ 2, 100, r)
        val q = BigInteger(16 / 2, 100, r)
        var n: BigInteger = 0.toBigInteger() //valor da multiplicação dos primos (tamanho do conjunto)
        var tot: BigInteger = 0.toBigInteger()// função totiente
        var e: BigInteger = 0.toBigInteger()// parte da chave publica
        var inve: BigInteger = 0.toBigInteger() //parte da chava privada



        n = p * q //multiplicação p*q
        tot = totiente(p, q) // calculou a função totiente
        e = mdc(tot) // calculou e
        inve = inverso(e,tot )

        transforma(entrada,msg_asc)

        encripta(msg_asc,msg_encrypt,n,e)

        desecripta(msg_desencrypt,msg_encrypt,n,inve)

        return rsaretorno(e,n,msg_encrypt,inve)

    }
    fun desecripta(msg_desencrypt : MutableList<BigInteger>,msg_encrypt:  MutableList<BigInteger>,n : BigInteger,inve : BigInteger): MutableList<BigInteger> {
        for (i in msg_encrypt) {
            msg_desencrypt.add((i.pow(inve.toInt())).rem(n))
        }
        return msg_desencrypt
    }
    fun juntar(msg : MutableList<BigInteger>): String {
        var palavra : String = ""
        for (i in msg) {
            palavra += "${i.toInt().toChar()}"
        }
        return palavra
    }
    fun encripta(msg_asc : MutableList<BigInteger>,msg_encrypt:  MutableList<BigInteger>,n : BigInteger,e : BigInteger){
        for (i in msg_asc) {
            msg_encrypt.add((i.pow(e.toInt())).rem(n))
        }
    }

//    fun mostrar(msg : MutableList<BigInteger>){
//        for (i in msg) {
//            print("$i ")
//        }
//        println()
//    }

    open fun aparecer(): MutableList<BigInteger> {

        return msg_encrypt
    }

    fun transforma(entrada: String, msg_asc : MutableList<BigInteger>){
        var aux : String
        for (i in entrada) {
            aux = i.toInt().toString()
            val res = aux.toIntOrNull()
            if (res != null) {
                msg_asc.add(res.toBigInteger())
            }
        }
    }

    fun totiente(p: BigInteger, q: BigInteger): BigInteger {

        return (p - 1.toBigInteger()) * (q - 1.toBigInteger())
    }

    //Escolha um inteiro  "e"  , 1 < "e" < phi(n) ,  "e" e phi(n) sejam primos entre si.
    fun mdc(t: BigInteger): BigInteger {
        val arr: MutableList<BigInteger> = mutableListOf()

        for (_n2 in 1 until t.toInt()) {
            var n1: BigInteger = t
            var n2: BigInteger = _n2.toBigInteger()

            while (n1 != n2) {
                if (n1 > n2)
                    n1 -= n2
                else
                    n2 -= n1
            }
            if (n1.toInt() == 1) {
                arr.add(_n2.toBigInteger())
            }
        }
        return arr.random()

    }

    //calcula o inverso modular
    fun inverso(e: BigInteger, m: BigInteger): BigInteger {
        return e.modInverse(m)
    }
}


