package com.sizeofanton.passtool

import PasswordGenerator
import org.junit.Test
import org.junit.Assert.*
import java.lang.IllegalArgumentException

class PasswordGeneratorTest {
    @Test
    fun test_passwordLength() {
        var p = PasswordGenerator(len=4)
        assertEquals(4, p.generate().length)

        p = PasswordGenerator(len=8)
        assertEquals(8, p.generate().length)

        p = PasswordGenerator(len=16)
        assertEquals(16, p.generate().length)

        p = PasswordGenerator(len=32)
        assertEquals(32, p.generate().length)
    }

    @Test
    fun test_isStringGenerated() {
        val p = PasswordGenerator()
        val s = p.generate()

        assertTrue(s is String)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalidLengthCausesException() {
        val p = PasswordGenerator(len = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalidDictionariesCausesExceptions() {
        val p = PasswordGenerator(hasSymbols = false, hasDigits = false, hasLower = false, hasUpper = false)
    }
}
