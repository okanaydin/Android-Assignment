package com.okanaydin.assignment.features.details.repository

import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSource
import javax.inject.Inject

class PostDetailRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) {
    suspend fun getComments(id: Int) = postRemoteDataSource.getComments(id)
}
