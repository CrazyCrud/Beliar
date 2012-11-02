/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

/**
 *
 * @author andministrator
 */
public class MapModel {
    private static MapModel instance;
    private int [][] int_map;
    
    private MapModel(){
        // nothing to do here...
    }
    
    public static MapModel getInstance(){
        if(instance != null){
            return instance;
        }
        
        return instance = new MapModel();
    }
    
    public void setMap(int [][] newMap){
        System.out.println("MapModel: setMap()");
        int_map = newMap;
    }
    
    public int [][] getMap(){
        return int_map;
    }
}
