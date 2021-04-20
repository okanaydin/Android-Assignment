package app.storytel.candidate.com.features.details.repository

import app.storytel.candidate.com.data.remote.datasource.posts.PostRemoteDataSource
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) {
    suspend fun getComments(id: Int) = postRemoteDataSource.getComments(id)
}
