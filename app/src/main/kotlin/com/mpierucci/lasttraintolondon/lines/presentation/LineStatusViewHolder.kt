package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.R
import kotlinx.android.synthetic.main.line_status_list_item.view.*

class LineStatusViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context).inflate(R.layout.line_status_list_item, parent, false)
) {

    fun bindLineStatus(lineStatus: PresentationLineStatus) {
        itemView.lineName.text = lineStatus.name
        itemView.lineStatus.text = lineStatus.status
        itemView.lineStatus.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                lineStatus.statusColor
            )
        )
        itemView.lineIcon.setImageResource(lineStatus.badgeId)
        itemView.disruptionsIcon.visibility = lineStatus.disruptionVisibility
    }
}