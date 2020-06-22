package com.mpierucci.lasttraintolondon.lines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mpierucci.lasttraintolondon.lines.databinding.LineStatusListItemBinding
import com.mpierucci.lasttraintolondon.lines.presentation.PresentationLineStatus

internal class LineStatusAdapter :
    ListAdapter<PresentationLineStatus, LineStatusViewHolder>(
        LINE_STATUS_DIFF
    ) {

    override fun onBindViewHolder(holder: LineStatusViewHolder, position: Int) {
        holder.bindLineStatus(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineStatusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LineStatusViewHolder(
            LineStatusListItemBinding.inflate(inflater, parent, false)
        )
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