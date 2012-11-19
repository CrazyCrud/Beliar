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
public class BuildingModel {
    private static BuildingModel buildingModel;
    private ArrayList<ProductionBuilding> list_prodBuildings;
    
    private BuildingModel(){
        initValues();
        initBuildingNodes();
    }
    
    public static BuildingModel getInstance(){
        if(buildingModel == null){
            buildingModel = new BuildingModel();
        }
        return buildingModel;
    }
    
    private void initValues(){
        this.list_prodBuildings = new ArrayList<ProductionBuilding>();
    }
    
    private void initBuildingNodes(){
        
    }
    
    protected Node buildProductionBuilding(Node slave, int posX, int posZ, int type, 
            int buildingSize){
        ProductionBuilding prodBuilding  = new ProductionBuilding(posX, posZ, 
            type, buildingSize);
        //list_prodBuildings.add(prodBuilding);
        slave.getControl(SlaveCharacterControl.class).build((Node)prodBuilding.getSpatial());
        return (Node) prodBuilding.getSpatial();
    }
    
    protected ArrayList<ProductionBuilding> getProductionBuildings(){
        return list_prodBuildings;
    }
}
