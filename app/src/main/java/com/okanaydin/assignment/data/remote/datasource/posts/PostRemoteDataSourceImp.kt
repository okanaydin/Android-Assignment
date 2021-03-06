package com.okanaydin.assignment.data.remote.datasource.posts

import com.okanaydin.assignment.data.remote.api.PostService
import com.okanaydin.assignment.data.remote.datasource.model.CommentModel
import com.okanaydin.assignment.data.remote.datasource.model.PostModel
import javax.inject.Inject

/**
 * you do not have to create a new instance of PostService
 * use @Inject in the constructor to have only one instance
 */
class PostRemoteDataSourceImp @Inject constructor(
    private val postService: PostService
) : PostRemoteDataSource {

    override suspend fun getPosts(): List<PostModel> = postService.fetchPosts()

    override suspend fun getComments(id: Int): List<CommentModel> = postService.fetchComments(id)
}
