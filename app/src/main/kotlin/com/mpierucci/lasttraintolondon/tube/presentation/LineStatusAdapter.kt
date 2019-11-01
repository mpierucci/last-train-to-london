package com.mpierucci.lasttraintolondon.tube.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mpierucci.lasttraintolondon.tube.domain.LineStatus

class LineStatusAdapter : ListAdapter<LineStatus, LineStatusViewHolder>(LINE_STATUS_DIFF) {

    override fun onBindViewHolder(holder: LineStatusViewHolder, position: Int) {
        holder.bindLineStatus(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineStatusViewHolder {
        return LineStatusViewHolder(parent)
    }

    companion object {
        private val LINE_STATUS_DIFF = object : DiffUtil.ItemCallback<LineStatus>() {

            override fun areContentsTheSame(oldItem: LineStatus, newItem: LineStatus): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: LineStatus, newItem: LineStatus): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}