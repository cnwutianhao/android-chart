package com.tyhoo.android.chart.demo.ui.barchart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.charts.HorizontalBarChart;
import com.tyhoo.android.chart.library.components.AxisBase;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.components.XAxis;
import com.tyhoo.android.chart.library.components.YAxis;
import com.tyhoo.android.chart.library.data.BarData;
import com.tyhoo.android.chart.library.data.BarDataSet;
import com.tyhoo.android.chart.library.data.BarEntry;
import com.tyhoo.android.chart.library.data.Entry;
import com.tyhoo.android.chart.library.formatter.IAxisValueFormatter;
import com.tyhoo.android.chart.library.formatter.IValueFormatter;
import com.tyhoo.android.chart.library.highlight.Highlight;
import com.tyhoo.android.chart.library.interfaces.datasets.IBarDataSet;
import com.tyhoo.android.chart.library.listener.OnChartValueSelectedListener;
import com.tyhoo.android.chart.library.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StackedBarActivityNegative extends AppCompatActivity implements
        OnChartValueSelectedListener {

    private HorizontalBarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_age_distribution);

        setTitle("StackedBarActivityNegative");

        chart = findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setHighlightFullBarEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setAxisMaximum(25f);
        chart.getAxisRight().setAxisMinimum(-25f);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawZeroLine(true);
        chart.getAxisRight().setLabelCount(7, false);
        chart.getAxisRight().setValueFormatter(new CustomFormatter());
        chart.getAxisRight().setTextSize(9f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(9f);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(110f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setLabelCount(12);
        xAxis.setGranularity(10f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private final DecimalFormat format = new DecimalFormat("###");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return format.format(value) + "-" + format.format(value + 10);
            }
        });

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        // IMPORTANT: When using negative values in stacked bars, always make sure the negative values are in the array first
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(5, new float[]{-10, 10}));
        values.add(new BarEntry(15, new float[]{-12, 13}));
        values.add(new BarEntry(25, new float[]{-15, 15}));
        values.add(new BarEntry(35, new float[]{-17, 17}));
        values.add(new BarEntry(45, new float[]{-19, 20}));
        values.add(new BarEntry(45, new float[]{-19, 20}, getResources().getDrawable(R.drawable.star)));
        values.add(new BarEntry(55, new float[]{-19, 19}));
        values.add(new BarEntry(65, new float[]{-16, 16}));
        values.add(new BarEntry(75, new float[]{-13, 14}));
        values.add(new BarEntry(85, new float[]{-10, 11}));
        values.add(new BarEntry(95, new float[]{-5, 6}));
        values.add(new BarEntry(105, new float[]{-1, 2}));

        BarDataSet set = new BarDataSet(values, "Age Distribution");
        set.setDrawIcons(false);
        set.setValueFormatter(new CustomFormatter());
        set.setValueTextSize(7f);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setColors(Color.rgb(67, 67, 72), Color.rgb(124, 181, 236));
        set.setStackLabels(new String[]{
                "Men", "Women"
        });

        BarData data = new BarData(set);
        data.setBarWidth(8.5f);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        BarEntry entry = (BarEntry) e;
        Log.i("VAL SELECTED",
                "Value: " + Math.abs(entry.getYVals()[h.getStackIndex()]));
    }

    @Override
    public void onNothingSelected() {
        Log.i("NOTING SELECTED", "");
    }

    private class CustomFormatter implements IValueFormatter, IAxisValueFormatter {

        private final DecimalFormat mFormat;

        CustomFormatter() {
            mFormat = new DecimalFormat("###");
        }

        // data
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(Math.abs(value)) + "m";
        }

        // YAxis
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mFormat.format(Math.abs(value)) + "m";
        }
    }

    private void actionToggleValues() {
        List<IBarDataSet> sets = chart.getData().getDataSets();
        for (IBarDataSet iSet : sets) {
            BarDataSet set = (BarDataSet) iSet;
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleIcons() {
        List<IBarDataSet> sets = chart.getData().getDataSets();
        for (IBarDataSet iSet : sets) {
            BarDataSet set = (BarDataSet) iSet;
            set.setDrawIcons(!set.isDrawIconsEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleHighlight() {
        if (chart.getData() != null) {
            chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
            chart.invalidate();
        }
    }

    private void actionTogglePinch() {
        if (chart.isPinchZoomEnabled()) {
            chart.setPinchZoom(false);
        } else {
            chart.setPinchZoom(true);
        }
        chart.invalidate();
    }

    private void actionToggleAutoScaleMinMax() {
        chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
        chart.notifyDataSetChanged();
    }

    private void actionToggleBarBorders() {
        for (IBarDataSet set : chart.getData().getDataSets()) {
            ((BarDataSet) set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);
        }
        chart.invalidate();
    }

    private void animateX() {
        chart.animateX(3000);
    }

    private void animateY() {
        chart.animateY(3000);
    }

    private void animateXY() {
        chart.animateXY(3000, 3000);
    }
}
