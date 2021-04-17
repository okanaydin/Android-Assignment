package app.storytel.candidate.com.data.remote.datasource.model

data class PostAndPhotoResponse(
    val post: PostResponse?,
    val thumbnailUrl: String?,
    val imageUrl: String?
)
