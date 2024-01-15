package com.tyhoo.android.chart.demo.ui.barchart;

import android.graphics.RectF;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.charts.HorizontalBarChart;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.components.XAxis;
import com.tyhoo.android.chart.library.components.YAxis;
import com.tyhoo.android.chart.library.data.BarData;
import com.tyhoo.android.chart.library.data.BarDataSet;
import com.tyhoo.android.chart.library.data.BarEntry;
import com.tyhoo.android.chart.library.data.Entry;
import com.tyhoo.android.chart.library.highlight.Highlight;
import com.tyhoo.android.chart.library.interfaces.datasets.IBarDataSet;
import com.tyhoo.android.chart.library.listener.OnChartValueSelectedListener;
import com.tyhoo.android.chart.library.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class HorizontalBarChartActivity extends AppCompatActivity implements OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private HorizontalBarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_horizontalbarchart);

        setTitle("HorizontalBarChartActivity");

        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarY = findViewById(R.id.seekBar2);

        seekBarY.setOnSeekBarChangeListener(this);
        seekBarX.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);
        // chart.setHighlightEnabled(false);

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

        chart.setDrawGridBackground(false);

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(tfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);

        YAxis yl = chart.getAxisLeft();
//        yl.setTypeface(tfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
//        yr.setTypeface(tfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        chart.setFitBars(true);
        chart.animateY(2500);

        // setting data
        seekBarY.setProgress(50);
        seekBarX.setProgress(12);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void setData(int count, float range) {

        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            values.add(new BarEntry(i * spaceForBar, val,
                    getResources().getDrawable(R.drawable.star)));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(tfLight);
            data.setBarWidth(barWidth);
            chart.setData(data);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        setData(seekBarX.getProgress(), seekBarY.getProgress());
        chart.setFitBars(true);
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private final RectF mOnValueSelectedRectF = new RectF();

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        chart.getBarBounds((BarEntry) e, bounds);

        MPPointF position = chart.getPosition(e, chart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency());

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {
    }

    private void actionToggleValues() {
        List<IBarDataSet> sets = chart.getData().getDataSets();
        for (IBarDataSet iSet : sets) {
            iSet.setDrawValues(!iSet.isDrawValuesEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleIcons() {
        List<IBarDataSet> sets = chart.getData().getDataSets();
        for (IBarDataSet iSet : sets) {
            iSet.setDrawIcons(!iSet.isDrawIconsEnabled());
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
        chart.animateX(2000);
    }

    private void animateY() {
        chart.animateY(2000);
    }

    private void animateXY() {
        chart.animateXY(2000, 2000);
    }
}
