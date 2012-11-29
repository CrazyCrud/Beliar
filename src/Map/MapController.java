/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;
import Pathfinding.Node;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
/**
 *
 * @author andministrator
 */
public final class MapController {
    private MapController(){
        throw new AssertionError();
    }
    
    public static int [][] getMap(){
       return MapModel.getInstance().getMap();
    }
    
    public static void setMap(int [][] newMap){
        System.out.println("MapController: setMap()");
        MapModel.getInstance().setMap(newMap);
    }
    
    public static void setMapNodes(){
        System.out.println("MapController: setMapNodes()");
        MapNodes.getInstance().initializeMapNodes();
    }
    
    public static Node getNode(int x, int z){
        return MapNodes.getInstance().getNode(x, z);
    }
    
    public static void setNodeTo(int x, int z, boolean isCovered){
        MapNodes.getInstance().setNodeTo(x, z, isCovered);
    }
    
    public static boolean isNodeCovered(int x, int z){
        return  MapNodes.getInstance().isNodeCovered(x, z);
    }
    
    public static void setHallwayAt(int xPos, int zPos){
        MapNodes.getInstance().changeEdge(xPos, zPos, 1.0f);
        MapNodes.getInstance().changeType(xPos, zPos, 1);
    }
    
    public static void setBuilding(int xPos, int zPos){
        MapNodes.getInstance().changeEdge(xPos, zPos, 10000.0f);
        MapNodes.getInstance().changeType(xPos, zPos, 0);
    }
    
    public static Vector3f getSoulAbyssPosition(){
        return new Vector3f(5.0f, 0.0f ,20.0f);
    }
}
