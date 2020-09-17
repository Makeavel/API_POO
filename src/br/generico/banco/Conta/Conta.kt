package br.generico.banco.Conta
import br.generico.banco.Cliente.Cliente

open class Conta {
    var NumConta: String? = null
    var nomeCliente: Cliente? = null// Não inicia null rever
    var saldo: Double? = null
    var agencia : String? = null
    //val tipoConta: String? = null
}

