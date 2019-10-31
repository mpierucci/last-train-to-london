package com.mpierucci.lasttraintolondon.domain

interface LineStatusRepository {

    suspend fun getAll(): List<LineStatus>
}