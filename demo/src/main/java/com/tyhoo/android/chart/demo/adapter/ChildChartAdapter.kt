package com.tyhoo.android.chart.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.chart.demo.R
import com.tyhoo.android.chart.demo.data.ChildChartEntity

class ChildChartAdapter(
    private val onItemClick: (ChildChartEntity) -> Unit
) : ListAdapter<ChildChartEntity, ChildChartAdapter.LineChartViewHolder>(LineChartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineChartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_child_chart, parent, false)
        return LineChartViewHolder(view)
    }

    override fun onBindViewHolder(holder: LineChartViewHolder, position: Int) {
        val chart = getItem(position)
        holder.bind(chart)
    }

    inner class LineChartViewHolder(itemView: View) : ViewHolder(itemView) {
        private val childChartName = itemView.findViewById<TextView>(R.id.child_chart_name)

        fun bind(item: ChildChartEntity) {
            childChartName.text = item.name

            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}

private class LineChartDiffCallback : DiffUtil.ItemCallback<ChildChartEntity>() {
    override fun areItemsTheSame(oldItem: ChildChartEntity, newItem: ChildChartEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ChildChartEntity, newItem: ChildChartEntity): Boolean {
        return oldItem == newItem
    }
}