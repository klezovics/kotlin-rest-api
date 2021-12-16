package com.klezovich.notchdemo.domain

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Article {
    @Id
    @GeneratedValue
    open var id: Long? = null
    open var title: String? = null
    open var content: String? = null
    open var createdAt: Instant? = null
    open var articleOrder: Long? = null
}



