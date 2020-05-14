package com.igormeira.githubpop.view.pullrequest

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.igormeira.githubpop.model.PullRequest

class PullRequestRecyclerAdapter(private val context: Context, private val clickListener: (PullRequest) -> Unit)
    : PagedListAdapter<PullRequest, PullRequestViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder.create(parent, context, clickListener)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<PullRequest>() {
            override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem == newItem
            }
        }
    }
}