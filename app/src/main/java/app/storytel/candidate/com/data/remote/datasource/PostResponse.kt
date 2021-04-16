package app.storytel.candidate.com.data.remote.datasource

import com.squareup.moshi.Json

data class PostResponse(
    @Json(name = "userId")
    var userId: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "body")
    var body: String?
)
