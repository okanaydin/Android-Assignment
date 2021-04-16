package app.storytel.candidate.com.features.posts.model

import app.storytel.candidate.com.data.remote.datasource.model.PhotoResponse
import app.storytel.candidate.com.data.remote.datasource.model.PostResponse

data class PostAndImages(
    var mPostResponses: List<PostResponse>,
    var mPhotoResponses: List<PhotoResponse>
)
