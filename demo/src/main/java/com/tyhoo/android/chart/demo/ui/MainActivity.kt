package com.tyhoo.android.chart.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.chart.demo.R
import com.tyhoo.android.chart.demo.adapter.ParentChartAdapter
import com.tyhoo.android.chart.demo.base.BAR_CHARTS
import com.tyhoo.android.chart.demo.base.BAR_CHART_BASIC
import com.tyhoo.android.chart.demo.base.BAR_CHART_BASIC_2
import com.tyhoo.android.chart.demo.base.BAR_CHART_HORIZONTAL
import com.tyhoo.android.chart.demo.base.BAR_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.BAR_CHART_NEGATIVE
import com.tyhoo.android.chart.demo.base.BAR_CHART_NEGATIVE_HORIZONTAL
import com.tyhoo.android.chart.demo.base.BAR_CHART_SINE
import com.tyhoo.android.chart.demo.base.BAR_CHART_STACKED
import com.tyhoo.android.chart.demo.base.BAR_CHART_STACKED_2
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHARTS
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_DYNAMIC
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_HOURLY
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_REALTIME
import com.tyhoo.android.chart.demo.base.LINE_CHARTS
import com.tyhoo.android.chart.demo.base.LINE_CHART_BASIC
import com.tyhoo.android.chart.demo.base.LINE_CHART_COLORFUL
import com.tyhoo.android.chart.demo.base.LINE_CHART_CUBIC
import com.tyhoo.android.chart.demo.base.LINE_CHART_DUAL_AXIS
import com.tyhoo.android.chart.demo.base.LINE_CHART_FILLED
import com.tyhoo.android.chart.demo.base.LINE_CHART_INVERTED_AXIS
import com.tyhoo.android.chart.demo.base.LINE_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.LINE_CHART_PERFORMANCE
import com.tyhoo.android.chart.demo.base.OTHER_CHARTS
import com.tyhoo.android.chart.demo.base.OTHER_CHART_BUBBLE_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_CANDLESTICK
import com.tyhoo.android.chart.demo.base.OTHER_CHART_COMBINED_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_RADAR_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_SCATTER_PLOT
import com.tyhoo.android.chart.demo.base.PIE_CHARTS
import com.tyhoo.android.chart.demo.base.PIE_CHART_BASIC
import com.tyhoo.android.chart.demo.base.PIE_CHART_HALF_PIE
import com.tyhoo.android.chart.demo.base.PIE_CHART_VALUE_LINES
import com.tyhoo.android.chart.demo.base.SCROLLING_CHARTS
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_MANY_BAR_CHARTS
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_TALL_BAR_CHART
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_VIEW_PAGER
import com.tyhoo.android.chart.demo.data.ChildChartEntity
import com.tyhoo.android.chart.demo.data.ParentChartEntity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lineCharts = mutableListOf<ChildChartEntity>()
        lineCharts.add(ChildChartEntity(LINE_CHART_BASIC))
        lineCharts.add(ChildChartEntity(LINE_CHART_MULTIPLE))
        lineCharts.add(ChildChartEntity(LINE_CHART_DUAL_AXIS))
        lineCharts.add(ChildChartEntity(LINE_CHART_INVERTED_AXIS))
        lineCharts.add(ChildChartEntity(LINE_CHART_CUBIC))
        lineCharts.add(ChildChartEntity(LINE_CHART_COLORFUL))
        lineCharts.add(ChildChartEntity(LINE_CHART_PERFORMANCE))
        lineCharts.add(ChildChartEntity(LINE_CHART_FILLED))

        val barCharts = mutableListOf<ChildChartEntity>()
        barCharts.add(ChildChartEntity(BAR_CHART_BASIC))
        barCharts.add(ChildChartEntity(BAR_CHART_BASIC_2))
        barCharts.add(ChildChartEntity(BAR_CHART_MULTIPLE))
        barCharts.add(ChildChartEntity(BAR_CHART_HORIZONTAL))
        barCharts.add(ChildChartEntity(BAR_CHART_STACKED))
        barCharts.add(ChildChartEntity(BAR_CHART_NEGATIVE))
        barCharts.add(ChildChartEntity(BAR_CHART_NEGATIVE_HORIZONTAL))
        barCharts.add(ChildChartEntity(BAR_CHART_STACKED_2))
        barCharts.add(ChildChartEntity(BAR_CHART_SINE))

        val pieCharts = mutableListOf<ChildChartEntity>()
        pieCharts.add(ChildChartEntity(PIE_CHART_BASIC))
        pieCharts.add(ChildChartEntity(PIE_CHART_VALUE_LINES))
        pieCharts.add(ChildChartEntity(PIE_CHART_HALF_PIE))

        val otherCharts = mutableListOf<ChildChartEntity>()
        otherCharts.add(ChildChartEntity(OTHER_CHART_COMBINED_CHART))
        otherCharts.add(ChildChartEntity(OTHER_CHART_SCATTER_PLOT))
        otherCharts.add(ChildChartEntity(OTHER_CHART_BUBBLE_CHART))
        otherCharts.add(ChildChartEntity(OTHER_CHART_CANDLESTICK))
        otherCharts.add(ChildChartEntity(OTHER_CHART_RADAR_CHART))

        val scrollingCharts = mutableListOf<ChildChartEntity>()
        scrollingCharts.add(ChildChartEntity(SCROLLING_CHART_MULTIPLE))
        scrollingCharts.add(ChildChartEntity(SCROLLING_CHART_VIEW_PAGER))
        scrollingCharts.add(ChildChartEntity(SCROLLING_CHART_TALL_BAR_CHART))
        scrollingCharts.add(ChildChartEntity(SCROLLING_CHART_MANY_BAR_CHARTS))

        val evenMoreLineCharts = mutableListOf<ChildChartEntity>()
        evenMoreLineCharts.add(ChildChartEntity(EVEN_MORE_LINE_CHART_DYNAMIC))
        evenMoreLineCharts.add(ChildChartEntity(EVEN_MORE_LINE_CHART_REALTIME))
        evenMoreLineCharts.add(ChildChartEntity(EVEN_MORE_LINE_CHART_HOURLY))

        val parentCharts = mutableListOf<ParentChartEntity>()
        parentCharts.add(ParentChartEntity(LINE_CHARTS, lineCharts))
        parentCharts.add(ParentChartEntity(BAR_CHARTS, barCharts))
        parentCharts.add(ParentChartEntity(PIE_CHARTS, pieCharts))
        parentCharts.add(ParentChartEntity(OTHER_CHARTS, otherCharts))
        parentCharts.add(ParentChartEntity(SCROLLING_CHARTS, scrollingCharts))
        parentCharts.add(ParentChartEntity(EVEN_MORE_LINE_CHARTS, evenMoreLineCharts))

        val parentChartList = findViewById<RecyclerView>(R.id.parent_chart_list)
        val parentChartAdapter = ParentChartAdapter(this)
        parentChartList.adapter = parentChartAdapter
        parentChartAdapter.submitList(parentCharts)
    }
}