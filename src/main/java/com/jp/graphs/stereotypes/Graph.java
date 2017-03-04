package com.jp.graphs.stereotypes;

/**
 * Interface for building a graph and possibly answering some questions about the graph itself
 * <p>
 * Created by JP on 3/3/2017.
 */
public interface Graph {

    boolean add(Vertex vertex);

    boolean connect(Vertex i, Vertex j);

    boolean disconnect(Vertex i, Vertex j);

    boolean isAdjacent(Vertex i, Vertex j);

    boolean remove(Vertex vertex);


}
