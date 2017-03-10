package com.jp.graphs.search;

import com.jp.graphs.core.GridVertex;
import com.jp.graphs.stereotypes.Search;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * A* implementation based on the wikipedia page
 * <p>
 * Created by JP on 3/9/2017.
 */
public class AStar implements Search {

    private static final Logger LOGGER = LoggerFactory.getLogger(AStar.class);

    private Queue<Vertex> openList = new PriorityBlockingQueue<>();
    private Queue<Vertex> closedList = new PriorityBlockingQueue<>();

    private static double calculateDistance(Vertex a, Vertex b) {
        // This exists because the wiki had a function, but we know that our grid distance is always 1
        return 1;
    }

    @Override
    public Collection getVisitedNodes() {
        return closedList;
    }

    @Override
    public Vertex search(Vertex start, Vertex target) {
        GridVertex gv = (GridVertex) start;
        GridVertex goal = (GridVertex) target;

        openList.offer(gv);

        gv.setgScore(0);
        gv.setfScore(gv.calculateHeuristic(goal));

        while (!openList.isEmpty()) {

            GridVertex cv = (GridVertex) openList.poll();

            if (cv.equals(goal)) {
                LOGGER.info("Found the end.");
                return cv;
            }


            closedList.add(cv);

            cv.getNeighbors().stream().filter(n -> !closedList.contains(n)).forEach(n -> {
                GridVertex cn = (GridVertex) n;
                double potentialG = cv.getgScore() + calculateDistance(cv, cn);

                // If we've seen this node before and the previously found gScore is better, then leave it alone
                if (openList.contains(cn) && potentialG >= cn.getgScore()) {
                    return;
                }

                if (openList.contains(cn) && potentialG < cn.getgScore()) {
                    // This path is better; need to update the values
                    cn.setParent(cv);
                    cn.setgScore(potentialG);
                    cn.setfScore(potentialG + cn.calculateHeuristic(goal));
                }

                // We've never seen this node before, so add it to the queue
                if (!openList.contains(cn)) {
                    cn.setParent(cv);
                    cn.setgScore(potentialG);
                    cn.setfScore(potentialG + cn.calculateHeuristic(goal));
                    openList.offer(cn);
                }

                // This is the current best path, so we set this node's parent and update its scores

            });


        }

        LOGGER.warn("How did i get here??");

        return null;

    }

}
