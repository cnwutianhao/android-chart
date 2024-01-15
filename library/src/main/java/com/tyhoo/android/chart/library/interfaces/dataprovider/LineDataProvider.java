package com.tyhoo.android.chart.library.interfaces.dataprovider;

import com.tyhoo.android.chart.library.components.YAxis;
import com.tyhoo.android.chart.library.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
