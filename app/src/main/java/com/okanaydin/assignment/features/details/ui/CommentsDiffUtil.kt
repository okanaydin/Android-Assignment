package com.okanaydin.assignment.features.details.ui

import androidx.recyclerview.widget.DiffUtil
import com.okanaydin.assignment.data.remote.datasource.model.CommentModel

/**
 * DiffUtil is a utility class that calculates the difference between two lists
 * and outputs a list of update operations that converts the first list into the second one.
 * ref: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil
 */
class CommentsDiffUtil : DiffUtil.ItemCallback<CommentModel>() {
    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }
}
