package app.storytel.candidate.com.features.details.usecase

import app.storytel.candidate.com.core.Resource
import app.storytel.candidate.com.features.details.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    fun getComments(id: Int) = flow {
        emit(Resource.loading())
        emit(Resource.success(getComment(id)))
    }.catch { exception ->
        emit(Resource.failed(exception.message.toString()))
    }.flowOn(Dispatchers.IO)

    private suspend fun getComment(id: Int) = commentRepository.getComments(id)
}
