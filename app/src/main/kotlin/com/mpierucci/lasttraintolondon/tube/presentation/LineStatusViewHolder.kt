package com.mpierucci.lasttraintolondon.tube.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.tube.domain.LineStatus
import kotlinx.android.synthetic.main.line_status_list_item.view.*

class LineStatusViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context).inflate(R.layout.line_status_list_item, parent, false)
) {

    fun bindLineStatus(lineStatus: LineStatus) {
        itemView.lineName.text = lineStatus.name
        itemView.lineStatus.text = lineStatus.statuses.firstOrNull()?.severityDescription.orEmpty()
    }
}