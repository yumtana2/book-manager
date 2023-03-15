package com.book.manager.presentation.form

import com.book.manager.domain.model.AuthorWithBook

data class GetBookListResponse(val bookList: List<BookInfo>)

data class BookInfo(
    val bookId: Int,
    val authorId: Int,
    val title: String,
    val author: String
) {
    constructor(model: AuthorWithBook) : this(model.bookId, model.authorId, model.title, model.name)
}
data class ResisterBookRequest(
    val id: Int,
    val title: String,
    val author: String,
)