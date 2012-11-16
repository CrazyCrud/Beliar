/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

/**
 *
 * @author andministrator
 */
public class Edge {
    
    protected final Node source, destination;
    protected float weight;
    
    public Edge(Node source, Node destination, float weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        
        //source.addEdge(this);
        //destination.addBacktrack(this);
    }
}
