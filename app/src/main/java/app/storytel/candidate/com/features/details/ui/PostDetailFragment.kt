package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
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

    override fun getViewBinding(): FragmentPostDetailBinding =
        FragmentPostDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        with(binding) {
            title1.text = commentList[0].name
            description1.text = commentList[0].body
            title2.text = commentList[1].name
            description2.text = commentList[1].body
            title3.text = commentList[2].name
            description3.text = commentList[2].body

            backdrop.load(postArg.post.imageUrl)
        }
    }

    private fun configureToolBar() {
        binding.toolbar.title = postArg.post.postItem?.title
    }
}
