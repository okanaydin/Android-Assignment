package com.okanaydin.assignment.features.posts.ui

import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModel
import java.util.Locale

data class PostViewState(var postDetail: PostAndPhotoModel) {

    fun getImageUrl() = postDetail.thumbnailUrl ?: ""

    fun getPostTitle() =
        postDetail.postItem?.title?.capitalize(Locale.ROOT) ?: "Unknown post title"

    fun getPostBody() = postDetail.postItem?.body?.capitalize(Locale.ROOT) ?: "Unknown post body"
}
