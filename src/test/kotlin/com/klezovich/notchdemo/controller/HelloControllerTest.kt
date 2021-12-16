package com.klezovich.notchdemo.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.hamcrest.CoreMatchers.`is` as coreMatchersIs

@WebMvcTest
internal class HelloControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testCanGetGreeting() {
        mockMvc.get("/")
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.greeting", coreMatchersIs("Hello, world!"))
                }
            }
    }
}
