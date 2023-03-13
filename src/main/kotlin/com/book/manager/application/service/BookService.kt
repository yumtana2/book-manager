package com.book.manager.application.service

import com.book.manager.domain.model.AuthorWithBook
import com.book.manager.domain.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repository: AuthorRepository
) {
    fun getBooksByAuthor(id: Int): List<AuthorWithBook> {
        return repository.findBy(id)
    }
}