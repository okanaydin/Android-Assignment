package com.okanaydin.assignment.features.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okanaydin.assignment.data.remote.datasource.model.CommentModel
import com.okanaydin.assignment.features.core.LayoutViewState
import com.okanaydin.assignment.features.core.Resource
import com.okanaydin.assignment.features.details.usecase.PostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postDetailUseCase: PostDetailUseCase
) : ViewModel() {

    private val _commentList = MutableLiveData<List<CommentModel>>()
    val commentList: LiveData<List<CommentModel>> = _commentList

    private val _layoutViewState = MutableLiveData<LayoutViewState>()
    val layoutViewState: LiveData<LayoutViewState> = _layoutViewState

    fun getCommentList(id: Int) {
        viewModelScope.launch {
            postDetailUseCase.getComments(id).collect { state ->
                _layoutViewState.value = LayoutViewState(state)
                if (state is Resource.Success) {
                    _commentList.value = state.data
                }
            }
        }
    }
}
