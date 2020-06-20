package com.mpierucci.lasttraintolondon.lines.data

import arrow.core.Either
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
    override suspend fun getAll(): Either<Throwable, List<DomainLineStatus>> {
        // TODO warning this may catch Cancelled exceptions and let the coroutine on dubious state
        return try {
            val lines = lineStatusApi.getStatus()
            Either.right(
                withContext(dispatcher.default()) {
                    yield()
                    lines.map { it.toDomain() }
                }
            )
        } catch (ex: Exception) {
            Either.left(ex)
        }
    }
}