package br.generico.banco.Banco.Conta
import br.generico.banco.Banco.Cliente.Cliente

open class Conta {
    var NumConta: String? = null
    var nomeCliente: Cliente? = null// Não inicia null rever
    var saldo: Double? = null
    var agencia : String? = null

}

