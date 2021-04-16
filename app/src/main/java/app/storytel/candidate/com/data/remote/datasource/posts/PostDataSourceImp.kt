package app.storytel.candidate.com.data.remote.datasource.posts

import app.storytel.candidate.com.data.remote.api.PostService
import app.storytel.candidate.com.data.remote.datasource.model.PostResponse
import javax.inject.Inject

/**
 * you do not have to create a new instance of PostService
 * use @Inject in the constructor to have only one instance
 */
class PostDataSourceImp @Inject constructor(
    private val postService: PostService
) : PostDataSource {

    override suspend fun getPosts(): List<PostResponse> = postService.fetchPosts()
}
