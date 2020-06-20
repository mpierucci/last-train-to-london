package com.mpierucci.lasttraintolondon.lines.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.lines.databinding.LineStatusListItemBinding
import com.mpierucci.lasttraintolondon.lines.presentation.PresentationLineStatus

internal class LineStatusViewHolder(
    private val binding: LineStatusListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindLineStatus(lineStatus: PresentationLineStatus) {
        binding.lineName.text = lineStatus.name
        binding.lineStatus.text = lineStatus.status
        binding.lineStatus.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                lineStatus.statusColor
            )
        )
        binding.lineIcon.setImageResource(lineStatus.badgeId)
        binding.disruptionsIcon.visibility = lineStatus.disruptionVisibility
    }
}