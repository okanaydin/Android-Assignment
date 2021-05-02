package app.storytel.candidate.com.features.posts.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.FragmentPostListBinding
import app.storytel.candidate.com.features.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment that is annotated with @AndroidEntryPoint can get the ViewModel instance as normal using ViewModelProvider or the by viewModels() using KTX
 * ref: https://developer.android.com/training/dependency-injection/hilt-jetpack#viewmodels
 */
@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    @Inject
    lateinit var postAdapter: PostAdapter

    private val postViewModel: PostViewModel by viewModels()

    override fun getViewBinding(): FragmentPostListBinding =
        FragmentPostListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observePostList()
    }

    private fun setUpUi() {
        with(binding) {
            observeLayoutViewState()

            toolbar.title = getString(R.string.app_name)

            swipeRefreshLayout.setOnRefreshListener(onRefreshListener())

            contentFailed.buttonTryAgain.setOnClickListener {
                postViewModel.getPostList()
            }

            binding.recyclerViewPostList.adapter = postAdapter
            postAdapter.postItemClickListener = { postItem ->
                val direction = PostListFragmentDirections
                    .actionPostListFragmentToPostDetailFragment(postItem)
                findNavController().navigate(direction)
            }
        }
    }

    private fun observeLayoutViewState() {
        postViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                contentLoading.progressBar.isVisible = state.isLoading()
                recyclerViewPostList.isVisible = state.isSuccess()
                contentFailed.layout.isVisible = state.isFailed()
            }
        }
    }

    private fun observePostList() {
        postViewModel.postList.observe(viewLifecycleOwner) { postAndPhotoList ->
            postAdapter.submitList(postAndPhotoList)
        }
    }

    private fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            postViewModel.getPostList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
