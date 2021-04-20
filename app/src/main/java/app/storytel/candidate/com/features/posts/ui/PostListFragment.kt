package app.storytel.candidate.com.features.posts.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.FragmentPostListBinding
import app.storytel.candidate.com.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    private val postViewModel: PostViewModel by viewModels()
    private val postAdapter = PostAdapter()

    override fun getViewBinding(): FragmentPostListBinding =
        FragmentPostListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLayoutState()
        configureToolBar()
        configureUi()
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.recyclerViewPostList.adapter = postAdapter
        postAdapter.postItemClickListener = { postItem ->
            val direction = PostListFragmentDirections
                .actionPostListFragmentToPostDetailFragment(postItem)
            findNavController().navigate(direction)
        }
    }

    private fun configureLayoutState() {
        postViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                contentLoading.progressBar.isVisible = state.isLoading()
                recyclerViewPostList.isVisible = state.isSuccess()
                contentFailed.layout.isVisible = state.isFailed()
            }
        }
    }

    private fun configureUi() {
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener(onRefreshListener())
            contentFailed.buttonTryAgain.setOnClickListener {
                postViewModel.getPostList()
            }
        }
    }

    private fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            postViewModel.getPostList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initViewModel() {
        postViewModel.postList.observe(viewLifecycleOwner) { postAndPhotoList ->
            postAdapter.submitList(postAndPhotoList)
        }
    }

    private fun configureToolBar() {
        binding.toolbar.title = getString(R.string.app_name)
    }
}
