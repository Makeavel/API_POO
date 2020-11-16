package main.dinamica


open class modeloSimetrica(
        var id : String,
        var senha : String,
        var chave : String = "",
        var mensagem : String
) : cripto {

    //var teste : AES = AES()


    override fun criptografar() {

    }

    override fun descriptografar() : String {
        TODO("Not yet implemented")
    }


}