package com.klezovich.notchdemo.service

import com.klezovich.notchdemo.domain.Article
import com.klezovich.notchdemo.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ArticleService {

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    fun createArticle(title: String, content: String): Article {

        // TODO: Ensure thread safety
        val maxOrder = articleRepository.getMaxOrderId()

        val article = Article()
        article.title = title
        article.content = content
        article.createdAt = Instant.now()
        article.articleOrder = maxOrder + 1

        return articleRepository.save(article)
    }

    fun getArticlesSortedByKey(sortBy: String): List<Article> {
        if (sortBy == "order") {
            return articleRepository.findAllOrderByArticleOrder()
        }

        if (sortBy == "createdAt") {
            return articleRepository.findAllOrderByCreatedAt()
        }

        // TODO: Refactor to custom exception for observability
        throw RuntimeException("Invalid article sort parameter")
    }

    fun reorderArticle(articleId: Long, newOrder: Long) {
        val article = articleRepository.findById(articleId).get()
        // find article entity which has order = newOrder
        // swap its ordering with the current article
        article.articleOrder = newOrder
        articleRepository.save(article)
    }
}
