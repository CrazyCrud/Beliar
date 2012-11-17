/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Map.MapController;
import Pathfinding.Pathfinder;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author andministrator
 */
public class BuildingModel {
    private static BuildingModel buildingModel;
    private ArrayList<ProductionBuilding> list_prodBuildings;
    private Node node_productionBuildings, node_buildings, node_traps;
    
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
        node_buildings = new Node("Buildings");
        node_productionBuildings = new Node("ProductionBuildings");
        node_traps = new Node("Traps");
    }
    
    protected Node buildProductionBuilding(Node slave, int posX, int posZ, int type, 
            int buildingSize){
        if(isBuildingAccessible(slave, posX, posZ)){
            ProductionBuilding prodBuilding  = new ProductionBuilding(posX, posZ, 
                type, buildingSize);
            list_prodBuildings.add(prodBuilding);
            slave.getControl(SlaveCharacterControl.class).build((Node)prodBuilding.getSpatial());
            return (Node) prodBuilding.getSpatial();
        }
        else{
            return null;
        }
    }
    
    protected ArrayList<ProductionBuilding> getProductionBuildings(){
        return list_prodBuildings;
    }

    private boolean isBuildingAccessible(Node slave, int x, int z) {
        if(slave.getControl(WalkControl.class).findPath(x, z)){
            //slave.getControl(SlaveCharacterControl.class).build();
            return true;
        }else{
            return false;
        }
    }
}
