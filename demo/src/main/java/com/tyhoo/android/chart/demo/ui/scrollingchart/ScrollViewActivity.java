package com.tyhoo.android.chart.demo.ui.scrollingchart;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.charts.BarChart;
import com.tyhoo.android.chart.library.components.XAxis;
import com.tyhoo.android.chart.library.data.BarData;
import com.tyhoo.android.chart.library.data.BarDataSet;
import com.tyhoo.android.chart.library.data.BarEntry;
import com.tyhoo.android.chart.library.utils.ColorTemplate;

import java.util.ArrayList;

public class ScrollViewActivity extends AppCompatActivity {

    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scrollview);

        setTitle("ScrollViewActivity");

        chart = findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chart.getAxisLeft().setDrawGridLines(false);

        chart.getLegend().setEnabled(false);

        setData(10);
        chart.setFitBars(true);
    }

    private void setData(int count) {

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * count) + 15;
            values.add(new BarEntry(i, (int) val));
        }

        BarDataSet set = new BarDataSet(values, "Data Set");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setDrawValues(false);

        BarData data = new BarData(set);

        chart.setData(data);
        chart.invalidate();
        chart.animateY(800);
    }
}
