package app.storytel.candidate.com.data.remote.datasource.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PostModel(
    @Json(name = "userId")
    val userId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "body")
    val body: String?
) : Parcelable
