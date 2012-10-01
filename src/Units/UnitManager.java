/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andministrator
 */
public class UnitManager {
    private beliar.GameState gameState;
    private AssetManager assetManager;
    
    private Slave slaveInstance;
    private List<Spatial> slaveUnits;
    
    public UnitManager(beliar.GameState gameState, AssetManager assetManager){
        initValues(gameState, assetManager);
        initUnits();
    }
    
    private void initValues(beliar.GameState gameState, AssetManager assetManager){
        this.gameState = gameState;
        this.assetManager = assetManager;
        slaveUnits = new ArrayList<Spatial>();
    }
    
    private void initUnits(){
        slaveInstance = new Slave(assetManager);
    }
    
    public void createSlave(Vector3f spwanLocation){
        Node mySlave = (Node) slaveInstance.createSlave(gameState, spwanLocation);
        slaveUnits.add(mySlave);
    }
}
