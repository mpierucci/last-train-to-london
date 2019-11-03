package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.lines.domain.Line as DomainLineStatus

class LineRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val restLineMapper: Mapper<RestLine, DomainLineStatus>
) : LineRepository {
    override suspend fun getAll() = lineStatusApi.getStatus().map { restLineMapper.map(it) }
}