package com.klezovich.notchdemo.controller

import com.klezovich.notchdemo.controller.dto.GetGreetingResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HelloController {

    @GetMapping
    fun getHello(): GetGreetingResponse {
        return GetGreetingResponse(greeting = "Hello, world!")
    }
}
