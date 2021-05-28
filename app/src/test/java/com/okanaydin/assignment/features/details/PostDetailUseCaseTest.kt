package com.okanaydin.assignment.features.details

import com.google.common.truth.Truth.assertThat
import com.okanaydin.assignment.data.remote.datasource.model.CommentModelFactory.getComment
import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.details.repository.PostDetailRepository
import com.okanaydin.assignment.features.details.usecase.PostDetailUseCase
import com.okanaydin.assignment.util.MainCoroutineRule
import com.okanaydin.assignment.util.second
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDetailUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var postDetailRepository: PostDetailRepository

    private lateinit var postDetailUseCase: PostDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postDetailUseCase = PostDetailUseCase(postDetailRepository)
    }

    @Test
    fun `when state is loading, then getComments returns loading`() = runBlocking {
        // given
        val id = 1
        coEvery { postDetailRepository.getComments(id) }

        // when
        val flow = postDetailUseCase.getComments(id).first()

        // then
        assertThat(flow).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun `when state is success, then getComments returns comments of the post`() =
        runBlocking {
            // given
            val id = 1
            coEvery { postDetailRepository.getComments(id) } returns listOf(getComment())

            // when
            val result = postDetailUseCase.getComments(id).toList()

            // then
            coVerify { postDetailRepository.getComments(id) }

            assertThat(result).hasSize(2)
            assertThat(result.first()).isInstanceOf(Resource.Loading::class.java)
            assertThat((result.second() as Resource.Success<*>).data).isEqualTo(listOf(getComment()))
        }

    @Test
    fun `when state is failed, then getComments returns error`() =
        runBlocking {
            // given
            val id = 1
            val throwable = mockk<Throwable>()
            coEvery { postDetailRepository.getComments(id) } throws throwable

            // when
            val result = postDetailUseCase.getComments(id).toList()

            // then
            assertThat(result).hasSize(2)
            assertThat(result.first()).isInstanceOf(Resource.Loading::class.java)
            assertThat((result.second() as Resource.Failed<*>).throwable).isEqualTo(throwable)
        }
}
