package main.dinamica

import java.math.BigInteger

data class guardarRSA(val id : String, val senha : String, var chavePublica : BigInteger,
                   var chaveN : BigInteger,
                   var chavePrivada : BigInteger,
                   val mensagem : MutableList<BigInteger>
                   )

data class guardarAES(val id : String,
                      var chave: String,
                      val mensagem : String)

class banco {

    var assimetricos : MutableList<modeloAssimetrica> = mutableListOf<modeloAssimetrica>()
    var mensagenscriptografadasRSA : MutableList<guardarRSA> = mutableListOf<guardarRSA>()

    var simetricos : MutableList<modeloSimetrica> = mutableListOf<modeloSimetrica>()
    var mensagenscriptografadasAES : MutableList<guardarAES> = mutableListOf<guardarAES>()


}