package com.okanaydin.assignment.data.remote.datasource.photos

import com.okanaydin.assignment.data.remote.datasource.model.PhotoModel

interface PhotoRemoteDataSource {

    suspend fun getPhotos(): List<PhotoModel>
}
