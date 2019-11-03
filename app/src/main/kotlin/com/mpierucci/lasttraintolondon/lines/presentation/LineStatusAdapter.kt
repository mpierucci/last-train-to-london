package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class LineStatusAdapter :
    ListAdapter<PresentationLineStatus, LineStatusViewHolder>(LINE_STATUS_DIFF) {

    override fun onBindViewHolder(holder: LineStatusViewHolder, position: Int) {
        holder.bindLineStatus(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineStatusViewHolder {
        return LineStatusViewHolder(parent)
    }

    companion object {
        private val LINE_STATUS_DIFF =
            object : DiffUtil.ItemCallback<PresentationLineStatus>() {

            override fun areContentsTheSame(
                oldItem: PresentationLineStatus,
                newItem: PresentationLineStatus
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: PresentationLineStatus,
                newItem: PresentationLineStatus
            ): Boolean {
                return oldItem.badgeId == newItem.badgeId
            }
        }
    }
}