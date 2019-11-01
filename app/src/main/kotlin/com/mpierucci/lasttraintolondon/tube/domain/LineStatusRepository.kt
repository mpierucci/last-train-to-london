package com.mpierucci.lasttraintolondon.tube.domain

interface LineStatusRepository {

    suspend fun getAll(): List<LineStatus>
}