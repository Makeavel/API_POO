package br.generico.banco.Banco.Movimentacao
import br.generico.banco.Banco.Conta.Conta


fun deposito(saldo: Double, conta: Conta) {

    conta.saldo = conta.saldo?.plus(saldo)
}

fun saque(saldo: Double, conta: Conta) {

    when {
        saldo < conta.saldo!! -> {
            conta.saldo = conta.saldo?.minus(saldo)
        }
        else -> {
            println("Saldo Insuficiente")

        }
    }
}

fun transferencia(saldo: Double, contaSaida: Conta, contaEntrada: Conta) {

    when {
        saldo < contaSaida.saldo!! -> {
            saque(saldo, contaSaida)

            deposito(saldo, contaEntrada)
        }
        else -> {
            println("Saldo Insuficiente")
        }
    }
}
