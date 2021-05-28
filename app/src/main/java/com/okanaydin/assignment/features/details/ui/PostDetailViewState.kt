package com.okanaydin.assignment.features.details.ui

import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModel
import java.util.Locale

data class PostDetailViewState(private var postDetail: PostAndPhotoModel) {

    fun getImageUrl() = postDetail.imageUrl ?: ""

    fun getPostId() = postDetail.postItem?.id ?: 0

    fun getPostTitle() = postDetail.postItem?.title?.capitalize(Locale.ROOT) ?: "Unknown post"
}
