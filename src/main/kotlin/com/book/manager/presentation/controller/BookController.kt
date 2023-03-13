package com.book.manager.presentation.controller

import com.book.manager.application.service.BookService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
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
    fun getList(@RequestParam("authorId") authorId: Int): GetBookListResponse{
        val list = service.getBooksByAuthor(authorId).map { BookInfo(it) }
        return GetBookListResponse(list)
    }
}