/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.List;

/**
 *
 * @author andministrator
 */
public class UnitManager {
    private beliar.GameState gameState;
    private AssetManager assetManager;
    private Node rootNode;
    
    private Slave slaveInstance;
    private Node slaveUnits;
    
    public UnitManager(beliar.GameState gameState, AssetManager assetManager, Node rootNode){
        initValues(gameState, assetManager, rootNode);
        initUnits();
        attachUnits();
    }
    
    private void initValues(beliar.GameState gameState, AssetManager assetManager, Node rootNode){
        this.gameState = gameState;
        this.assetManager = assetManager;
        this.rootNode = rootNode;
    }
    
    private void initUnits(){
        slaveUnits = new Node("SlaveUnits");
        slaveInstance = new Slave(assetManager);
    }
    
    private void attachUnits(){
        this.rootNode.attachChild(slaveUnits);
    }
    
    public void createSlave(Vector3f spwanLocation){
        Node mySlave = (Node) slaveInstance.createSlave(gameState, spwanLocation);
        slaveUnits.attachChild(mySlave);
    }
    
    public List<Spatial> getSlaves(){
        return slaveUnits.getChildren();
    }
    
    public int getSlaveNumber(){
        return slaveUnits.getChildren().size();
    }
}
