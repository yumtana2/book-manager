package com.book.manager.presentation.controller

import com.book.manager.application.service.BookService
import com.book.manager.domain.model.Author
import com.book.manager.domain.model.Book
import com.book.manager.presentation.form.BookInfo
import com.book.manager.presentation.form.GetBookListResponse
import com.book.manager.presentation.form.ResisterBookRequest
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class BookController(
    private val service: BookService
) {
    @GetMapping("/list")
    fun getList(@RequestParam("authorId") authorId: Int): GetBookListResponse {
        val list = service.getBooksByAuthor(authorId).map { BookInfo(it) }
        return GetBookListResponse(list)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: ResisterBookRequest){
        service.registerBook(
            Author(null, request.author),
            Book(
                request.id,
                request.id,
                request.title,
                )
        )
    }

    @DeleteMapping("/delete/{book_id}")
    fun delete(@PathVariable("book_id") bookId: Int){
        service.deleteBook(bookId)
    }
}