package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.storytel.candidate.com.databinding.FragmentPostDetailBinding
import app.storytel.candidate.com.features.core.BaseFragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    @Inject
    lateinit var commentAdapter: CommentsAdapter
    private val postDetailViewModel: PostDetailViewModel by viewModels()
    private val postArg by navArgs<PostDetailFragmentArgs>()
    private lateinit var viewState: PostDetailViewState

    override fun getViewBinding(): FragmentPostDetailBinding =
        FragmentPostDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observeCommentList()
    }

    private fun setUpUi() {
        viewState = PostDetailViewState(postArg.post)
        observeLayoutViewState()
        with(binding) {
            toolbar.title = viewState.getPostTitle()
            imageViewBanner.load(viewState.getImageUrl())
            recyclerViewComments.adapter = commentAdapter
            contentFailed.buttonTryAgain.setOnClickListener {
                postDetailViewModel.getCommentList(viewState.getPostId())
            }
        }
    }

    private fun observeLayoutViewState() {
        postDetailViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                contentLoading.progressBar.isVisible = state.isLoading()
                recyclerViewComments.isVisible = state.isSuccess()
                contentFailed.layout.isVisible = state.isFailed()
            }
        }
    }

    private fun observeCommentList() {
        postDetailViewModel.getCommentList(viewState.getPostId())
        postDetailViewModel.commentList.observe(viewLifecycleOwner) { commentList ->
            commentAdapter.submitList(commentList)
        }
    }
}
