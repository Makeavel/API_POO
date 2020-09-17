import br.generico.banco.Conta.Conta
import br.generico.banco.Cliente.Cliente
import br.generico.banco.movimentacao.deposito
import br.generico.banco.movimentacao.saque
import br.generico.banco.movimentacao.transferencia

fun main(){

    val Cliente1 = Cliente()
    val Cliente2 = Cliente()

    val Conta1 = Conta()
    val Conta2 = Conta()


    Cliente1.nome = "Makeavel"
    Cliente1.Cpf = "123.456.789-09"
    Cliente1.contato = "(+55)98888-0000"
    Conta1.NumConta = "6565-6"
    Conta1.saldo = 7860.00
    Conta1.agencia = "1005-0"


    Cliente2.nome = "Joaozinho"
    Cliente2.Cpf = "777.777.777-77"
    Cliente2.contato = "(+322)88621553"
    Conta2.saldo = 0.0
    Conta2.agencia = "2090-6"
    Conta2.NumConta = "8982-4"

    // ---------------------------------------------

    println(Conta1.saldo)
    deposito(3050.00 , Conta1)

    println(Conta1.saldo)

    saque(12090.00 , Conta1)
    println(Conta1.saldo)

    println("Conta2: ${Conta2.saldo}")
    transferencia(88910.00 , Conta1 , Conta2)

    println(Conta2.saldo)

}

