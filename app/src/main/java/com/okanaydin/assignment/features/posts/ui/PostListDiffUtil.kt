package com.okanaydin.assignment.features.posts.ui

import androidx.recyclerview.widget.DiffUtil
import com.okanaydin.assignment.data.remote.datasource.model.PostAndPhotoModel

/**
 * DiffUtil is a utility class that calculates the difference between two lists
 * and outputs a list of update operations that converts the first list into the second one.
 * ref: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil
 */
class PostListDiffUtil : DiffUtil.ItemCallback<PostAndPhotoModel>() {

    override fun areItemsTheSame(
        oldItem: PostAndPhotoModel,
        newItem: PostAndPhotoModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PostAndPhotoModel,
        newItem: PostAndPhotoModel
    ): Boolean {
        return oldItem == newItem
    }
}
