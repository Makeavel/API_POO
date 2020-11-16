package main.dinamica

import br.criptografias.Aes


open class modeloSimetrica(
        var id : String,
        var senha : String,
        var chave : String = "",
        var mensagem : String
) : cripto {

    var teste : Aes = Aes()


    override fun criptografar() {

    }

    override fun descriptografar() : String {
        TODO("Not yet implemented")
    }


}