package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.databinding.LineStatusListItemBinding

class LineStatusViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context).inflate(R.layout.line_status_list_item, parent, false)
) {
    private val viewBinding = LineStatusListItemBinding.bind(itemView)

    fun bindLineStatus(lineStatus: PresentationLineStatus) {
        viewBinding.lineName.text = lineStatus.name
        viewBinding.lineStatus.text = lineStatus.status
        viewBinding.lineStatus.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                lineStatus.statusColor
            )
        )
        viewBinding.lineIcon.setImageResource(lineStatus.badgeId)
        viewBinding.disruptionsIcon.visibility = lineStatus.disruptionVisibility
    }
}