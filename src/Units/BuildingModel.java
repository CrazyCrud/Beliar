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
    
    protected Node buildProductionBuilding(int posX, int posZ, int type, 
            int buildingSize){
        ProductionBuilding prodBuilding = new ProductionBuilding(posX, posZ, 
                type, buildingSize);
        list_prodBuildings.add(prodBuilding);
        return (Node) prodBuilding.getSpatial();
    }
    
    protected ArrayList<ProductionBuilding> getProductionBuildings(){
        return list_prodBuildings;
    }
}
