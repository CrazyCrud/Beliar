/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.scene.Node;

/**
 *
 * @author andministrator
 */
public abstract class Building extends GameObject{

    protected Node node_building;
    protected int int_type, int_size;
    
    public Building(int healthPoints, int posX, int posZ){
        super(healthPoints, posX, posZ);
        System.out.println("Building: set at " + posX + ", " + posZ);
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
        node_building.setUserData(GameObjectValues.BUILDING_TYPE, int_type);
        node_building.setUserData(GameObjectValues.BUILDING_SIZE, int_size);
    }

    public int getType() {
        return spatial.getUserData(GameObjectValues.BUILDING_TYPE);
    }

    public void setType(int int_type) {
        this.int_type = int_type;
    }
}
