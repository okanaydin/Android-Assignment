package com.okanaydin.assignment.data.remote.datasource.photos

import com.okanaydin.assignment.data.remote.api.PhotoService
import com.okanaydin.assignment.data.remote.datasource.model.PhotoModel
import javax.inject.Inject

class PhotoRemoteDataSourceImp @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemoteDataSource {

    override suspend fun getPhotos(): List<PhotoModel> = photoService.fetchPhotos()
}
