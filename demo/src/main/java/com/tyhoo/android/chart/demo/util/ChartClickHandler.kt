package com.tyhoo.android.chart.demo.util

import android.content.Context
import android.content.Intent
import com.tyhoo.android.chart.demo.base.BAR_CHART_BASIC
import com.tyhoo.android.chart.demo.base.BAR_CHART_BASIC_2
import com.tyhoo.android.chart.demo.base.BAR_CHART_HORIZONTAL
import com.tyhoo.android.chart.demo.base.BAR_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.BAR_CHART_NEGATIVE
import com.tyhoo.android.chart.demo.base.BAR_CHART_NEGATIVE_HORIZONTAL
import com.tyhoo.android.chart.demo.base.BAR_CHART_SINE
import com.tyhoo.android.chart.demo.base.BAR_CHART_STACKED
import com.tyhoo.android.chart.demo.base.BAR_CHART_STACKED_2
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_DYNAMIC
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_HOURLY
import com.tyhoo.android.chart.demo.base.EVEN_MORE_LINE_CHART_REALTIME
import com.tyhoo.android.chart.demo.base.LINE_CHART_BASIC
import com.tyhoo.android.chart.demo.base.LINE_CHART_COLORFUL
import com.tyhoo.android.chart.demo.base.LINE_CHART_CUBIC
import com.tyhoo.android.chart.demo.base.LINE_CHART_DUAL_AXIS
import com.tyhoo.android.chart.demo.base.LINE_CHART_FILLED
import com.tyhoo.android.chart.demo.base.LINE_CHART_INVERTED_AXIS
import com.tyhoo.android.chart.demo.base.LINE_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.LINE_CHART_PERFORMANCE
import com.tyhoo.android.chart.demo.base.OTHER_CHART_BUBBLE_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_CANDLESTICK
import com.tyhoo.android.chart.demo.base.OTHER_CHART_COMBINED_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_RADAR_CHART
import com.tyhoo.android.chart.demo.base.OTHER_CHART_SCATTER_PLOT
import com.tyhoo.android.chart.demo.base.PIE_CHART_BASIC
import com.tyhoo.android.chart.demo.base.PIE_CHART_HALF_PIE
import com.tyhoo.android.chart.demo.base.PIE_CHART_VALUE_LINES
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_MANY_BAR_CHARTS
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_MULTIPLE
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_TALL_BAR_CHART
import com.tyhoo.android.chart.demo.base.SCROLLING_CHART_VIEW_PAGER
import com.tyhoo.android.chart.demo.data.ChildChartEntity
import com.tyhoo.android.chart.demo.ui.scrollingchart.SimpleChartDemo
import com.tyhoo.android.chart.demo.ui.barchart.AnotherBarActivity
import com.tyhoo.android.chart.demo.ui.barchart.BarChartActivity
import com.tyhoo.android.chart.demo.ui.barchart.BarChartActivityMultiDataset
import com.tyhoo.android.chart.demo.ui.barchart.BarChartActivitySinus
import com.tyhoo.android.chart.demo.ui.barchart.BarChartPositiveNegative
import com.tyhoo.android.chart.demo.ui.barchart.HorizontalBarChartActivity
import com.tyhoo.android.chart.demo.ui.barchart.HorizontalBarNegativeChartActivity
import com.tyhoo.android.chart.demo.ui.barchart.StackedBarActivity
import com.tyhoo.android.chart.demo.ui.barchart.StackedBarActivityNegative
import com.tyhoo.android.chart.demo.ui.evenmorelinechart.DynamicalAddingActivity
import com.tyhoo.android.chart.demo.ui.evenmorelinechart.LineChartTime
import com.tyhoo.android.chart.demo.ui.evenmorelinechart.RealtimeLineChartActivity
import com.tyhoo.android.chart.demo.ui.linechart.CubicLineChartActivity
import com.tyhoo.android.chart.demo.ui.linechart.FilledLineActivity
import com.tyhoo.android.chart.demo.ui.linechart.InvertedLineChartActivity
import com.tyhoo.android.chart.demo.ui.linechart.LineChartActivity1
import com.tyhoo.android.chart.demo.ui.linechart.LineChartActivity2
import com.tyhoo.android.chart.demo.ui.linechart.LineChartActivityColored
import com.tyhoo.android.chart.demo.ui.linechart.MultiLineChartActivity
import com.tyhoo.android.chart.demo.ui.linechart.PerformanceLineChart
import com.tyhoo.android.chart.demo.ui.otherchart.BubbleChartActivity
import com.tyhoo.android.chart.demo.ui.otherchart.CandleStickChartActivity
import com.tyhoo.android.chart.demo.ui.otherchart.CombinedChartActivity
import com.tyhoo.android.chart.demo.ui.otherchart.RadarChartActivity
import com.tyhoo.android.chart.demo.ui.otherchart.ScatterChartActivity
import com.tyhoo.android.chart.demo.ui.piechart.HalfPieChartActivity
import com.tyhoo.android.chart.demo.ui.piechart.PieChartActivity
import com.tyhoo.android.chart.demo.ui.piechart.PiePolylineChartActivity
import com.tyhoo.android.chart.demo.ui.scrollingchart.ListViewBarChartActivity
import com.tyhoo.android.chart.demo.ui.scrollingchart.ListViewMultiChartActivity
import com.tyhoo.android.chart.demo.ui.scrollingchart.ScrollViewActivity

class ChartClickHandler(private val context: Context) {
    fun handleChartClick(chart: ChildChartEntity) {
        when (chart.name) {
            LINE_CHART_BASIC -> context.startActivity(
                Intent(context, LineChartActivity1::class.java)
            )

            LINE_CHART_MULTIPLE -> context.startActivity(
                Intent(context, MultiLineChartActivity::class.java)
            )

            LINE_CHART_DUAL_AXIS -> context.startActivity(
                Intent(context, LineChartActivity2::class.java)
            )

            LINE_CHART_INVERTED_AXIS -> context.startActivity(
                Intent(context, InvertedLineChartActivity::class.java)
            )

            LINE_CHART_CUBIC -> context.startActivity(
                Intent(context, CubicLineChartActivity::class.java)
            )

            LINE_CHART_COLORFUL -> context.startActivity(
                Intent(context, LineChartActivityColored::class.java)
            )

            LINE_CHART_PERFORMANCE -> context.startActivity(
                Intent(context, PerformanceLineChart::class.java)
            )

            LINE_CHART_FILLED -> context.startActivity(
                Intent(context, FilledLineActivity::class.java)
            )

            BAR_CHART_BASIC -> context.startActivity(
                Intent(context, BarChartActivity::class.java)
            )

            BAR_CHART_BASIC_2 -> context.startActivity(
                Intent(context, AnotherBarActivity::class.java)
            )

            BAR_CHART_MULTIPLE -> context.startActivity(
                Intent(context, BarChartActivityMultiDataset::class.java)
            )

            BAR_CHART_HORIZONTAL -> context.startActivity(
                Intent(context, HorizontalBarChartActivity::class.java)
            )

            BAR_CHART_STACKED -> context.startActivity(
                Intent(context, StackedBarActivity::class.java)
            )

            BAR_CHART_NEGATIVE -> context.startActivity(
                Intent(context, BarChartPositiveNegative::class.java)
            )

            BAR_CHART_NEGATIVE_HORIZONTAL -> context.startActivity(
                Intent(context, HorizontalBarNegativeChartActivity::class.java)
            )

            BAR_CHART_STACKED_2 -> context.startActivity(
                Intent(context, StackedBarActivityNegative::class.java)
            )

            BAR_CHART_SINE -> context.startActivity(
                Intent(context, BarChartActivitySinus::class.java)
            )

            PIE_CHART_BASIC -> context.startActivity(
                Intent(context, PieChartActivity::class.java)
            )

            PIE_CHART_VALUE_LINES -> context.startActivity(
                Intent(context, PiePolylineChartActivity::class.java)
            )

            PIE_CHART_HALF_PIE -> context.startActivity(
                Intent(context, HalfPieChartActivity::class.java)
            )

            OTHER_CHART_COMBINED_CHART -> context.startActivity(
                Intent(context, CombinedChartActivity::class.java)
            )

            OTHER_CHART_SCATTER_PLOT -> context.startActivity(
                Intent(context, ScatterChartActivity::class.java)
            )

            OTHER_CHART_BUBBLE_CHART -> context.startActivity(
                Intent(context, BubbleChartActivity::class.java)
            )

            OTHER_CHART_CANDLESTICK -> context.startActivity(
                Intent(context, CandleStickChartActivity::class.java)
            )

            OTHER_CHART_RADAR_CHART -> context.startActivity(
                Intent(context, RadarChartActivity::class.java)
            )

            SCROLLING_CHART_MULTIPLE -> context.startActivity(
                Intent(context, ListViewMultiChartActivity::class.java)
            )

            SCROLLING_CHART_VIEW_PAGER -> context.startActivity(
                Intent(context, SimpleChartDemo::class.java)
            )

            SCROLLING_CHART_TALL_BAR_CHART -> context.startActivity(
                Intent(context, ScrollViewActivity::class.java)
            )

            SCROLLING_CHART_MANY_BAR_CHARTS -> context.startActivity(
                Intent(context, ListViewBarChartActivity::class.java)
            )

            EVEN_MORE_LINE_CHART_DYNAMIC -> context.startActivity(
                Intent(context, DynamicalAddingActivity::class.java)
            )

            EVEN_MORE_LINE_CHART_REALTIME -> context.startActivity(
                Intent(context, RealtimeLineChartActivity::class.java)
            )

            EVEN_MORE_LINE_CHART_HOURLY -> context.startActivity(
                Intent(context, LineChartTime::class.java)
            )
        }
    }
}