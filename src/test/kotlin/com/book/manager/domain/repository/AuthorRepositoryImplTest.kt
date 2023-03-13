package com.book.manager.domain.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AuthorRepositoryImplTest(
    private val repository: AuthorRepository
) {

    @Test
    fun findByTest() {
        val result = repository.findBy(1)
        assertNotNull(result)
    }
}