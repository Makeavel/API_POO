package main.dinamica

import java.math.BigInteger

data class guardar(val id : String, val senha : String, var chavePublica : BigInteger,
                   var chaveN : BigInteger,
                   var chavePrivada : BigInteger,
                   val mensagem : MutableList<BigInteger>
                   )

class banco {

    var assimetricos : MutableList<modeloAssimetrica> = mutableListOf<modeloAssimetrica>()
    var mensagenscriptografadas : MutableList<guardar> = mutableListOf<guardar>()

}