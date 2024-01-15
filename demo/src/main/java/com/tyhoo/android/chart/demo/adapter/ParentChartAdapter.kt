package com.tyhoo.android.chart.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.chart.demo.R
import com.tyhoo.android.chart.demo.data.ParentChartEntity
import com.tyhoo.android.chart.demo.util.ChartClickHandler

class ParentChartAdapter(
    private val context: Context
) : ListAdapter<ParentChartEntity, ParentChartAdapter.ParentChartViewHolder>(ParentChartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentChartViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_parent_chart, parent, false)
        return ParentChartViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentChartViewHolder, position: Int) {
        val chart = getItem(position)
        holder.bind(chart)
    }

    inner class ParentChartViewHolder(itemView: View) : ViewHolder(itemView) {
        private val parentChartName = itemView.findViewById<TextView>(R.id.parent_chart_name)
        private val childChartList = itemView.findViewById<RecyclerView>(R.id.child_chart_list)
        private val chartClickHandler = ChartClickHandler(context)
        private val childChartAdapter = ChildChartAdapter { chart ->
            chartClickHandler.handleChartClick(chart)
        }

        fun bind(item: ParentChartEntity) {
            parentChartName.text = item.name
            childChartList.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
            childChartList.adapter = childChartAdapter
            childChartAdapter.submitList(item.charts)
        }
    }
}

private class ParentChartDiffCallback : DiffUtil.ItemCallback<ParentChartEntity>() {
    override fun areItemsTheSame(oldItem: ParentChartEntity, newItem: ParentChartEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: ParentChartEntity, newItem: ParentChartEntity
    ): Boolean {
        return oldItem == newItem
    }
}