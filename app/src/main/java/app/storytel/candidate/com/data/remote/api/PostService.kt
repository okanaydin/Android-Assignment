package app.storytel.candidate.com.data.remote.api

import app.storytel.candidate.com.data.remote.datasource.model.CommentModel
import app.storytel.candidate.com.data.remote.datasource.model.PostModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("/posts")
    suspend fun fetchPosts(): List<PostModel>

    @GET("/posts/{id}/comments")
    suspend fun fetchComments(@Path("id") id: Int?): List<CommentModel>
}
