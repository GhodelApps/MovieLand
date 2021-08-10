package com.example.movieland.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datasource.remote.models.responses.Result
import com.example.movieland.data.models.HomeFeed
import com.example.movieland.databinding.ItemFeedHorizontalListBinding

class HomeAdapter(
    private var onPosterClick: (result: Result) -> Unit
) : ListAdapter<HomeFeed, HomeAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemFeedHorizontalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFeedHorizontalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeFeed: HomeFeed) = binding.apply {
            title.text = homeFeed.title
            // Setting Horizontal recyclerview
            HorizontalAdapter(onPosterClick = onPosterClick).let {
                it.submitList(homeFeed.list)
                recyclerviewPostersList.adapter = it
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<HomeFeed>() {

        override fun areItemsTheSame(oldItem: HomeFeed, newItem: HomeFeed): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: HomeFeed, newItem: HomeFeed): Boolean =
            oldItem.equals(newItem)
    }
}