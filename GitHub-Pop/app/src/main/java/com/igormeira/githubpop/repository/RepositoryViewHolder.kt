package com.igormeira.githubpop.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.igormeira.githubpop.R
import com.igormeira.githubpop.databinding.ItemRepositoryBinding
import com.igormeira.githubpop.model.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryViewHolder(view: View, private val context: Context, private val clickListener: (Repository) -> Unit): RecyclerView.ViewHolder(view) {

    fun bind(repository: Repository?) {
        if (repository == null) return

        itemView.title.text = repository.title
        itemView.description.text = repository.description
        itemView.username.text = repository.user?.username
        itemView.star_count.text = repository.getStars()
        itemView.fork_count.text = repository.getForks()

        Glide.with(context)
            .load(repository.user?.avatar)
            .placeholder(R.drawable.ic_user)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.avatar)

        itemView.setOnClickListener { clickListener.invoke(repository) }
    }

    companion object {
        fun create(parent: ViewGroup, context: Context, clickListener: (Repository) -> Unit): RepositoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
            return RepositoryViewHolder(view, context, clickListener)
        }
    }
}