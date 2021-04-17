package app.storytel.candidate.com.data.remote.datasource.photos

import app.storytel.candidate.com.data.remote.datasource.model.PhotoResponse

interface PhotoDataSource {

    suspend fun getPhotos(): List<PhotoResponse>
}
