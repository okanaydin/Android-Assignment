package app.storytel.candidate.com.features.posts.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.features.posts.model.PostAndImages
import app.storytel.candidate.com.R
import app.storytel.candidate.com.features.posts.ui.PostAdapter.PostViewHolder
import app.storytel.candidate.com.features.details.ui.DetailsActivity
import com.bumptech.glide.RequestManager
import java.util.Random

class PostAdapter(private val mRequestManager: RequestManager, private val mActivity: Activity) :
    RecyclerView.Adapter<PostViewHolder>() {
    private var mData: PostAndImages? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = mData!!.mPosts[position].title
        holder.body.text = mData!!.mPosts[position].body
        val index = Random().nextInt(mData!!.mPhotos.size - 1)
        val imageUrl = mData!!.mPhotos[index].thumbnailUrl
        mRequestManager.load(imageUrl).into(holder.image)
        holder.body.setOnClickListener {
            mActivity.startActivity(
                Intent(
                    mActivity,
                    DetailsActivity::class.java
                )
            )
        }
    }

    fun setData(data: PostAndImages?) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (mData == null || mData!!.mPhotos == null) 0 else mData!!.mPosts.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var body: TextView
        var image: ImageView

        init {
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
            image = itemView.findViewById(R.id.image)
        }
    }
}
