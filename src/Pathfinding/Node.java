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
    
    private boolean isCovered;
    protected final int x, y;
    protected int type;
    private List<Edge> edges;
    private List<Edge> backtracks;
    
    public Node(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.isCovered = false;
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
    
    public void changeType(int type){
        this.type = type;
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
    
    public boolean isNodeCovered() {
        return isCovered;
    }

    public void setIsCovered(boolean isCovered) {
        this.isCovered = isCovered;
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
