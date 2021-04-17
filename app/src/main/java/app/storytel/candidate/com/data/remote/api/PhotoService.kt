package app.storytel.candidate.com.data.remote.api

import app.storytel.candidate.com.data.remote.datasource.model.PhotoModel
import retrofit2.http.GET

interface PhotoService {

    @GET("/photos")
    suspend fun fetchPhotos(): List<PhotoModel>
}
