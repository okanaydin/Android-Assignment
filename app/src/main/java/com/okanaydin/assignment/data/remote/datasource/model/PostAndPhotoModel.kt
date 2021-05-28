package com.okanaydin.assignment.data.remote.datasource.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PostAndPhotoModel(
    val postItem: PostModel?,
    val thumbnailUrl: String?,
    val imageUrl: String?
) : Parcelable
