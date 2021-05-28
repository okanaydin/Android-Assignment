package com.okanaydin.assignment.features.posts.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.okanaydin.assignment.com.R
import com.okanaydin.assignment.com.databinding.FragmentPostListBinding
import com.okanaydin.assignment.features.core.BaseFragment
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
        setUpViewModel()
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

    private fun setUpViewModel() {
        postViewModel.postList.observe(viewLifecycleOwner) { postAndPhotoList ->
            postAdapter.submitList(postAndPhotoList)
        }
        postViewModel.getPostList()
    }

    private fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            postViewModel.getPostList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
