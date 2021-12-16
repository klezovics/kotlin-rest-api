package com.klezovich.notchdemo.controller

import com.klezovich.notchdemo.controller.dto.article.CreateArticleRequestDTO
import com.klezovich.notchdemo.controller.dto.article.CreateArticleResponseDTO
import com.klezovich.notchdemo.domain.Article
import com.klezovich.notchdemo.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController {

    @Autowired
    lateinit var articleService: ArticleService

    @PostMapping
    fun createArticle(@RequestBody dto: CreateArticleRequestDTO): CreateArticleResponseDTO {
        val article = articleService.createArticle(
            title = dto.title,
            content = dto.content
        )
        return toDto(article)
    }

    @GetMapping
    fun getArticles(@RequestParam("sortBy") sortBy: String): List<Article> {
        return articleService.getArticlesSortedByKey(sortBy)
    }

    // Take as a parameter a reordering 
    // 1,2,3  -> 3,2,1
    // 1 -> 3
    // 2 -> 2
    // 3 -> 1 
    @PostMapping("/{id}/reorder")
    fun reorderArticle(@PathVariable("id") articleId: Long, @RequestParam("new_order") newOrder: Long) {
        return articleService.reorderArticle(articleId = articleId, newOrder = newOrder)
    }

    private fun toDto(article: Article): CreateArticleResponseDTO {
        return CreateArticleResponseDTO(
            id = article.id.toString(),
            createdAt = article.createdAt!!,
            title = article.title!!,
            content = article.content!!,
            order = article.articleOrder!!
        )
    }
}
