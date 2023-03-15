package com.book.manager.domain.repository

import com.book.manager.domain.model.AuthorWithBook
import com.book.manager.domain.model.Book

/**
 * 著者リポジトリインターフェース
 */
interface BookRepository {
    fun findAll(): List<AuthorWithBook>
    fun save(book: Book): Book
    fun delete(id: Int)
}