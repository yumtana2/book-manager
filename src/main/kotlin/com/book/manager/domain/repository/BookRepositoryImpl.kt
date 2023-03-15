package com.book.manager.domain.repository

import com.book.manager.domain.model.Author
import com.book.manager.domain.model.AuthorWithBook
import com.book.manager.domain.model.Book
import com.example.ktknowledgeTodo.infra.jooq.tables.references.AUTHOR
import com.example.ktknowledgeTodo.infra.jooq.tables.references.BOOK_
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

/**
 * 書籍リポジトリ
 */
@Repository
class BookRepositoryImpl(private val dslContext: DSLContext) : BookRepository {

    /**
     * 全件取得
     */
    override fun findAll(): List<AuthorWithBook> {
        return this.dslContext.select().from(BOOK_).join(BOOK_).on(BOOK_.ID.eq(BOOK_.ID))
            .fetch().map { toModel(it) }
    }

    /**
     * 登録
     */
    override fun save(book: Book): Book {
        val record = this.dslContext.newRecord(BOOK_).also {
            it.authorId = book.authorId
            it.title = book.title
            it.store()
        }
        return Book(record.id!!, record.authorId!!, record.title!!)
    }

    override fun update(book: Book) {
        this.dslContext.update(BOOK_).set(BOOK_.TITLE, book.title).where(BOOK_.ID.eq(book.id)).execute()
    }

    /**
     * IDで削除
     */
    override fun delete(id: Int) {
        this.dslContext.delete(BOOK_).where(BOOK_.ID.eq(id)).execute()
    }

    /**
     * 取得レコードからモデルへ変換する
     */
    private fun toAuthorModel(record: Record) = Author(
        record.getValue(AUTHOR.ID)!!,
        record.getValue(AUTHOR.NAME)!!,
    )

    private fun toModel(record: Record) = AuthorWithBook(
        record.getValue(AUTHOR.ID)!!,
        record.getValue(BOOK_.ID)!!,
        record.getValue(AUTHOR.NAME)!!,
        record.getValue(BOOK_.TITLE)!!,
    )
}