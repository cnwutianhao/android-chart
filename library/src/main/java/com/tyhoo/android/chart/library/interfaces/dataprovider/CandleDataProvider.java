package com.tyhoo.android.chart.library.interfaces.dataprovider;

import com.tyhoo.android.chart.library.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
