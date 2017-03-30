package com.jp.graphs.stereotypes;

import java.util.Collection;

/**
 * Created by JP on 3/8/2017.
 */
public interface Search {

    Vertex search(Vertex start, Vertex target);

    Collection getVisitedNodes();

    Collection getUncheckedNodes();
}
