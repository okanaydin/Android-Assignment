package com.okanaydin.assignment.data.remote.datasource.post

import com.okanaydin.assignment.data.remote.api.PostService
import com.okanaydin.assignment.data.remote.datasource.model.CommentModelFactory.getComment
import com.okanaydin.assignment.data.remote.datasource.model.PostModelFactory.getPost
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSourceImp
import com.okanaydin.assignment.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostRemoteDataSourceImpTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var postService: PostService

    private lateinit var postRemoteDataSourceImp: PostRemoteDataSourceImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postRemoteDataSourceImp = PostRemoteDataSourceImp(postService)
    }

    @Test
    fun `check getPosts`() = runBlocking {
        // given
        coEvery { postService.fetchPosts() } returns listOf(getPost())

        // when
        val postResult = postRemoteDataSourceImp.getPosts()

        // then
        coVerify { postService.fetchPosts() }
        assertEquals(postResult, listOf(getPost()))
    }

    @Test
    fun `check getComment`() = runBlocking {
        // given
        val id = 1
        coEvery { postService.fetchComments(id) } returns listOf(getComment())

        // when
        val commentResult = postRemoteDataSourceImp.getComments(id)

        // then
        coVerify { postService.fetchComments(id) }
        assertEquals(commentResult, listOf(getComment()))
    }
}
