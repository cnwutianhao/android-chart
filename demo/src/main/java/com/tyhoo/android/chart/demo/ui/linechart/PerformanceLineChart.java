package com.tyhoo.android.chart.demo.ui.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.charts.LineChart;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.data.Entry;
import com.tyhoo.android.chart.library.data.LineData;
import com.tyhoo.android.chart.library.data.LineDataSet;

import java.util.ArrayList;

public class PerformanceLineChart extends AppCompatActivity implements OnSeekBarChangeListener {

    private LineChart chart;
    private SeekBar seekBarValues;
    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_performance_linechart);

        setTitle("PerformanceLineChart");

        tvCount = findViewById(R.id.tvValueCount);
        seekBarValues = findViewById(R.id.seekbarValues);
        seekBarValues.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setDrawGridBackground(false);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setDrawGridLines(true);
        chart.getXAxis().setDrawAxisLine(false);

        seekBarValues.setProgress(9000);

        // don't forget to refresh the drawing
        chart.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 3;
            values.add(new Entry(i * 0.001f, val));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "DataSet 1");

        set1.setColor(Color.BLACK);
        set1.setLineWidth(0.5f);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);
        set1.setMode(LineDataSet.Mode.LINEAR);
        set1.setDrawFilled(false);

        // create a data object with the data sets
        LineData data = new LineData(set1);

        // set data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int count = seekBarValues.getProgress() + 1000;
        tvCount.setText(String.valueOf(count));

        chart.resetTracking();

        setData(count, 500f);

        // redraw
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
