package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.lines.domain.Line as DomainLineStatus

class LineRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val restLineMapper: Mapper<RestLine, DomainLineStatus>,
    private val dispachter: DispatcherProvider
) : LineRepository {
    override suspend fun getAll(): List<DomainLineStatus> {
        val lines = lineStatusApi.getStatus()
        return withContext(dispachter.default()) {
            yield()
            lines.map { restLineMapper.map(it) }
        }
    }
}