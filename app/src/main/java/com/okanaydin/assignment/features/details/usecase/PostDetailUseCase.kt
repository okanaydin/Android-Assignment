package com.okanaydin.assignment.features.details.usecase

import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.details.repository.PostDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostDetailUseCase @Inject constructor(
    private val postDetailRepository: PostDetailRepository
) {
    fun getComments(id: Int) = flow {
        emit(Resource.loading())
        emit(Resource.success(getComment(id)))
    }.catch { exception ->
        emit(Resource.failed(exception))
    }.flowOn(Dispatchers.IO)

    private suspend fun getComment(id: Int) = postDetailRepository.getComments(id)
}
