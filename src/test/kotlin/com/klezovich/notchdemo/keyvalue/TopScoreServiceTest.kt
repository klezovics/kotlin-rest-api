package com.klezovich.notchdemo.keyvalue

import org.junit.jupiter.api.Test

internal class TopScoreServiceTest {

    private val s = TopScoreService()

    @Test
    fun testBasic() {
        s.setInfo(1, 1, 55)
        s.setInfo(1, 2, 8)
        s.setInfo(1, 3, 15)
        s.setInfo(2, 3, 22)

        val l3Info = s.getLevelTop(3)
        println(l3Info)

        val u1Info = s.getUserTop(1)
        println(u1Info)
    }
}
