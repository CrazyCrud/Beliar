/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import java.util.Comparator;

/**
 *
 * @author andministrator
 */
public class NodeComparator implements Comparator<Node>{

    private final Pathfinder pathFinder;
    
    public NodeComparator(Pathfinder pathFinder){
        this.pathFinder = pathFinder;
    }
    
    @Override
    public int compare(Node o1, Node o2) {
        if(o1 == o2){
            return 0;
        }
        
        float weightO1 = pathFinder.getAccumulatedWeight(o1);
        float weightO2 = pathFinder.getAccumulatedWeight(o2);
        
        return weightO1 < weightO2 ? -1 : +1;
    }
    
}
