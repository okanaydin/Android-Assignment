package app.storytel.candidate.com.data.remote.datasource.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PostModel(
    @Json(name = "userId")
    var userId: Int,
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "body")
    var body: String
) : Parcelable
