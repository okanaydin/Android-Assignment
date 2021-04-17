package app.storytel.candidate.com.data.remote.datasource.photos

import app.storytel.candidate.com.data.remote.datasource.model.PhotoModel

interface PhotoDataSource {

    suspend fun getPhotos(): List<PhotoModel>
}
