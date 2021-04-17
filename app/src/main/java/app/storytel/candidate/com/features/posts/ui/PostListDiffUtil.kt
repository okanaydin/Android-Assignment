package app.storytel.candidate.com.features.posts.ui

import androidx.recyclerview.widget.DiffUtil
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel

/**
 * DiffUtil is a utility class that calculates the difference between two lists
 * and outputs a list of update operations that converts the first list into the second one.
 * ref: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil
 */
class PostListDiffUtil(
    private val oldList: List<PostAndPhotoModel>,
    private val newList: List<PostAndPhotoModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
