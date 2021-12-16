package com.klezovich.notchdemo.repository

import com.klezovich.notchdemo.domain.Article
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : CrudRepository<Article, Long> {
    @Query("SELECT coalesce(max(art.articleOrder), -1) FROM Article art")
    fun getMaxOrderId(): Long

    @Query("SELECT ar FROM Article ar ORDER BY ar.articleOrder")
    fun findAllOrderByArticleOrder(): List<Article>

    @Query("SELECT ar FROM Article ar ORDER BY ar.createdAt")
    fun findAllOrderByCreatedAt(): List<Article>
}
