/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author andministrator
 */
public class BuildingController {
    public static Node buildProductionBuilding(int posX, int posZ, int type, 
            int buildingSize){
        return BuildingModel.getInstance().buildProductionBuilding(posX, posZ, 
                type, buildingSize);
    }
    
    public static ArrayList<ProductionBuilding> getProductionBuildings(){
        return BuildingModel.getInstance().getProductionBuildings();
    }
}
