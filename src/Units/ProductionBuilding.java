/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import beliar.GameContainer;
import com.jme3.math.Vector3f;
/**
 *
 * @author andministrator
 */
public class ProductionBuilding extends Building {

    private Node node_marker;
    public ProductionBuilding(int posX, int posZ, int type, int buildingSize){
        super(GameObjectValues.HEALTH_VALUE_PRODUCTIONBUILDING, posX, posZ);
        
        System.out.println("ProductionBuilding: Constructor at " + posX + ", " + posZ);
        
        this.int_type = type;
        this.int_size = buildingSize; 
        
        createBuilding();
    }
    
    private void createBuilding(){
        setUpSpatial();
        setUpControllers();
    }
    
    private void setUpSpatial(){
        switch(int_type){
            case GameContainer.ADAM_BUILDING:
                node_building = (Node) assetManager.loadModel("Models/AdamHall/adamBuilding_" + int_size + 
                        "/adamBuilding_" + int_size + ".j3o");
                break;
            case GameContainer.KYTHOS_BUILDING:
                node_building = (Node) assetManager.loadModel("Models/kythosBuilding_" + int_size + 
                        "/kythosBuilding_" + int_size + ".j3o");
                break;
            case GameContainer.MARA_BUILDING:
                node_building = (Node) assetManager.loadModel("Models/maraBuilding_" + int_size + 
                        "/maraBuilding_" + int_size + ".mesh.j3o");
                break;
        }
        node_marker = (Node) assetManager.loadModel("Models/sleepingZ/sleepingZ.j3o");
        node_marker.setName("marker");
        node_building.setLocalTranslation(new Vector3f(int_posX, GameObjectValues.Y_POSITION_BUILDINGS, int_posZ));
        //node_building.attachChild(node_marker);
        spatial = (Spatial) node_building;  
    }
    
    @Override
    protected void setUpControllers() {
        addControllers();
        initControllerValues();
    }
    
    @Override
    protected void addControllers(){
        super.addControllers();
        spatial.addControl(new ProductionBuildingControl());
        spatial.addControl(new BuildingAnimationControl());
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
    }
    
    public boolean hasGoodies()
    {
        return spatial.getControl(ProductionBuildingControl.class).hasGoodies();
    }
    
    public int getGoods() {
        return spatial.getControl(ProductionBuildingControl.class).getGoods();
    }
    
    public int getSize()
    {
        return spatial.getUserData(GameObjectValues.BUILDING_SIZE);
    }
    
    protected Node getMarker(){
        return node_marker;
    }
}
