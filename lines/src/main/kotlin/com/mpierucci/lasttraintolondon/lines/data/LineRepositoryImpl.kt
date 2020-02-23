package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.android.architecture.usecase.failure.Failure
import com.mpierucci.android.architecture.usecase.functional.Either
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import javax.inject.Inject
import com.mpierucci.lasttraintolondon.lines.domain.Line as DomainLineStatus

class LineRepositoryImpl @Inject constructor(
    private val lineStatusApi: LineStatusApi,
    private val restLineMapper: Mapper<RestLine, DomainLineStatus>,
    private val dispatcher: DispatcherProvider
) : LineRepository {

    @ExperimentalCoroutinesApi
    private val lineRequestChannel = ConflatedBroadcastChannel<Unit>()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun getAll(): Flow<Either<Failure, List<DomainLineStatus>>> {
        return lineRequestChannel.asFlow()
            .map {
                val lines = lineStatusApi.getStatus()
                yield()
                withContext(dispatcher.default()) {
                    Either.Right(lines.map { restLineMapper.map(it) })
                }
            }
            .catch {
                //TODO proper error handling coming
                Either.Left(Failure.Unknown)
            }.also { lineRequestChannel.offer(Unit) }
    }


    @ExperimentalCoroutinesApi
    override fun retry() {
        lineRequestChannel.offer(Unit)
    }
}