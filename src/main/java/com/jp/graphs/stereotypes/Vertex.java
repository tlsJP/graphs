package com.jp.graphs.stereotypes;

import java.util.Collection;

/**
 * Created by JP on 3/7/2017.
 */
public interface Vertex {

    Vertex getParent();

    Collection getNeighbors();

    String printNeighbors();

    void setDataElement(Object t);

    Object getDataElement();

    void connect(Vertex v);

    void disconnect(Vertex v);
}
