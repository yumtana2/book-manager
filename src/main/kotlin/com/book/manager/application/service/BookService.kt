package com.book.manager.application.service

import com.book.manager.domain.model.Author
import com.book.manager.domain.model.AuthorWithBook
import com.book.manager.domain.model.Book
import com.book.manager.domain.repository.AuthorRepository
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repository: AuthorRepository,
    private val bookRepository: BookRepository,
) {
    fun getBooksByAuthor(id: Int): List<AuthorWithBook> {
        return repository.findBy(id)
    }

    fun registerBook(author: Author, book: Book): Book {
        val author = repository.save(author)
        return bookRepository.save(
            Book(
                null,
                author.id,
                book.title

            )
        )
    }

    fun deleteBook(bookId: Int) {
        bookRepository.delete(bookId)
    }
}