package com.okanaydin.assignment.data.remote.datasource.posts

import com.okanaydin.assignment.data.remote.datasource.model.CommentModel
import com.okanaydin.assignment.data.remote.datasource.model.PostModel

interface PostRemoteDataSource {

    suspend fun getPosts(): List<PostModel>

    suspend fun getComments(id: Int): List<CommentModel>
}
