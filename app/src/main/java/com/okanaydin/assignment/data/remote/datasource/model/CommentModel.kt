package com.okanaydin.assignment.data.remote.datasource.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentModel(
    @Json(name = "postId")
    val postId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "body")
    val body: String?
)
