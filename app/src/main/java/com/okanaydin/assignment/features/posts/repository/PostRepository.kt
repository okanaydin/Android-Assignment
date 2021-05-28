package com.okanaydin.assignment.features.posts.repository

import com.okanaydin.assignment.data.remote.datasource.photos.PhotoRemoteDataSource
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSource
import javax.inject.Inject

/**
 * Repository modules handle data operations. They provide a clean API so that the rest of the app can retrieve this data easily.
 * They know where to get the data from and what API calls to make when data is updated.
 * You can consider repositories to be mediators between different data sources, such as persistent models, web services, and caches.
 * ref: https://developer.android.com/jetpack/guide#fetch-data
 */
class PostRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {

    suspend fun getPosts() = postRemoteDataSource.getPosts()

    suspend fun getPhotos() = photoRemoteDataSource.getPhotos()
}
