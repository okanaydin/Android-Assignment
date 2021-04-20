package app.storytel.candidate.com.features.posts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.databinding.ItemPostBinding
import coil.load
import javax.inject.Inject

/**
 * ListAdapter is a convenience wrapper around AsyncListDiffer that implements
 * Adapter common default behavior for item access and counting.
 * ref: https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
 */
class PostAdapter @Inject constructor() :
    ListAdapter<PostAndPhotoModel, PostAdapter.PostViewHolder>(PostListDiffUtil()) {

    private lateinit var viewState: PostViewState

    var postItemClickListener: ((postItem: PostAndPhotoModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val view = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, postItemClickListener)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(
        private val binding: ItemPostBinding,
        private val postItemClickListener: ((postItem: PostAndPhotoModel) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostAndPhotoModel) {
            viewState = PostViewState(post)
            with(binding) {
                root.setOnClickListener { postItemClickListener?.invoke(post) }
                textViewPostTitle.text = viewState.getPostTitle()
                textViewPostBody.text = viewState.getPostBody()
                imageViewPostItem.load(viewState.getImageUrl())
            }
        }
    }
}
