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

    /**
     * 著者・書籍を取得します
     */
    fun getBooksByAuthor(id: Int): List<AuthorWithBook> {
        return repository.findBy(id)
    }

    /**
     * 著者・書籍を登録します
     */
    fun registerBook(author: Author, book: Book): Book {
        val saveResultAuthor = repository.save(author)
        return bookRepository.save(
            Book(
                null,
                saveResultAuthor.id,
                book.title

            )
        )
    }

    /**
     * 書籍を更新します
     */
    fun updateBook(book: Book) {
        println("debug message: $book")
        bookRepository.update(book)
    }

    /**
     * 書籍を削除します
     */
    fun deleteBook(bookId: Int) {
        bookRepository.delete(bookId)
    }
}