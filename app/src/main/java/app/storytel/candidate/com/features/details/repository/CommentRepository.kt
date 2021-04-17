package app.storytel.candidate.com.features.details.repository

import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSource
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val postDataSource: PostDataSource
) {
    suspend fun getComments(id: Int) = postDataSource.getComments(id)
}
