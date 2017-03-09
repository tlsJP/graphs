package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;

/**
 * Created by JP on 3/8/2017.
 */
public interface GraphFactory {

    Graph build(int height, int width);
}
