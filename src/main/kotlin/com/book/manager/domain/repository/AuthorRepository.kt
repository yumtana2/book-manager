package com.book.manager.domain.repository

import com.book.manager.domain.model.Author

/**
 * 著者リポジトリインターフェース
 */
interface AuthorRepository {
    fun findBy(id: Int): Author?
    fun findAll(): List<Author>
    fun save(firstName: String, lastName: String): Author
    fun delete(id: Int)
}