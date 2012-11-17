/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.asset.AssetManager;
import beliar.GameContainer;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;
/**
 *
 * @author andministrator
 */
public abstract class GameObject {
    protected AssetManager assetManager;
    protected Spatial spatial;
    protected Material material;
    protected int int_healthPoints, int_posX, int_posZ;
    
    public GameObject(int healtPoint, int posX, int posZ){
        assetManager = GameContainer.getInstance().getApplication().getAssetManager();
        this.int_posX = posX;
        this.int_posZ = posZ;
        this.int_healthPoints = healtPoint;
    }

    protected abstract void setUpControllers(); 
    
    protected void addControllers(){
        if(spatial == null){
            return;
        }
        this.spatial.addControl(new GameObjectControl());
    }

    protected void initControllerValues() {
        if(spatial == null){
            return;
        }
        spatial.getControl(GameObjectControl.class).setHealth(int_healthPoints);
        spatial.getControl(GameObjectControl.class).setPosX(int_posX);
        spatial.getControl(GameObjectControl.class).setPosZ(int_posZ);
    }
    
    protected Spatial getSpatial() {
        return spatial;
    }
    
    protected Material getMaterial() {
        return material;
    }
    
    protected void removeGameObject(){
        spatial.removeControl(GameObjectControl.class);
    }
}
