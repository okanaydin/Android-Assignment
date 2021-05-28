package com.okanaydin.assignment.features.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModel
import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModelFactory.getPostAndPhoto
import com.okanaydin.assignment.features.core.LayoutViewState
import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.posts.ui.PostViewModel
import com.okanaydin.assignment.features.posts.usecase.PostListUseCase
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
class PostViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var postListUseCase: PostListUseCase

    @MockK
    private lateinit var layoutViewStateObserver: Observer<LayoutViewState>

    @MockK
    private lateinit var postListStateObserver: Observer<List<PostAndPhotoModel>>

    private lateinit var postViewModel: PostViewModel

    private val layoutViewStateValues = arrayListOf<LayoutViewState>()
    private val postListValues = arrayListOf<List<PostAndPhotoModel>>()

    private val layoutViewStateSlot = slot<LayoutViewState>()
    private val postListSlot = slot<List<PostAndPhotoModel>>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        postViewModel = PostViewModel(postListUseCase)
    }

    @Test
    fun `check getPostList success`() = runBlockingTest {
        // given
        postViewModel.layoutViewState.observeForever(layoutViewStateObserver)
        postViewModel.postList.observeForever(postListStateObserver)

        every { layoutViewStateObserver.onChanged(capture(layoutViewStateSlot)) } answers {
            layoutViewStateValues.add(
                layoutViewStateSlot.captured
            )
        }

        every { postListStateObserver.onChanged(capture(postListSlot)) } answers {
            postListValues.add(
                postListSlot.captured
            )
        }

        coEvery { postListUseCase.getCombinedPostsAndPhotos() } returns flow {
            emit(Resource.loading())
            emit(Resource.success(listOf(getPostAndPhoto())))
        }

        // when
        postViewModel.getPostList()

        // then
        coVerify(exactly = 1) { postListUseCase.getCombinedPostsAndPhotos() }
        assertThat(layoutViewStateValues).hasSize(2)
        assertThat(layoutViewStateValues.first().isLoading()).isTrue()
        assertThat(layoutViewStateValues.second().isSuccess()).isTrue()
        assertThat(postListValues).hasSize(1)
        assertThat(postListValues.first()).isEqualTo(listOf(getPostAndPhoto()))
    }

    @Test
    fun `check getPostList fail`() = runBlockingTest {
        // given
        val throwable = mockk<Throwable>()
        postViewModel.layoutViewState.observeForever(layoutViewStateObserver)
        postViewModel.postList.observeForever(postListStateObserver)

        every { layoutViewStateObserver.onChanged(capture(layoutViewStateSlot)) } answers {
            layoutViewStateValues.add(
                layoutViewStateSlot.captured
            )
        }

        every { postListStateObserver.onChanged(capture(postListSlot)) } answers {
            postListValues.add(
                postListSlot.captured
            )
        }

        coEvery { postListUseCase.getCombinedPostsAndPhotos() } returns flow<Resource<List<PostAndPhotoModel>>> {
            emit(Resource.loading())
            emit(Resource.failed(throwable))
        }

        // when
        postViewModel.getPostList()

        // then
        coVerify(exactly = 1) { postListUseCase.getCombinedPostsAndPhotos() }
        assertThat(layoutViewStateValues).hasSize(2)
        assertThat(layoutViewStateValues.first().isLoading()).isTrue()
        assertThat(layoutViewStateValues.second().isFailed()).isTrue()
        assertThat(postListValues).hasSize(0)
    }
}
