package app.storytel.candidate.com.features.details.ui

import androidx.lifecycle.LiveData
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

    private val _commentList = MutableLiveData<List<CommentModel>>()
    val commentList: LiveData<List<CommentModel>> = _commentList

    private val _layoutViewState = MutableLiveData<LayoutViewState>()
    val layoutViewState: LiveData<LayoutViewState> = _layoutViewState

    fun getCommentList(id: Int) {
        viewModelScope.launch {
            commentUseCase.getComments(id).collect { state ->
                _layoutViewState.value = LayoutViewState(state)
                if (state is Resource.Success) {
                    _commentList.value = state.data
                }
            }
        }
    }
}
