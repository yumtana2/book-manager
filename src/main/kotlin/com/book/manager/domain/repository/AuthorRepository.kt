package com.book.manager.domain.repository

import com.book.manager.domain.model.Author
import com.book.manager.domain.model.AuthorWithBook

/**
 * 著者リポジトリインターフェース
 */
interface AuthorRepository {
    fun findBy(id: Int): List<AuthorWithBook>
    fun findAll(): List<AuthorWithBook>
    fun save(name: String): Author
    fun delete(id: Int)
}