/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author andministrator
 */
public class Slave {
    private AssetManager assetManager;
    private Node slaveNode;
    private Geometry slaveGeo;
    
    public Slave(AssetManager assetManager){
        this.assetManager = assetManager;
    }
    
    public Spatial createSlave(beliar.GameState gameState, Vector3f spwanLocation){
        slaveGeo = (Geometry) assetManager.loadModel("Models/Units/slave.mesh.xml");
        slaveGeo.setLocalTranslation(spwanLocation);
        slaveNode.attachChild(slaveGeo);
        slaveNode.setUserData(UnitValues.HEALTH_KEY, UnitValues.HEALTH_VALUE_SLAVE);
        slaveNode.setUserData(UnitValues.SPEED_KEY, UnitValues.SPEED_VALUE_SLAVE);
        slaveNode.addControl(new UnitControl(gameState));
        slaveNode.addControl(new IdleBehaviourControl());
        slaveNode.addControl(new WalkControl());
        return slaveNode;
    }
}
