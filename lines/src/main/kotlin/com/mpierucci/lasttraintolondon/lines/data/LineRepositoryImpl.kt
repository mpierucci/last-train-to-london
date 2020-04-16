package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.lines.domain.Line as DomainLineStatus

internal class LineRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val dispatcher: DispatcherProvider
) : LineRepository {
    override suspend fun getAll(): List<DomainLineStatus> {
        val lines = lineStatusApi.getStatus()
        return withContext(dispatcher.default()) {
            yield()
            lines.map { it.toDomain() }
        }
    }
}