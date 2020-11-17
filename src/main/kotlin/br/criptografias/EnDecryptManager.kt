package main.br.criptografias

interface EnDecryptManager {
    fun encrypt(data:String, usableKey:String)
    fun decrypt(dataCrypt:String, usableKey:String)
    fun exeTime()
}