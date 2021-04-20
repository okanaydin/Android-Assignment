package app.storytel.candidate.com.data.remote.datasource.photos

import app.storytel.candidate.com.data.remote.api.PhotoService
import app.storytel.candidate.com.data.remote.datasource.model.PhotoModel
import javax.inject.Inject

class PhotoRemoteDataSourceImp @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemoteDataSource {

    override suspend fun getPhotos(): List<PhotoModel> = photoService.fetchPhotos()
}
