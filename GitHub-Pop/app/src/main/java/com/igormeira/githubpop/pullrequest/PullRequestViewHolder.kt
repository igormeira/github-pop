package com.igormeira.githubpop.pullrequest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.igormeira.githubpop.R
import com.igormeira.githubpop.model.PullRequest
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestViewHolder(view: View, private val context: Context,
                            private val clickListener: (PullRequest) -> Unit): RecyclerView.ViewHolder(view) {

    fun bind(pullRequest: PullRequest?) {
        if (pullRequest == null) return

        itemView.title.text = pullRequest.title
        itemView.description.text = pullRequest.description
        itemView.username.text = pullRequest.user.username
        itemView.date.text = pullRequest.getFormattedDate()

        Glide.with(context)
            .load(pullRequest.user.avatar)
            .placeholder(R.drawable.ic_user)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.avatar)

        itemView.setOnClickListener { clickListener.invoke(pullRequest) }
    }

    companion object {
        fun create(parent: ViewGroup, context: Context, clickListener: (PullRequest) -> Unit): PullRequestViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
            return PullRequestViewHolder(view, context, clickListener)
        }
    }
}