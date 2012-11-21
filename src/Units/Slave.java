/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author andministrator
 */
public class Slave extends Unit{
    private Node node_slave;
    
    public Slave(int posX, int posZ){
        super(GameObjectValues.HEALTH_VALUE_SLAVE, posX, posZ, GameObjectValues.SPEED_VALUE_SLAVE);
        System.out.println("Slave: Constructor at " + posX + ", " + posZ);
        createSlave(new Vector3f(posX, GameObjectValues.Y_POSITION_UNITS, posZ));
    }
    
    private void createSlave(Vector3f spwanLocation){
        setUpSpatial(spwanLocation);
        setUpControllers();
        positionSlave(spwanLocation);
    }
    
    private void setUpSpatial(Vector3f spwanLocation){
        node_slave = (Node) assetManager.loadModel("Models/slave/slave.mesh.xml");
        node_slave.setMaterial(assetManager.loadMaterial("Materials/slave.j3m"));
        //node_slave.setLocalTranslation(spwanLocation);
        spatial = (Spatial) node_slave;  
    }
    
    protected void setUpControllers(){
        addControllers();
        initControllerValues();
    }
    
    @Override
    protected void addControllers(){
        super.addControllers();
        node_slave.addControl(new SlaveCharacterControl());
        node_slave.addControl(new SlaveAnimationControl());
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
    }
    
    private void positionSlave(Vector3f spwanLocation){
        spatial.getControl(GameObjectControl.class).setLocation(spwanLocation);
    }
    
    @Override
    protected void removeGameObject(){
        super.removeGameObject();
        node_slave.removeFromParent();
        node_slave.removeControl(SlaveAnimationControl.class);
        node_slave.removeControl(SlaveCharacterControl.class);
    }
}