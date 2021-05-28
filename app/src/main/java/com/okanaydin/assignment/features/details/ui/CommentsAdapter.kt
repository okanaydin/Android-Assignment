package com.okanaydin.assignment.features.details.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okanaydin.assignment.data.remote.datasource.model.CommentModel
import com.okanaydin.assignment.com.databinding.ItemCommentBinding
import javax.inject.Inject

/**
 * ListAdapter is a convenience wrapper around AsyncListDiffer that implements
 * Adapter common default behavior for item access and counting.
 * ref: https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
 */

class CommentsAdapter @Inject constructor() :
    ListAdapter<CommentModel, CommentsAdapter.CommentViewHolder>(CommentsDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        val view = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentModel) {
            with(binding) {
                textViewCommentTitle.text = comment.name
                textViewCommentDescription.text = comment.body
            }
        }
    }
}
