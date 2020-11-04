package testeConvKotlin

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays.copyOf
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

open class AES {
    private lateinit var secretKey:SecretKeySpec
    private lateinit var key:ByteArray

    fun defineKey(myKey:String) {
        val sha: MessageDigest?
        try
        {
            key = myKey.toByteArray(charset("UTF-8"))
            sha = MessageDigest.getInstance("SHA-1")
            key = sha.digest(key)
            key = copyOf(key, 16)
            secretKey = SecretKeySpec(key, "AES")
        }
        catch (e:NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        catch (e:UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }
    fun encrypt(strToEncrypt:String, secret:String): String? {
        try
        {
            defineKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
        }
        catch (e:Exception) {
            println("Erro na descriptografia: " + e.toString())
        }
        return null
    }
    fun decrypt(strToDecrypt:String, secret:String): String? {
        try
        {
            defineKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        }
        catch (e:Exception) {
            println("Erro na descriptografia: " + e.toString())
        }
        return null
    }
}