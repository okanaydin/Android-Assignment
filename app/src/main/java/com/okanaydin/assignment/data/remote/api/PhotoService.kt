package com.okanaydin.assignment.data.remote.api

import com.okanaydin.assignment.data.remote.datasource.model.PhotoModel
import retrofit2.http.GET

interface PhotoService {

    @GET("/photos")
    suspend fun fetchPhotos(): List<PhotoModel>
}
