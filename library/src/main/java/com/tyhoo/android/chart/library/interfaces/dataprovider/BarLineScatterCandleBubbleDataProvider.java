package com.tyhoo.android.chart.library.interfaces.dataprovider;

import com.tyhoo.android.chart.library.components.YAxis.AxisDependency;
import com.tyhoo.android.chart.library.data.BarLineScatterCandleBubbleData;
import com.tyhoo.android.chart.library.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);

    boolean isInverted(AxisDependency axis);

    float getLowestVisibleX();

    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
