package app.storytel.candidate.com.data.remote.datasource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoModel(
    @Json(name = "albumId")
    val albumId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String?
)
