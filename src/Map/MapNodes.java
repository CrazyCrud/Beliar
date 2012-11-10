/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Pathfinding.Node;

/**
 *
 * @author andministrator
 */
public class MapNodes {
    private static MapNodes instance;
    private int [][] int_map;
    private Node [][] node_mapNodes;
    
    private MapNodes(){
        // nothing to do here...
    }
    
    public static MapNodes getInstance(){
        if(instance != null){
            return instance;
        }        
        return instance = new MapNodes();
    }

    public void initializeMapNodes(){
        System.out.println("MapNodes: initializeMapNodes");

        initializeMap();
        if(int_map == null){
            return;
        }
        createMapNodes();
        setEdges();
    }
    
    private void initializeMap(){
        int_map = MapController.getMap();
    }
    
    private void createMapNodes() {
        System.out.println("MapNodes: createMapNodes");
        node_mapNodes = new Node[int_map.length][int_map.length];
        
        int x, y;
        int mapLength = int_map.length;
        
        for(x = 0; x < mapLength; x++){
            for(y = 0; y < mapLength; y++){
                node_mapNodes[x][y] = new Node(x, y);
            }
        }
    }

    private void setEdges() {
        System.out.println("MapNodes: setEdges");
        int x, y;
        int mapLength = int_map.length;
        
        for(x = 0; x < mapLength; x++){
            for(y = 0; y < mapLength; y++){
                Node node = node_mapNodes[x][y];
                if((x - 1) > -1){
                    node.setEdge(node_mapNodes[x - 1][y], int_map[x - 1][y] + 1);
                }
                if((x + 1) < mapLength){
                    node.setEdge(node_mapNodes[x + 1][y], int_map[x + 1][y] + 1);
                }
                if((y - 1) > -1){
                    node.setEdge(node_mapNodes[x][y - 1], int_map[x][y - 1] + 1);
                }
                if((y + 1) < mapLength){
                    node.setEdge(node_mapNodes[x][y + 1], int_map[x][y + 1] + 1);
                }
                if((x - 1) > -1 && (y - 1) > -1){
                    node.setEdge(node_mapNodes[x - 1][y - 1], int_map[x - 1][y - 1] + 1);
                }
                if((x + 1) < mapLength && (y - 1) > -1){
                    node.setEdge(node_mapNodes[x + 1][y - 1], int_map[x + 1][y - 1] + 1);
                }
                if((x - 1) > -1 && (y + 1) < mapLength){
                    node.setEdge(node_mapNodes[x - 1][y + 1], int_map[x - 1][y + 1] + 1);
                }
                if((x + 1) < mapLength && (y + 1) < mapLength){
                    node.setEdge(node_mapNodes[x + 1][y + 1], int_map[x + 1][y + 1] + 1);
                }
            }
        }
    }
    
    private boolean isNodeEmpty(int x, int y){
        return node_mapNodes[x][y] == null ? true : false;
    }
    
    public Node [][] getMapNodes(){
        return node_mapNodes;
    }
    
    public Node getNode(int x, int y){
        if(x > node_mapNodes.length - 1){
            x = node_mapNodes.length - 1;
        }else if(x < 0){
            x = 0;
        }
        if(y > node_mapNodes.length - 1){
            y = node_mapNodes.length - 1;
        }else if(y < 0){
            y = 0;
        }
        return node_mapNodes[x][y];
    }
}
