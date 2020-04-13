package com.igormeira.githubpop.repository

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.igormeira.githubpop.model.Repository

class RepositoriesRecyclerAdapter(private val context: Context, private val clickListener: (Repository) -> Unit)
    : PagedListAdapter<Repository, RepositoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.create(parent, context, clickListener)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }
}