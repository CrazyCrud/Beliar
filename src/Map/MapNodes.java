/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Pathfinding.Node;
import Pathfinding.Edge;

/**
 *
 * @author andministrator
 */
public class MapNodes {
    private static MapNodes instance;
    private int [][] int_map;
    private Node [][] node_mapNodes;
    
    private MapNodes(){
        instance = new MapNodes();
    }
    
    public static MapNodes getInstance(){
        if(instance != null){
            return instance;
        }        
        return instance = new MapNodes();
    }
    
    public void getMap(){
        int_map = MapController.getMap();
    }
    
    public void createMapNodes(){
        if(int_map == null){
            return;
        }
        node_mapNodes = new Node[int_map.length][int_map.length];
        
        int y, x;
        int mapLength = int_map.length;
        
        for(y = 0; y < mapLength; y++){
            for(x = 0; x < mapLength; x++){
                Node node = new Node(x, y);
                Node nodeRight, nodeLeft, nodeOver, nodeUnder,
                        nodeRightOver, nodeLeftOver, nodeRightUnder, nodeLeftUnder;
                
                if((x + 1) < (mapLength + 1)){
                    if(isNodeEmpty(x + 1, y)){
                        nodeRight = new Node(x + 1, y);
                        node.setEdge(nodeRight, int_map[y][x + 1]);
                        node_mapNodes[y][x + 1] = nodeRight;
                    }
                    if((y + 1) < mapLength + 1){
                        if(isNodeEmpty(x, y + 1)){
                            nodeUnder = new Node(x, y + 1);
                            node.setEdge(nodeUnder, int_map[y + 1][x]);
                            node_mapNodes[y + 1][x] = nodeUnder;
                        }
                        if(isNodeEmpty(x + 1, y + 1)){
                            nodeRightUnder = new Node(x + 1, y + 1);
                            node.setEdge(nodeRightUnder, int_map[y + 1][x + 1]);
                            node_mapNodes[y + 1][x + 1] = nodeRightUnder;
                        }
                    }
                    if((y - 1) >= 0){
                        if(isNodeEmpty(x, y - 1)){
                            nodeOver = new Node(x, y - 1);
                            node.setEdge(nodeOver, int_map[y - 1][x]);
                            node_mapNodes[y - 1][x] = nodeOver;
                        }
                        if(isNodeEmpty( + 1, y - 1)){
                            nodeRightOver = new Node(x + 1, y - 1);
                            node.setEdge(nodeRightOver, int_map[y - 1][x + 1]);
                            node_mapNodes[y - 1][x + 1] = nodeRightOver;
                        } 
                    }
                }else{
                    if((y + 1) < mapLength + 1){
                        if(isNodeEmpty(x, y + 1)){
                            nodeUnder = new Node(x, y + 1);
                            node.setEdge(nodeUnder, int_map[y + 1][x]);
                            node_mapNodes[y + 1][x] = nodeUnder;
                        }
                    }
                    if((y - 1) >= 0){
                        if(isNodeEmpty(x, y - 1)){
                           nodeOver = new Node(x, y - 1);
                           node.setEdge(nodeOver, int_map[y - 1][x]);
                           node_mapNodes[y - 1][x] = nodeOver; 
                        }
                    }
                }
                
                if((x - 1) >= 0){
                    if(isNodeEmpty(x - 1, y)){
                        nodeLeft = new Node(x - 1, y);
                        node.setEdge(nodeLeft, int_map[y][x - 1]);
                        node_mapNodes[y][x - 1] = nodeLeft;
                    }
                    if((y + 1) < mapLength + 1){
                        if(isNodeEmpty(x - 1, y + 1)){
                            nodeLeftUnder = new Node(x - 1, y + 1);
                            node.setEdge(nodeLeftUnder, int_map[y + 1][x - 1]);
                            node_mapNodes[y + 1][x - 1] = nodeLeftUnder;
                        } 
                    }
                    if((y - 1) >= 0){
                        if(isNodeEmpty(x - 1, y - 1)){
                            nodeLeftOver = new Node(x - 1, y - 1);
                            node.setEdge(nodeLeftOver, int_map[y - 1][x - 1]);
                            node_mapNodes[y - 1][x - 1] = nodeLeftOver;
                        }
                    }
                }
                
                if(isNodeEmpty(x, y)){
                    node_mapNodes[y][x] = node;
                }
            }
        }
    }
    
    private boolean isNodeEmpty(int x, int y){
        return node_mapNodes[y][x] == null ? true : false;
    }
    
    public Node [][] getMapNodes(){
        return node_mapNodes;
    }
    
    public Node getNode(int x, int y){
        return node_mapNodes[y][x];
    }
}
