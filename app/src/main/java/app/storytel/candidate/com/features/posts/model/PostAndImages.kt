package app.storytel.candidate.com.features.posts.model

import app.storytel.candidate.com.data.remote.datasource.Photo
import app.storytel.candidate.com.data.remote.datasource.Post

data class PostAndImages(
    var mPosts: List<Post>,
    var mPhotos: List<Photo>
)
