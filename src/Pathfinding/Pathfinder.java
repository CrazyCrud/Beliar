/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author andministrator
 */
public class Pathfinder {
    protected final TreeSet<Node> frontline;
    private final Set<Node> visited;
    private final Map<Node, Float> accumulatedWeight;
    
    public Pathfinder(){
        frontline = new TreeSet<Node>(new NodeComparator((this)));
        visited = new HashSet<Node>();
        accumulatedWeight = new HashMap<Node,Float>();
    }
    
    public List<Node> search(Node start, Filter<Node> target){
        this.frontline.add(start);
        
        while(!this.frontline.isEmpty()){
            Node found = this.step(target);
            
            if(found != null){
                return this.getPath(found);
            }
        } 
        return null;
    }
    
    private Node step(Filter<Node> target){
        Node head = frontline.pollFirst();
        
        if(head == null){
            return null;
        }
        
        this.visit(head);
        
        if(target.accept(head)){
            return head;
        }
        
        for(Edge edge: head.getEdges()){
            if(this.isVisited(edge.destination)){
                continue;
            }
            
            float last = this.getAccumulatedWeight(edge.destination);
            float current = this.getAccumulatedWeight(head) + edge.weight;
            
            if(last != 0.0f && last < current){
                continue;
            }
            
            this.frontline.remove(edge.destination);
            this.setAccumulatedWeight(edge.destination, current);
            this.frontline.add(edge.destination);
        }
        
        return null;
    }

    private List<Node> getPath(Node node){
        LinkedList<Node> path = new LinkedList<Node>();
        
        path.addFirst(node);
        
        do
        {
            path.addFirst(node = this.getPreviousNode(node));
        }
        while(this.getAccumulatedWeight(node) != 0.0f);
        
        return path;
    }
    
    private Node getPreviousNode(Node node){
        Edge best = null;
        
        for(Edge edge: node.getBacktracks()){
            if(this.isVisited(edge.source)){
                if(best == null || this.getAccumulatedWeight(edge.source) < 
                        this.getAccumulatedWeight(best.source)){
                    best = edge;
                }
            }
        }
        if(best == null){
            throw new IllegalStateException();
        }
        return best.source;
    }
    
    private void visit(Node node){
        visited.add(node);
    }
    
    private boolean isVisited(Node node){
        return this.visited.contains(node);
    }
    
    private void setAccumulatedWeight(Node node, float weight){
        accumulatedWeight.put(node, Float.valueOf(weight));
    }
    
    protected float getAccumulatedWeight(Node node){
        Float weight = accumulatedWeight.get(node);
        return weight == null ? 0.0f : weight.floatValue();
    }
}
