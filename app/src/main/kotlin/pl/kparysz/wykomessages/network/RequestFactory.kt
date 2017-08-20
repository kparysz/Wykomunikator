package pl.kparysz.wykomessages.network

import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object RequestFactory {

    private val ALGORITHM = "MD5"
    private val CHARSET = "UTF-8"

    fun hash(secret: String, page: String): String {
        var hashedPassword = secret + page

        try {
            val md = MessageDigest.getInstance(ALGORITHM)
            md.update(hashedPassword.toByteArray(charset(CHARSET)))
            val hash = BigInteger(1, md.digest())
            hashedPassword = hash.toString(16)
            while (hashedPassword.length < 32) {
                hashedPassword = "0" + hashedPassword
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return hashedPassword
    }
}