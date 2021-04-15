package app.storytel.candidate.com

data class Photo(
    var albumId: Int = 0,
    var id: Int = 0,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null
)