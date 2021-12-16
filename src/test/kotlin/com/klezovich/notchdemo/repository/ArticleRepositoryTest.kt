package com.klezovich.notchdemo.repository

import com.klezovich.notchdemo.domain.Article
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Test
    fun testCanLoadAndSaveArticle() {
        val article = Article()
        article.title = "t1"
        article.content = "c1"

        val id = articleRepository.save(article).id!!

        val articleFromDb = articleRepository.findById(id).get()
        assertEquals("c1", articleFromDb.content)
        //Asserts for all other fields
    }

    // Tests for each of the repository methods
}
