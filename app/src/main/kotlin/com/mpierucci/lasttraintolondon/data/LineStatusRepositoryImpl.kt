package com.mpierucci.lasttraintolondon.data

import com.mpierucci.lasttraintolondon.domain.LineStatusRepository
import javax.inject.Inject

class LineStatusRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val lineStatusMapper: LineStatusMapper
) : LineStatusRepository {

    override suspend fun getAll() = lineStatusApi.getStatus().map { lineStatusMapper.map(it) }
}