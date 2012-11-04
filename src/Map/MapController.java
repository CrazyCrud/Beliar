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
    
    public static Node getNode(int x, int y){
        return MapNodes.getInstance().getNode(x, y);
    }
}
