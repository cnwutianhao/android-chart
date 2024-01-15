package com.tyhoo.android.chart.demo.custom;

import com.tyhoo.android.chart.library.data.Entry;
import com.tyhoo.android.chart.library.formatter.IValueFormatter;
import com.tyhoo.android.chart.library.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements IValueFormatter {

    private final DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + " $";
    }
}
