package com.book.manager.domain.repository

import com.book.manager.domain.model.Author
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
    override fun findBy(id: Int): Author? {
        return this.dslContext.select().from(AUTHOR).where(AUTHOR.ID.eq(id)).fetchOne()?.let { toModel(it) }
    }

    /**
     * 全件取得
     */
    override fun findAll(): List<Author> {
        return this.dslContext.select().from(AUTHOR).fetch().map { toModel(it) }
    }

    /**
     * 登録
     */
    override fun save(firstName: String, lastName: String): Author {
        val record = this.dslContext.newRecord(AUTHOR).also {
            it.firstName = firstName
            it.lastName = lastName
            it.store()
        }
        return Author(record.id!!, record.firstName!!, record.lastName!!)
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
    private fun toModel(record: Record) = Author(
        record.getValue(AUTHOR.ID)!!,
        record.getValue(AUTHOR.FIRST_NAME)!!,
        record.getValue(AUTHOR.LAST_NAME)!!,
    )
}