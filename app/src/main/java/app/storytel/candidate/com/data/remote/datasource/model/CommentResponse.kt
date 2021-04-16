package app.storytel.candidate.com.data.remote.datasource.model

import com.squareup.moshi.Json

data class CommentResponse(
    @Json(name = "postId")
    var postId: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "body")
    var body: String?
)
