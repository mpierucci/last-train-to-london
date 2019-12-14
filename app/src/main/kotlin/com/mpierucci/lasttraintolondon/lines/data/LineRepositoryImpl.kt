package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.lines.domain.Line as DomainLineStatus

class LineRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val restLineMapper: Mapper<RestLine, DomainLineStatus>
) : LineRepository {
    override suspend fun getAll(): List<DomainLineStatus> {
        val lines = lineStatusApi.getStatus()
        return withContext(Dispatchers.Default) { lines.map { restLineMapper.map(it) } }
    }

}