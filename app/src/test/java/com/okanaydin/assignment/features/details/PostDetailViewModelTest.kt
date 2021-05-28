package com.okanaydin.assignment.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.okanaydin.assignment.data.remote.datasource.model.CommentModel
import com.okanaydin.assignment.data.remote.datasource.model.CommentModelFactory.getComment
import com.okanaydin.assignment.features.core.LayoutViewState
import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.details.ui.PostDetailViewModel
import com.okanaydin.assignment.features.details.usecase.PostDetailUseCase
import com.okanaydin.assignment.util.MainCoroutineRule
import com.okanaydin.assignment.util.second
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var postDetailUseCase: PostDetailUseCase

    @MockK
    private lateinit var layoutViewStateObserver: Observer<LayoutViewState>

    @MockK
    private lateinit var commentListStateObserver: Observer<List<CommentModel>>

    private lateinit var postDetailViewModel: PostDetailViewModel

    private val layoutViewStateValues = arrayListOf<LayoutViewState>()
    private val commentListValues = arrayListOf<List<CommentModel>>()

    private val layoutViewStateSlot = slot<LayoutViewState>()
    private val commentListSlot = slot<List<CommentModel>>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postDetailViewModel = PostDetailViewModel(postDetailUseCase)
    }

    @Test
    fun `check getCommentList success`() = runBlockingTest {
        // given
        postDetailViewModel.layoutViewState.observeForever(layoutViewStateObserver)
        postDetailViewModel.commentList.observeForever(commentListStateObserver)

        every { layoutViewStateObserver.onChanged(capture(layoutViewStateSlot)) } answers {
            layoutViewStateValues.add(
                layoutViewStateSlot.captured
            )
        }

        every { commentListStateObserver.onChanged(capture(commentListSlot)) } answers {
            commentListValues.add(
                commentListSlot.captured
            )
        }

        coEvery { postDetailUseCase.getComments(1) } returns flow {
            emit(Resource.loading())
            emit(Resource.success(listOf(getComment())))
        }

        // when
        postDetailViewModel.getCommentList(1)

        // then
        coVerify(exactly = 1) { postDetailUseCase.getComments(1) }
        assertThat(layoutViewStateValues).hasSize(2)
        assertThat(layoutViewStateValues.first().isLoading()).isTrue()
        assertThat(layoutViewStateValues.second().isSuccess()).isTrue()
        assertThat(commentListValues).hasSize(1)
        assertThat(commentListValues.first()).isEqualTo(listOf(getComment()))
    }

    @Test
    fun `check getCommentList fail`() = runBlockingTest {
        // given
        val throwable = mockk<Throwable>()
        postDetailViewModel.layoutViewState.observeForever(layoutViewStateObserver)
        postDetailViewModel.commentList.observeForever(commentListStateObserver)

        every { layoutViewStateObserver.onChanged(capture(layoutViewStateSlot)) } answers {
            layoutViewStateValues.add(
                layoutViewStateSlot.captured
            )
        }

        every { commentListStateObserver.onChanged(capture(commentListSlot)) } answers {
            commentListValues.add(
                commentListSlot.captured
            )
        }

        coEvery { postDetailUseCase.getComments(1) } returns flow<Resource<List<CommentModel>>> {
            emit(Resource.loading())
            emit(Resource.failed(throwable))
        }

        // when
        postDetailViewModel.getCommentList(1)

        // then
        coVerify(exactly = 1) { postDetailUseCase.getComments(1) }
        assertThat(layoutViewStateValues).hasSize(2)
        assertThat(layoutViewStateValues.first().isLoading()).isTrue()
        assertThat(layoutViewStateValues.second().isFailed()).isTrue()
        assertThat(commentListValues).hasSize(0)
    }
}
