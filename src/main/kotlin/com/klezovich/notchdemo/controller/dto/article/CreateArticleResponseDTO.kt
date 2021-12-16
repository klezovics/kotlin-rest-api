package com.klezovich.notchdemo.controller.dto.article

import java.time.Instant

class CreateArticleResponseDTO(
    val id: String,
    val createdAt: Instant,
    val title: String,
    val content: String,
    val order: Long
)
