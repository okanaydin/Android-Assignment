package app.storytel.candidate.com.features.posts.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.storytel.candidate.com.R
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.databinding.FragmentPostListBinding
import app.storytel.candidate.com.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    private val postViewModel: PostViewModel by viewModels()

    override fun getViewBinding(): FragmentPostListBinding =
        FragmentPostListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLayoutState()
        configureToolBar()
        configureUi()
        initViewModel()
    }

    private fun configureLayoutState() {
        postViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                when {
                    state.isLoading() -> {
                        contentLoading.progressBar.isVisible = true
                        recyclerViewPostList.isVisible = false
                        contentFailed.layout.isVisible = false
                    }
                    state.isSuccess() -> {
                        contentLoading.progressBar.isVisible = false
                        recyclerViewPostList.isVisible = true
                        contentFailed.layout.isVisible = false
                    }
                    state.isFailed() -> {
                        contentLoading.progressBar.isVisible = false
                        recyclerViewPostList.isVisible = false
                        contentFailed.layout.isVisible = true
                    }
                }
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
            checkPostListAdapter(postAndPhotoList)
        }
    }

    private fun checkPostListAdapter(postList: List<PostAndPhotoModel>) {
        if (binding.recyclerViewPostList.adapter == null) {
            createPostList(postList)
        } else {
            (binding.recyclerViewPostList.adapter as PostAdapter).updateList(postList)
        }
    }

    private fun createPostList(postList: List<PostAndPhotoModel>) {
        binding.recyclerViewPostList.apply {
            adapter = PostAdapter(
                postList,
                object : PostAdapter.PostItemClick {
                    override fun onClick(postItem: PostAndPhotoModel) {
                        val direction = PostListFragmentDirections
                            .actionPostListFragmentToPostDetailFragment(postItem)
                        findNavController().navigate(direction)
                    }
                }
            )
        }
    }

    private fun configureToolBar() {
        binding.toolbar.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_scrolling, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
