package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.storytel.candidate.com.databinding.FragmentPostDetailBinding
import app.storytel.candidate.com.features.base.BaseFragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    private val commentsViewModel: CommentsViewModel by viewModels()
    private val postArg by navArgs<PostDetailFragmentArgs>()
    private val commentAdapter = CommentsAdapter()
    private lateinit var viewState: PostDetailViewState

    override fun getViewBinding(): FragmentPostDetailBinding =
        FragmentPostDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewState = PostDetailViewState(postArg.post)
        configureLayoutState()
        configureToolBar()
        configureUi()
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.recyclerViewComments.adapter = commentAdapter
    }

    private fun configureLayoutState() {
        commentsViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                contentLoading.progressBar.isVisible = state.isLoading()
                recyclerViewComments.isVisible = state.isSuccess()
                contentFailed.layout.isVisible = state.isFailed()
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
            commentAdapter.submitList(commentList)
        }
        commentsViewModel.getCommentList(viewState.getPostId())
    }

    private fun configureToolBar() {
        with(binding) {
            imageViewBanner.load(viewState.getImageUrl())
            toolbar.title = viewState.getPostTitle()
        }
    }
}
