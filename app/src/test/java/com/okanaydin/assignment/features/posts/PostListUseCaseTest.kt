package com.okanaydin.assignment.features.posts

import com.google.common.truth.Truth.assertThat
import com.okanaydin.assignment.data.remote.datasource.model.PhotoModelFactory.getPhoto
import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModelFactory.getPostAndPhoto
import com.okanaydin.assignment.data.remote.datasource.model.PostModelFactory.getPost
import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.core.Resource.Success
import com.okanaydin.assignment.features.posts.repository.PostRepository
import com.okanaydin.assignment.features.posts.usecase.PostListUseCase
import com.okanaydin.assignment.util.MainCoroutineRule
import com.okanaydin.assignment.util.second
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostListUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var postRepository: PostRepository

    private lateinit var postListUseCase: PostListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postListUseCase = PostListUseCase(postRepository)
    }

    @Test
    fun `when state is loading, then getCombinedPostsAndPhotos returns loading`() = runBlocking {
        // given
        coEvery { postRepository.getPosts() }

        // when
        val result = postListUseCase.getCombinedPostsAndPhotos().first()

        // then
        assertThat(result).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun `when state is success, then getCombinedPostsAndPhotos returns post and photo`() =
        runBlocking {
            // given
            coEvery { postRepository.getPosts() } returns listOf(getPost())
            coEvery { postRepository.getPhotos() } returns listOf(getPhoto())

            // when
            val result = postListUseCase.getCombinedPostsAndPhotos().toList()

            // then
            coVerify { postRepository.getPhotos() }
            coVerify { postRepository.getPosts() }

            assertThat(result).hasSize(2)
            assertThat(result.first()).isInstanceOf(Resource.Loading::class.java)
            assertThat((result.second() as Success<*>).data).isEqualTo(listOf(getPostAndPhoto()))
        }

    @Test
    fun `when state is failed, then getCombinedPostsAndPhotos returns error`() =
        runBlocking {
            // given
            val errorMessage = "ERROR_MESSAGE"
            val throwable = Throwable(errorMessage)
            coEvery { postRepository.getPosts() } throws throwable
            coEvery { postRepository.getPhotos() } throws throwable

            // when
            val result = postListUseCase.getCombinedPostsAndPhotos().toList()

            // then
            assertThat(result).hasSize(2)
            assertThat(result.first()).isInstanceOf(Resource.Loading::class.java)
            assertThat((result.second() as Resource.Failed<*>).throwable.message).isEqualTo(
                errorMessage
            )
        }
}
