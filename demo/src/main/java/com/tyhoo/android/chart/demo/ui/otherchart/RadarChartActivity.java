package com.tyhoo.android.chart.demo.ui.otherchart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.demo.custom.RadarMarkerView;
import com.tyhoo.android.chart.library.animation.Easing;
import com.tyhoo.android.chart.library.charts.RadarChart;
import com.tyhoo.android.chart.library.components.AxisBase;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.components.MarkerView;
import com.tyhoo.android.chart.library.components.XAxis;
import com.tyhoo.android.chart.library.components.YAxis;
import com.tyhoo.android.chart.library.data.RadarData;
import com.tyhoo.android.chart.library.data.RadarDataSet;
import com.tyhoo.android.chart.library.data.RadarEntry;
import com.tyhoo.android.chart.library.formatter.IAxisValueFormatter;
import com.tyhoo.android.chart.library.interfaces.datasets.IDataSet;
import com.tyhoo.android.chart.library.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    private RadarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radarchart);

        setTitle("RadarChartActivity");

        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
//        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private final String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
//        yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setTypeface(tfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    private void setData() {

        float mul = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mul) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mul) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
//        data.setValueTypeface(tfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }

    private void actionToggleValues() {
        for (IDataSet<?> set : chart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());

        chart.invalidate();
    }

    private void actionToggleHighlight() {
        if (chart.getData() != null) {
            chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
            chart.invalidate();
        }
    }

    private void actionToggleRotate() {
        if (chart.isRotationEnabled())
            chart.setRotationEnabled(false);
        else
            chart.setRotationEnabled(true);
        chart.invalidate();
    }

    private void actionToggleFilled() {
        ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) chart.getData()
                .getDataSets();

        for (IRadarDataSet set : sets) {
            if (set.isDrawFilledEnabled())
                set.setDrawFilled(false);
            else
                set.setDrawFilled(true);
        }
        chart.invalidate();
    }

    private void actionToggleHighlightCircle() {
        ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) chart.getData()
                .getDataSets();

        for (IRadarDataSet set : sets) {
            set.setDrawHighlightCircleEnabled(!set.isDrawHighlightCircleEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleXLabels() {
        chart.getXAxis().setEnabled(!chart.getXAxis().isEnabled());
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    private void actionToggleYLabels() {
        chart.getYAxis().setEnabled(!chart.getYAxis().isEnabled());
        chart.invalidate();
    }

    private void actionToggleSpin() {
        chart.spin(2000, chart.getRotationAngle(), chart.getRotationAngle() + 360, Easing.EaseInOutCubic);
    }

    private void animateX() {
        chart.animateX(1400);
    }

    private void animateY() {
        chart.animateY(1400);
    }

    private void animateXY() {
        chart.animateXY(1400, 1400);
    }
}
