package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.storytel.candidate.com.data.remote.datasource.model.CommentModel
import app.storytel.candidate.com.databinding.FragmentPostDetailBinding
import app.storytel.candidate.com.features.base.BaseFragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    private val commentsViewModel: CommentsViewModel by viewModels()
    private val postArg by navArgs<PostDetailFragmentArgs>()
    lateinit var viewState: PostDetailViewState

    override fun getViewBinding(): FragmentPostDetailBinding =
        FragmentPostDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewState = PostDetailViewState(postArg.post)
        configureLayoutState()
        configureToolBar()
        configureUi()
        initViewModel()
    }

    private fun configureLayoutState() {
        commentsViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                when {
                    state.isLoading() -> {
                        contentLoading.progressBar.isVisible = true
                        recyclerViewComments.isVisible = false
                        contentFailed.layout.isVisible = false
                    }
                    state.isSuccess() -> {
                        contentLoading.progressBar.isVisible = false
                        recyclerViewComments.isVisible = true
                        contentFailed.layout.isVisible = false
                    }
                    state.isFailed() -> {
                        contentLoading.progressBar.isVisible = false
                        recyclerViewComments.isVisible = false
                        contentFailed.layout.isVisible = true
                    }
                }
            }
        }
    }

    private fun configureUi() {
        with(binding) {
            contentFailed.buttonTryAgain.setOnClickListener {
                commentsViewModel.getCommentList(viewState.getPostId())
                configureToolBar()
            }
        }
    }

    private fun initViewModel() {
        commentsViewModel.commentList.observe(viewLifecycleOwner) { commentList ->
            createCommentList(commentList)
        }
        commentsViewModel.getCommentList(viewState.getPostId())
    }

    private fun createCommentList(commentList: List<CommentModel>) {
        binding.recyclerViewComments.adapter = CommentsAdapter(commentList)
    }

    private fun configureToolBar() {
        with(binding) {
            backdrop.load(viewState.getImageUrl())
            toolbar.title = viewState.getPostTitle()
        }
    }
}
