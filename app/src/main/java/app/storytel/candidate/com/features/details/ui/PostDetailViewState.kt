package app.storytel.candidate.com.features.details.ui

import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import java.util.Locale

data class PostDetailViewState(private var postDetail: PostAndPhotoModel) {

    fun getImageUrl() = postDetail.imageUrl ?: ""

    fun getPostId() = postDetail.postItem?.id ?: 0

    fun getPostTitle() = postDetail.postItem?.title?.capitalize(Locale.ROOT) ?: "Unknown post"
}
