package app.storytel.candidate.com.data.remote.api

import app.storytel.candidate.com.data.remote.datasource.model.PostResponse
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    suspend fun fetchPosts(): List<PostResponse>
}
