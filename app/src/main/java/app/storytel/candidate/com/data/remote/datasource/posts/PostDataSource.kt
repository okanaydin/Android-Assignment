package app.storytel.candidate.com.data.remote.datasource.posts

import app.storytel.candidate.com.data.remote.datasource.model.PostResponse

interface PostDataSource {

    suspend fun getPosts(): List<PostResponse>
}
