package com.book.manager.domain.repository

import com.book.manager.domain.model.Author
import com.book.manager.domain.model.AuthorWithBook
import com.example.ktknowledgeTodo.infra.jooq.tables.Book.Companion.BOOK_
import com.example.ktknowledgeTodo.infra.jooq.tables.Author.Companion.AUTHOR
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

/**
 * 著者リポジトリ
 */
@Repository
class AuthorRepositoryImpl(private val dslContext: DSLContext) : AuthorRepository {

    /**
     * IDで取得
     */
    override fun findBy(id: Int): List<AuthorWithBook> {
        return this.dslContext.select().from(AUTHOR).join(BOOK_).on(BOOK_.AUTHOR_ID.eq(AUTHOR.ID))
            .where(AUTHOR.ID.eq(id)).fetch().map { toModel(it) }
    }

    /**
     * 全件取得
     */
    override fun findAll(): List<AuthorWithBook> {
        return this.dslContext.select().from(AUTHOR).join(BOOK_).on(BOOK_.AUTHOR_ID.eq(AUTHOR.ID))
            .fetch().map { toModel(it) }    }

    /**
     * 登録
     */
    override fun save(author: Author): Author {
        val record = this.dslContext.newRecord(AUTHOR).also {
            it.name = author.name
            it.store()
        }
        return Author(record.id!!, record.name!!)
    }

    /**
     * IDで削除
     */
    override fun delete(id: Int) {
        this.dslContext.delete(AUTHOR).where(AUTHOR.ID.eq(id)).execute()
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