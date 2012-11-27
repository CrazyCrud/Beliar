/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Map.MapController;
import com.jme3.audio.AudioNode;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author andministrator
 */
public class Slave extends Unit{
    private Node node_slave;
    private AudioNode node_build1, node_build2, node_notReachable;
    public Slave(int posX, int posZ){
        super(GameObjectValues.HEALTH_VALUE_SLAVE, posX, posZ, GameObjectValues.SPEED_VALUE_SLAVE);
        System.out.println("Slave: Constructor at " + posX + ", " + posZ);
        createSlave(new Vector3f(posX, GameObjectValues.Y_POSITION_UNITS, posZ));
    }
    
    private void createSlave(Vector3f spwanLocation){
        setUpSpatial();
        setUpControllers();
        positionSlave(spwanLocation);
        setUpAudio();
    }
    
    private void setUpSpatial(){
        node_slave = (Node) assetManager.loadModel("Models/slave/slave.j3o");
        node_slave.setMaterial(assetManager.loadMaterial("Materials/slave.j3m"));
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

    protected void die() {
        MapController.setNodeTo(spatial.getControl(GameObjectControl.class).getPosX(), 
                spatial.getControl(GameObjectControl.class).getPosZ(), false);
        spatial.getControl(GameObjectControl.class).setHealth(0);
    }

    private void setUpAudio() {
        node_build1 = new AudioNode(assetManager, "Sounds/sounds/GrabenGrabenGraben.ogg");
        node_build2 = new AudioNode(assetManager, "Sounds/sounds/WirdErledigt.ogg");
        node_notReachable = new AudioNode(assetManager, "Sounds/sounds/DortKommeIchNichtHin.ogg");
        node_slave.attachChild(node_build1);
        node_slave.attachChild(node_build2);
        node_slave.attachChild(node_notReachable);
    }
    
    protected void playNotAccessible(){
        node_notReachable.play();
    }
    
    protected void playBuild(){
        switch((int)Math.round(Math.random() * 2.0f)){
            case 0:
                node_build1.play();
                break;
            case 1:
                node_build2.play();
                break;
        }
    }
}