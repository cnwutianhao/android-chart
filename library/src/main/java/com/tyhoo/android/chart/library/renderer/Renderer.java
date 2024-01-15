package com.tyhoo.android.chart.library.renderer;

import com.tyhoo.android.chart.library.utils.ViewPortHandler;

/**
 * Abstract baseclass of all Renderers.
 *
 * @author Philipp Jahoda
 */
public abstract class Renderer {

    /**
     * the component that handles the drawing area of the chart and it's offsets
     */
    protected ViewPortHandler mViewPortHandler;

    public Renderer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }
}
