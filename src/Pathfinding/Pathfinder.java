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
    protected final TreeSet<Node> set_frontline;
    private final Set<Node> set_visited;
    private final Map<Node, Float> map_accumulatedWeight;
    
    public Pathfinder(){
        set_frontline = new TreeSet<Node>(new NodeComparator((this)));
        set_visited = new HashSet<Node>();
        map_accumulatedWeight = new HashMap<Node,Float>();
    }
    
    public List<Node> search(Node start, Node target){
        reset();
        
        this.set_frontline.add(start);
        
        while(!this.set_frontline.isEmpty()){
            Node found = this.step(target);
            
            if(found != null){
                System.out.println("Node found: " + found.getXPos() + ", " + found.getYPos());
                return this.getPath(found);
            }
        } 
        return null;
    }
    
    private Node step(Node target){
        Node head = set_frontline.pollFirst();
        
        if(head == null){
            return null;
        }
        
        System.out.println("Current node: " + head.getXPos() + ", " + head.getYPos());
        
        this.visit(head);
        
        if(head == target){
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
            
            this.set_frontline.remove(edge.destination);
            this.setAccumulatedWeight(edge.destination, current);
            this.set_frontline.add(edge.destination);
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
        set_visited.add(node);
    }
    
    private boolean isVisited(Node node){
        return this.set_visited.contains(node);
    }
    
    private void setAccumulatedWeight(Node node, float weight){
        map_accumulatedWeight.put(node, Float.valueOf(weight));
    }
    
    protected float getAccumulatedWeight(Node node){
        Float weight = map_accumulatedWeight.get(node);
        return weight == null ? 0.0f : weight.floatValue();
    }
    
    private void reset(){
        set_frontline.clear();
        set_visited.clear();
        map_accumulatedWeight.clear();
    }
}
