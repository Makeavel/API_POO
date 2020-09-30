import java.math.BigInteger

fun main() {
    var p: BigInteger = 17.toBigInteger()
    var q: BigInteger = 41.toBigInteger()
    var n: BigInteger
    var t: BigInteger
    var e: BigInteger
    var d: BigInteger = 0.toBigInteger()
    var inve : BigInteger = 0.toBigInteger()

    val msg_asc: MutableList<Int> = mutableListOf()
    val msg_encrypt: MutableList<BigInteger> = mutableListOf()
    val msg_desencrypt: MutableList<BigInteger> = mutableListOf()

    n = p*q
    t = totiente(p,q)
    e = mdc(t)
    //e = 2

    var y: String = "ygor borges"
    var res:String = ""

    for (i in y){
        res = i.toInt().toString()
        val res = res.toIntOrNull()
        if (res != null) {
            msg_asc.add(res)
        }
    }
    for(i in msg_asc){
        println(i)
    }
    println("**********")
    inve = inverso(e,t )

    for (i  in msg_asc){
        msg_encrypt.add((i.toBigInteger().pow(e.toInt())).rem(n) )
    }

//    println(e)
//    println(t)
//    print(inve)

    for (i  in msg_encrypt){
        var j: BigInteger = i
        msg_desencrypt.add((j.pow(inve.toInt())).rem(n))
    }

    for(i in msg_desencrypt){
        println(i)
    }



}
fun rsa(p : Int, q : Int, n : Int){
    // duas primeiras linhas recebem o valor de p e q, será utilizado um random
    var _n : Int = n
    _n = p*q

}

fun totiente(p : BigInteger, q : BigInteger): BigInteger {

    return (p-1.toBigInteger())*(q-1.toBigInteger())
}

fun mdc(t : BigInteger ): BigInteger {
    val arr: MutableList<BigInteger> = mutableListOf()

    for(_n2 in 1 until t.toInt()){
        var n1:BigInteger = t
        var n2 : BigInteger = _n2.toBigInteger()

        while (n1 != n2) {
            if (n1 > n2)
                n1 -= n2
            else
                n2 -= n1
        }
        if(n1.toInt() == 1) {
            arr.add(_n2.toBigInteger())
        }
    }

    return arr.random()

}

fun inverso(e : BigInteger,m : BigInteger): BigInteger{
    var d : BigInteger = 0.toBigInteger()
    d = e.modInverse(m)
    return d
}

