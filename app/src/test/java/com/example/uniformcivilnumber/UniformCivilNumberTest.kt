package com.example.uniformcivilnumber

import junit.framework.TestCase

class UniformCivilNumberTest : TestCase() {

    fun testDateValid() {
        val ucn = UniformCivilNumber()
        assertTrue(ucn.checkdate(2,2,1986))
    }

    fun testDateInValid() {
        val ucn = UniformCivilNumber()
        assertFalse(ucn.checkdate(2,2,-1))
    }

    fun testEgnIsValid() {
        val ucn = UniformCivilNumber()
        assertTrue(ucn.valid("2501254491"))
    }

    fun testEgnIsInValid() {
        val ucn = UniformCivilNumber()
        assertFalse(ucn.valid("2501253333"))
    }

    fun testParseEgnIsValid() {
        val ucn = UniformCivilNumber()
        val parse = ucn.parse("2501254491")
        assertNotNull(parse)
    }

    fun testParseEgnIsInValid() {
        val ucn = UniformCivilNumber()
        assertNull(ucn.parse("2501253333"))
    }

    fun testInfoEgnIsValid() {
        val ucn = UniformCivilNumber()
        val info = ucn.info("2501254491")
        assertNotNull(info)
    }

    fun testGenerateEgn() {
        val ucn = UniformCivilNumber()
        val info = ucn.generate(2,2,1983, 1, inputRegion = 0)
        assertNotNull(info)
    }
}