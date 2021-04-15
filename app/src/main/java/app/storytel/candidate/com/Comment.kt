package app.storytel.candidate.com

data class Comment(
    var postId: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
)