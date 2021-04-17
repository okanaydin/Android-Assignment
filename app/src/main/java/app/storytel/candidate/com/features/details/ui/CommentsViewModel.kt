package app.storytel.candidate.com.features.details.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.core.LayoutViewState
import app.storytel.candidate.com.core.Resource
import app.storytel.candidate.com.data.remote.datasource.model.CommentModel
import app.storytel.candidate.com.features.details.usecase.CommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val commentUseCase: CommentUseCase
) : ViewModel() {

    val commentList = MutableLiveData<List<CommentModel>>()
    val layoutViewState = MutableLiveData<LayoutViewState>()

    fun getCommentList(id: Int) {
        viewModelScope.launch {
            commentUseCase.getComments(id).collect { state ->
                when (state) {
                    is Resource.Success -> {
                        commentList.value = state.data
                    }
                    is Resource.Failed -> {
                        layoutViewState.value = LayoutViewState(state)
                    }
                    is Resource.Loading -> {
                        layoutViewState.value = LayoutViewState(state)
                    }
                }
            }
        }
    }
}
