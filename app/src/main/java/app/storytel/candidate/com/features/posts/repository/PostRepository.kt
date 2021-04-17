package app.storytel.candidate.com.features.posts.repository

import app.storytel.candidate.com.data.remote.datasource.photos.PhotoDataSource
import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSource
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postDataSource: PostDataSource,
    private val photoDataSource: PhotoDataSource
) {

    suspend fun getPosts() = postDataSource.getPosts()

    suspend fun getPhotos() = photoDataSource.getPhotos()
}
