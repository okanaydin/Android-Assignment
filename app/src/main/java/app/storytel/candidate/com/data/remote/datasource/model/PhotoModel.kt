package app.storytel.candidate.com.data.remote.datasource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoModel(
    @Json(name = "albumId")
    var albumId: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "url")
    var url: String?,
    @Json(name = "thumbnailUrl")
    var thumbnailUrl: String?
)
