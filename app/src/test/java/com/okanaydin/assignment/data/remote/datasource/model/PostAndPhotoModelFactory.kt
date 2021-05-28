package com.okanaydin.assignment.data.remote.datasource.model

import com.okanaydin.assignment.data.remote.datasource.model.PostModelFactory.getPost

object PostAndPhotoModelFactory {

    fun getPostAndPhoto() =
        PostAndPhotoModel(
            postItem = getPost(),
            thumbnailUrl = "thumbnailUrl",
            imageUrl = "imageUrl"
        )
}
