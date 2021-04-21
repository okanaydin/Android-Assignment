package app.storytel.candidate.com.features.posts.ui

import androidx.lifecycle.LiveData
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

/**
 * A ViewModel object provides the data for a specific UI component, such as a fragment or activity, and contains data-handling business logic to communicate with the model.
 * Provide a ViewModel by annotating it with @HiltViewModel and using the @Inject annotation in the ViewModel object's constructor.
 * ref: https://developer.android.com/training/dependency-injection/hilt-jetpack#viewmodels
 */
@HiltViewModel
class PostViewModel @Inject constructor(
    private val postListUseCase: PostListUseCase
) : ViewModel() {

    private val _postList = MutableLiveData<List<PostAndPhotoModel>>()
    val postList: LiveData<List<PostAndPhotoModel>> = _postList

    private val _layoutViewState = MutableLiveData<LayoutViewState>()
    val layoutViewState: LiveData<LayoutViewState> = _layoutViewState

    init {
        getPostList()
    }

    fun getPostList() {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            postListUseCase.getCombinedPostsAndPhotos().collect { state ->
                _layoutViewState.value = LayoutViewState(state)
                if (state is Resource.Success) {
                    _postList.value = state.data
                }
            }
        }
    }
}
