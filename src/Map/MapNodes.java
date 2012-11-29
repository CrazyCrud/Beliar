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
    private static final int MAX_WEIGHT = 100000;
    
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
                node_mapNodes[x][y] = new Node(x, y, int_map[x][y]);
            }
        }
    }

    private void setEdges() {
        System.out.println("MapNodes: setEdges");
        int x, z;
        int mapLength = int_map.length;
        
        for(x = 0; x < mapLength; x++){
            for(z = 0; z < mapLength; z++){
                Node node = node_mapNodes[x][z];
                if((x - 1) > -1){
                    if(int_map[x - 1][z] != 1 || int_map[x - 1][z] != 11){
                        node.setEdge(node_mapNodes[x - 1][z], MAX_WEIGHT);
                    }else{
                        node.setEdge(node_mapNodes[x - 1][z], int_map[x - 1][z]);
                    }
                }
                if((x + 1) < mapLength){
                    if(int_map[x + 1][z] != 1 || int_map[x - 1][z] != 11){
                        node.setEdge(node_mapNodes[x + 1][z], MAX_WEIGHT);
                    }else{
                        node.setEdge(node_mapNodes[x + 1][z], int_map[x + 1][z]);
                    }          
                }
                if((z - 1) > -1){
                    if(int_map[x][z - 1] != 1 || int_map[x - 1][z] != 11){
                        node.setEdge(node_mapNodes[x][z - 1], MAX_WEIGHT);
                    }else{
                        node.setEdge(node_mapNodes[x][z - 1], int_map[x][z - 1]);
                    } 
                }
                if((z + 1) < mapLength){
                    if(int_map[x][z + 1] != 1 || int_map[x - 1][z] != 11){
                        node.setEdge(node_mapNodes[x][z + 1], MAX_WEIGHT);
                    }else{
                        node.setEdge(node_mapNodes[x][z + 1], int_map[x][z + 1]);
                    }    
                }
                /*
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
                 * */
            }
        }
    }
    
    protected void changeEdge(int x, int z, float weight){
        System.out.println("MapNodes: changeEdge");
        int mapLength = int_map.length;
        if(isNodeEmpty(x, z)){
            //node_mapNodes[x][z] = new Node(x, z);
        }
        if((x - 1) > -1){
            node_mapNodes[x - 1][z].changeEdge(node_mapNodes[x][z], weight);
        }
        if((x + 1) < mapLength){
            node_mapNodes[x + 1][z].changeEdge(node_mapNodes[x][z], weight);
        }
        if((z - 1) > -1){
            node_mapNodes[x][z - 1].changeEdge(node_mapNodes[x][z], weight);
        }
        if((z + 1) < mapLength){
            node_mapNodes[x][z + 1].changeEdge(node_mapNodes[x][z], weight);
        }
    }
    
    protected void setNodeTo(int x, int z, boolean covered) {
        node_mapNodes[x][z].setIsCovered(covered);
    }
    
    protected boolean isNodeCovered(int x, int z) {
        return node_mapNodes[x][z].isNodeCovered();
    }
    
    protected void changeType(int x, int z, int type){
        node_mapNodes[x][z].changeType(type);
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
