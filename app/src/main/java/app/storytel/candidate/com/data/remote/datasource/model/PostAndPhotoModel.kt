package app.storytel.candidate.com.data.remote.datasource.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostAndPhotoModel(
    val postItem: PostModel,
    val thumbnailUrl: String,
    val imageUrl: String
) : Parcelable
