package app.storytel.candidate.com.features.posts.repository

import app.storytel.candidate.com.data.remote.datasource.photos.PhotoRemoteDataSource
import app.storytel.candidate.com.data.remote.datasource.posts.PostRemoteDataSource
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val photoDataSource: PhotoRemoteDataSource
) {

    suspend fun getPosts() = postRemoteDataSource.getPosts()

    suspend fun getPhotos() = photoDataSource.getPhotos()
}
