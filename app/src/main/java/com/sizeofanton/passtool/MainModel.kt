package com.sizeofanton.passtool

import PasswordGenerator
import android.content.ClipboardManager
import android.content.ClipData
import android.content.Context
import com.nulabinc.zxcvbn.Zxcvbn
import java.lang.IllegalArgumentException

class MainModel(private val context: Context){

    fun generate(
        len: Int,
        hasUpper: Boolean,
        hasLower: Boolean,
        hasDigits: Boolean,
        hasSymbols: Boolean
    ): String{
        val gen = PasswordGenerator(len=len, hasUpper = hasUpper, hasLower = hasLower,
                                    hasDigits = hasDigits, hasSymbols = hasSymbols)
        return gen.generate()
    }


    fun copyPasswordToClipboard(password: String){
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("password", password)
        clipboard.setPrimaryClip(clipData)
    }


    @Throws(IllegalArgumentException::class)
    fun measurePasswordStrength(password: String) : Int {
        if (password.isEmpty()) throw IllegalArgumentException("Password can't be empty")
        val zxcvbn = Zxcvbn()
        val strength = zxcvbn.measure(password)

        return strength.score
    }




}