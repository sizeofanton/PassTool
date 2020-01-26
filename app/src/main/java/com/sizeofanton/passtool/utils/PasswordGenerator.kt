import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.util.Random

class PasswordGenerator(
    private val len: Int = 6,
    private val hasUpper: Boolean = false,
    private val hasLower: Boolean = true,
    private val hasDigits: Boolean = true,
    private val hasSymbols: Boolean = false
) {

    private val lowerCaseLetters = "qwertyuiopasdfghjklzxcvbnm"
    private val upperCaseLetters = "QWERTYUIOPASDFGHJKLZXCVBNM"
    private val numbers = "0123456789"
    private val symbols = "!()-.?[]_`~;:@#$%^&*+="

    val resDictionary = StringBuilder()

    init {
        if ((len <= 0) or (!hasDigits.or(hasLower).or(hasSymbols).or(hasUpper)))
            throw IllegalArgumentException("IllegalArguments")
    }

    fun generate(): String {
        resDictionary.clear()
        if (hasDigits) resDictionary.append(numbers)
        if (hasUpper) resDictionary.append(upperCaseLetters)
        if (hasLower) resDictionary.append(lowerCaseLetters)
        if (hasSymbols) resDictionary.append(symbols)

        val pass = StringBuilder()

        for (i in 0 until len) {
            pass.append(resDictionary[generateRandomInt(resDictionary.length - 1)])
        }

        return pass.toString()
    }

    private fun generateRandomInt(upperRange: Int): Int {
        val r = Random()
        return r.nextInt(upperRange)
    }
}
