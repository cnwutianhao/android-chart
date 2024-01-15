package com.tyhoo.android.chart.demo.ui.barchart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.charts.BarChart;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.components.XAxis;
import com.tyhoo.android.chart.library.components.YAxis;
import com.tyhoo.android.chart.library.data.BarData;
import com.tyhoo.android.chart.library.data.BarDataSet;
import com.tyhoo.android.chart.library.data.BarEntry;
import com.tyhoo.android.chart.library.interfaces.datasets.IBarDataSet;
import com.tyhoo.android.chart.library.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivitySinus extends AppCompatActivity implements OnSeekBarChangeListener {

    private BarChart chart;
    private SeekBar seekBarX;
    private TextView tvX;

    private List<BarEntry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart_sinus);

        setTitle("BarChartActivitySinus");

        data = FileUtils.loadBarEntriesFromAssets(getAssets(), "othersine.txt");

        tvX = findViewById(R.id.tvValueCount);

        seekBarX = findViewById(R.id.seekbarValues);

        chart = findViewById(R.id.chart1);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        // chart.setDrawXLabels(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinimum(-2.5f);
        leftAxis.setAxisMaximum(2.5f);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(0.1f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(6, false);
        rightAxis.setAxisMinimum(-2.5f);
        rightAxis.setAxisMaximum(2.5f);
        rightAxis.setGranularity(0.1f);

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarX.setProgress(150); // set data

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        chart.animateXY(1500, 1500);
    }

    private void setData(int count) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            entries.add(data.get(i));
        }

        BarDataSet set;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(entries, "Sinus Function");
            set.setColor(Color.rgb(240, 120, 124));
        }

        BarData data = new BarData(set);
        data.setValueTextSize(10f);
//        data.setValueTypeface(tfLight);
        data.setDrawValues(false);
        data.setBarWidth(0.8f);

        chart.setData(data);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText(String.valueOf(seekBarX.getProgress()));

        setData(seekBarX.getProgress());
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void actionToggleValues() {
        for (IBarDataSet set : chart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
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
        chart.animateX(2000);
    }

    private void animateY() {
        chart.animateY(2000);
    }

    private void animateXY() {
        chart.animateXY(2000, 2000);
    }
}
