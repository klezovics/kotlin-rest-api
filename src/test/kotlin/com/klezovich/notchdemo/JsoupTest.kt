package com.klezovich.notchdemo

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class JsoupTest {

    @Test
    fun testJsoup() {
        val doc = Jsoup.connect("http://example.com").get()
        doc.select("p").forEach {
            println(it.text())
        }
    }

    @Test
    fun testCanParseHtmlFromString() {
        val html = """
            <html>
            <head><title>My Page</title></head>
            <body>
              <p>p1</p>
              <p>p2</p>
            </body>
            </html>
        """.trimIndent()

        val doc = Jsoup.parse(html)

        assertEquals("My Page", doc.title())

        val ps = doc.select("p")
        assertThat(ps.map { it.text() }, containsInAnyOrder("p1", "p2"))
    }

    @Test
    fun testCanDetectInvalidHtml() {
        val html = "<html> <a </html>"

        assertFalse(Jsoup.isValid(html, Whitelist()))
    }
}
