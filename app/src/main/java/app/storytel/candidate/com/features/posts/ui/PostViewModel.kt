package app.storytel.candidate.com.features.posts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.core.LayoutViewState
import app.storytel.candidate.com.core.Resource
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.features.posts.usecase.PostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postListUseCase: PostListUseCase
) : ViewModel() {

    val postList = MutableLiveData<List<PostAndPhotoModel>>()
    val layoutViewState = MutableLiveData<LayoutViewState>()

    init {
        getPostList()
    }

    fun getPostList() {
        viewModelScope.launch {
            postListUseCase.getCombinedPostsAndPhotos().collect { state ->
                layoutViewState.value = LayoutViewState(state)
                if (state is Resource.Success) {
                    postList.value = state.data
                }
            }
        }
    }
}
