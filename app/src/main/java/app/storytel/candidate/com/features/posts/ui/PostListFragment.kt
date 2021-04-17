package app.storytel.candidate.com.features.posts.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        configureToolBar()
        subscribeUi()
    }

    private fun subscribeUi() {
        postViewModel.layoutViewState.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading() -> {
                    // TODO implement loading case
                }
            }
        }
        postViewModel.postList.observe(viewLifecycleOwner) { postAndPhotoList ->
            createPostList(postAndPhotoList)
        }
    }

    private fun createPostList(postList: List<PostAndPhotoModel>) {

        val list = postList.toMutableList()
        with(binding.content) {
            if (recyclerView.adapter == null) {
                val adapter = PostAdapter(
                    list,
                    object : PostAdapter.PostItemClick {
                        override fun onClick(postItem: PostAndPhotoModel) {
                            val direction =
                                PostListFragmentDirections.actionPostListFragmentToPostDetailFragment()
                            findNavController().navigate(direction)
                        }
                    }
                )
                recyclerView.adapter = adapter
            } else {
                (recyclerView.adapter as PostAdapter).updateList(list)
            }

            val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = manager
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
