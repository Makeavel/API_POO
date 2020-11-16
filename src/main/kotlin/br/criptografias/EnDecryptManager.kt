package main.br.criptografias

interface EnDecryptManager {
    open fun encrypt(data:String, usableKey:String)
    open fun decrypt(dataCrypt:String, usableKey:String)
    open fun exeTime()
}