package br.core

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays.copyOf
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

open class Aes  {
    private lateinit var key: ByteArray
    private lateinit var scsMedial: SecretKeySpec

    //Método interno que torna a chave escolhida utilizável e seta a chave intermediária para criptografia.
    private fun setKey(initialKey: String) {
        val hashingAlgorithmInstance: MessageDigest?

        try
        {
            key = initialKey.toByteArray(charset("UTF-8"))
            hashingAlgorithmInstance = MessageDigest.getInstance("SHA-1")
            key = hashingAlgorithmInstance.digest(key)
            key = copyOf(key, 16)
            scsMedial = SecretKeySpec(key, "AES")

        } catch (e:NoSuchAlgorithmException) {
            e.printStackTrace()

        } catch (e:UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    fun encrypt(data: String, usableKey: String): String? {
        try {
            setKey(usableKey)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, scsMedial)
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.toByteArray(charset("UTF-8"))))

        } catch (e:Exception) {
            println("Erro na criptografia: " + e.toString())

        }

        return null
    }

    fun decrypt(dataCrypt: String, usableKey: String): String? {

        try {
            setKey(usableKey)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, scsMedial)
            return String(cipher.doFinal(Base64.getDecoder().decode(dataCrypt)))

        } catch (e:Exception) {
            println("Erro na descriptografia: " + e.toString())

        }

        return null
    }


}