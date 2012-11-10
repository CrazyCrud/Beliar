/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author andministrator
 */
public class Slave extends Unit{
    private Node node_slave;
    private Geometry geo_slave;
    
    public Slave(int posX, int posY){
        super(GameObjectValues.HEALTH_VALUE_SLAVE, posX, posY, GameObjectValues.SPEED_VALUE_SLAVE);
        System.out.println("Slave: Constructor");
        createSlave(new Vector3f(posX, GameObjectValues.Z_POSITION, posY));
    }
    
    private void createSlave(Vector3f spwanLocation){
        setUpSpatial(spwanLocation);
        setUpControllers();
    }
    
    private void setUpSpatial(Vector3f spwanLocation){
        //geo_slave = (Geometry) assetManager.loadModel("Models/slave/slave.mesh.xml");
        //geo_slave.setLocalTranslation(spwanLocation);
        node_slave = (Node) assetManager.loadModel("Models/slave/slave.mesh.xml");
        node_slave.setLocalTranslation(spwanLocation);
        //node_slave.attachChild(geo_slave);
        spatial = (Spatial) node_slave;  
    }
    
    protected void setUpControllers(){
        addControllers();
        initControllerValues();
    }
    
    @Override
    protected void addControllers(){
        super.addControllers();
        node_slave.addControl(new SlaveAnimationControl());
        node_slave.addControl(new SlaveCharacterControl());
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
    }
    
    @Override
    protected void removeGameObject(){
        super.removeGameObject();
        node_slave.removeFromParent();
        node_slave.removeControl(SlaveAnimationControl.class);
        node_slave.removeControl(SlaveCharacterControl.class);
    }
}