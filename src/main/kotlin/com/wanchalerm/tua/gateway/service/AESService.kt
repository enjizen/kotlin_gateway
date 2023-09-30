package com.wanchalerm.tua.gateway.service


import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@Service
class AESService {
    private val cipher = Cipher.getInstance(INSTANCE)
    fun decrypt(cipherText: String?, key: String?) : String? {
        getKey(key)?.let {secretKey ->
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            val v = Base64.getDecoder().decode(cipherText)
            val decodedByte = cipher.doFinal(v)
            return String(decodedByte)
        }
        return null
    }

   /* fun encrypt(strToEncrypt: String, secretKey: String): String? {
        Security.addProvider(BouncyCastleProvider())
        val keyBytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        val skey = SecretKeySpec(keyBytes, "AES")
        val input = strToEncrypt.toByteArray(StandardCharsets.UTF_8)
        synchronized(Cipher::class.java) {
            cipher.init(Cipher.ENCRYPT_MODE, skey)

            val cipherText = ByteArray(cipher.getOutputSize(input.size))
            var ctLength = cipher.update(input, 0, input.size, cipherText, 0)
            ctLength += cipher.doFinal(cipherText, ctLength)
            return String(
                Base64.getEncoder().encode(cipherText)
            )
        }

    }*/

    fun encrypt(strToEncrypt: String?, secret: String?): String? {
        getKey(secret)?.let {secretKey->
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt?.toByteArray(StandardCharsets.UTF_8)))
        }
        return null
    }


    private fun getKey(key: String?): SecretKey? {
        key?.let {
             return SecretKeySpec(it.toByteArray(), "AES")
        }
       return null
    }

    companion object {
        private const val INSTANCE = "AES/ECB/PKCS7Padding"
    }
}