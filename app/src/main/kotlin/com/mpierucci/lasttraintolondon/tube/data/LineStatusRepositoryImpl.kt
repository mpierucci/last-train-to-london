package com.mpierucci.lasttraintolondon.tube.data

import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
import com.mpierucci.lasttraintolondon.tube.domain.Mapper
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.tube.domain.LineStatus as DomainLineStatus

class LineStatusRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val restLineStatusMapper: Mapper<RestLineStatus, DomainLineStatus>
) : LineStatusRepository {
    override suspend fun getAll() = lineStatusApi.getStatus().map { restLineStatusMapper.map(it) }
}