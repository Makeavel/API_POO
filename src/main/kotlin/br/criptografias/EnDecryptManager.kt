package main.br.criptografias

interface key{

}

interface msg{

}

interface EnDecryptManager {
    open fun encrypt()
    open fun decrypt()
    open fun exeTime()
}