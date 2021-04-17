package app.storytel.candidate.com.features.posts.model

import app.storytel.candidate.com.data.remote.datasource.model.PhotoModel
import app.storytel.candidate.com.data.remote.datasource.model.PostModel

data class PostAndImages(
    var mPostRespons: List<PostModel>,
    var mPhotoRespons: List<PhotoModel>
)
