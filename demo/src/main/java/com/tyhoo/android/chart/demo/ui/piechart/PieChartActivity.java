package com.tyhoo.android.chart.demo.ui.piechart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tyhoo.android.chart.demo.R;
import com.tyhoo.android.chart.library.animation.Easing;
import com.tyhoo.android.chart.library.charts.PieChart;
import com.tyhoo.android.chart.library.components.Legend;
import com.tyhoo.android.chart.library.data.Entry;
import com.tyhoo.android.chart.library.data.PieData;
import com.tyhoo.android.chart.library.data.PieDataSet;
import com.tyhoo.android.chart.library.data.PieEntry;
import com.tyhoo.android.chart.library.formatter.PercentFormatter;
import com.tyhoo.android.chart.library.highlight.Highlight;
import com.tyhoo.android.chart.library.interfaces.datasets.IDataSet;
import com.tyhoo.android.chart.library.listener.OnChartValueSelectedListener;
import com.tyhoo.android.chart.library.utils.ColorTemplate;
import com.tyhoo.android.chart.library.utils.MPPointF;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity implements OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private PieChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);

        setTitle("PieChartActivity");

        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarY = findViewById(R.id.seekBar2);

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

//        chart.setCenterTextTypeface(tfLight);
        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this);

        seekBarX.setProgress(4);
        seekBarY.setProgress(10);

        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
//        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        setData(seekBarX.getProgress(), seekBarY.getProgress());
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    protected final String[] parties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private void actionToggleValues() {
        for (IDataSet<?> set : chart.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleIcons() {
        for (IDataSet<?> set : chart.getData().getDataSets()) {
            set.setDrawIcons(!set.isDrawIconsEnabled());
        }
        chart.invalidate();
    }

    private void actionToggleHole() {
        if (chart.isDrawHoleEnabled()) {
            chart.setDrawHoleEnabled(false);
        } else {
            chart.setDrawHoleEnabled(true);
        }
        chart.invalidate();
    }

    private void actionToggleMinAngles() {
        if (chart.getMinAngleForSlices() == 0f) {
            chart.setMinAngleForSlices(36f);
        } else {
            chart.setMinAngleForSlices(0f);
        }
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    private void actionToggleCurvedSlices() {
        boolean toSet = !chart.isDrawRoundedSlicesEnabled() || !chart.isDrawHoleEnabled();
        chart.setDrawRoundedSlices(toSet);
        if (toSet && !chart.isDrawHoleEnabled()) {
            chart.setDrawHoleEnabled(true);
        }
        if (toSet && chart.isDrawSlicesUnderHoleEnabled()) {
            chart.setDrawSlicesUnderHole(false);
        }
        chart.invalidate();
    }

    private void actionDrawCenter() {
        if (chart.isDrawCenterTextEnabled())
            chart.setDrawCenterText(false);
        else
            chart.setDrawCenterText(true);
        chart.invalidate();
    }

    private void actionToggleXValues() {
        chart.setDrawEntryLabels(!chart.isDrawEntryLabelsEnabled());
        chart.invalidate();
    }

    private void actionTogglePercent() {
        chart.setUsePercentValues(!chart.isUsePercentValuesEnabled());
        chart.invalidate();
    }

    private void actionToggleSpin() {
        chart.spin(1000, chart.getRotationAngle(), chart.getRotationAngle() + 360, Easing.EaseInOutCubic);
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