package com.okanaydin.assignment.features.details

import com.okanaydin.assignment.data.remote.datasource.model.CommentModelFactory.getComment
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSource
import com.okanaydin.assignment.features.details.repository.PostDetailRepository
import com.okanaydin.assignment.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDetailRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var postRemoteDataSource: PostRemoteDataSource

    private lateinit var postDetailRepository: PostDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postDetailRepository = PostDetailRepository(postRemoteDataSource)
    }

    @Test
    fun `check getComments`() = runBlocking {
        // given
        val id = 1
        coEvery { postRemoteDataSource.getComments(id) } returns listOf(getComment())

        // when
        val result = postDetailRepository.getComments(id)

        // then
        coVerify { postRemoteDataSource.getComments(id) }
        assertEquals(result, listOf(getComment()))
    }
}
