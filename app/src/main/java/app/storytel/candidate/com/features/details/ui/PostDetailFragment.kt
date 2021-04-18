package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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
        configureToolBar()
        subscribeUi()
    }

    private fun subscribeUi() {
        commentsViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading() -> {
                    // TODO implement loading case
                }
            }
        }
        val postId = postArg.post.postItem?.id
        if (postId != null) {
            commentsViewModel.getCommentList(postId)
        }
        commentsViewModel.commentList.observe(viewLifecycleOwner) { commentList ->
            createCommentList(commentList)
        }
    }

    private fun createCommentList(commentList: List<CommentModel>) {
        binding.recyclerViewComments.apply {
            adapter = CommentsAdapter(commentList)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun configureToolBar() {
        with(binding) {
            backdrop.load(viewState.getImageUrl())
            toolbar.title = viewState.getPostTitle()
        }
    }
}
