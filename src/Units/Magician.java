/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author andministrator
 */
public class Magician extends Warrior{

    public Magician(int posX, int posZ){
        super(GameObjectValues.HEALTH_VALUE_SLAVE, posX, posZ, GameObjectValues.SPEED_VALUE_SLAVE, 
                GameObjectValues.ATTACK_DAMAGE_MELEE, GameObjectValues.MELEE);
        createMagician(new Vector3f(posX, GameObjectValues.Y_POSITION_UNITS, posZ));
    }
    
    private void createMagician(Vector3f spwanLocation){
        setUpSpatial();
        setUpControllers();
        positionWarrior(spwanLocation);
    }
    
    private void setUpSpatial() {
        node_warrior = (Node) assetManager.loadModel("Models/warrior/warrior.mesh.j3o");
        node_warrior.setMaterial(assetManager.loadMaterial("Materials/warrior.j3m"));
    }
    
    @Override
    protected void setUpControllers() {
        addControllers();
        initControllerValues();
    }
    
    @Override
    protected void addControllers(){
        super.addControllers();
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
    }
}
