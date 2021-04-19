package app.storytel.candidate.com.features.posts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.databinding.ItemPostBinding
import coil.load

class PostAdapter(
    var postList: List<PostAndPhotoModel>,
    var itemClick: PostItemClick
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface PostItemClick {
        fun onClick(postItem: PostAndPhotoModel)
    }

    fun updateList(postList: List<PostAndPhotoModel>) {
        val diff = DiffUtil.calculateDiff(PostListDiffUtil(this.postList, postList))
        this.postList = postList
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostAndPhotoModel) {
            with(binding) {
                root.setOnClickListener { itemClick.onClick(post) }
                post.let { postItem ->
                    textViewPostTitle.text = postItem.postItem.title
                    textViewPostBody.text = postItem.postItem.body
                    imageViewPostItem.load(postItem.thumbnailUrl)
                }
            }
        }
    }
}
