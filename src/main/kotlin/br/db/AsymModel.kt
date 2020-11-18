package main.br.dinamica

import br.core.MoldCryptos
import main.br.criptografias.RSACLASS
import main.br.criptografias.rsaretorno
import java.math.BigInteger


class AsymModel(
        var id : String,
        var senha : String,
        var chavePublica : BigInteger = 0.toBigInteger(),
        var chaveN : BigInteger= 0.toBigInteger(),
        var chavePrivada : BigInteger = 0.toBigInteger(),
        var mensagem : String,
        var mensagemCriptografada : MutableList<BigInteger> = mutableListOf<BigInteger>()
) : MoldCryptos {

    var teste : RSACLASS = RSACLASS()
    var ola : rsaretorno = teste.RSA(this.mensagem)
    var mensageDesencriptografada : MutableList<BigInteger> = mutableListOf<BigInteger>()

    override fun criptografar() {

        this.chavePublica = ola.chavepublicaE
        this.chavePrivada = ola.chaveprivada
        this.mensagemCriptografada = ola.mensagem
        this.chaveN = ola.chaveN
    }

    override fun descriptografar() : String {

        return  teste.juntar(teste.desecripta(mensageDesencriptografada,mensagemCriptografada,chaveN,chavePrivada))

    }
}