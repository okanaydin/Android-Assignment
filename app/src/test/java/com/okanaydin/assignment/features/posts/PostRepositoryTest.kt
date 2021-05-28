package com.okanaydin.assignment.features.posts

import com.okanaydin.assignment.data.remote.datasource.model.PhotoModelFactory.getPhoto
import com.okanaydin.assignment.data.remote.datasource.model.PostModelFactory.getPost
import com.okanaydin.assignment.data.remote.datasource.photos.PhotoRemoteDataSource
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSource
import com.okanaydin.assignment.features.posts.repository.PostRepository
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
class PostRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var postRemoteDataSource: PostRemoteDataSource

    @MockK
    private lateinit var photoRemoteDataSource: PhotoRemoteDataSource

    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postRepository = PostRepository(postRemoteDataSource, photoRemoteDataSource)
    }

    @Test
    fun `check getPosts`() = runBlocking {
        // given
        coEvery { postRemoteDataSource.getPosts() } returns listOf(getPost())

        // when
        val result = postRepository.getPosts()

        // then
        coVerify { postRemoteDataSource.getPosts() }
        assertEquals(result, listOf(getPost()))
    }

    @Test
    fun `check getPhotos`() = runBlocking {
        // given
        coEvery { photoRemoteDataSource.getPhotos() } returns listOf(getPhoto())

        // when
        val result = postRepository.getPhotos()

        // then
        coVerify { photoRemoteDataSource.getPhotos() }
        assertEquals(result, listOf(getPhoto()))
    }
}
