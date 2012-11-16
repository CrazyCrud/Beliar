/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andministrator
 */
public class Node {
    
    protected final int x, y;
    private List<Edge> edges;
    private List<Edge> backtracks;
    
    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<Edge>();
        this.backtracks = new ArrayList<Edge>();
    }
    
    protected void addEdge(Edge edge){
        edges.add(edge);
    }

    protected void addBacktrack(Edge edge){
        backtracks.add(edge);
    }
    
    protected List<Edge> getEdges(){
        return edges;
    }
    
    protected List<Edge> getBacktracks(){
        return backtracks;
    }
    
    public void setEdge(Node destination, float weight){
        Edge edge = new Edge(this, destination, weight);
        edges.add(edge);
        destination.addBacktrack(edge);
    }
    
    public void changeEdge(Node destination, float newWeight){
        Edge edgeToChange = null;
        for(Edge edge: edges){
            if(destination == edge.destination){
                edgeToChange = edge;
                break;
            }
        }
        if(edgeToChange == null){
            return;
        }
        edgeToChange.weight = newWeight;
    }
    
    public int getXPos(){
        return x;
    }
    
    public int getYPos(){
        return y;
    }
    
    public int getNumberOfEdges(){
        return edges == null? 0 : edges.size();
    }
}
