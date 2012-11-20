/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Map.MapController;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/**
 *
 * @author andministrator
 */
public class GameObjectControl extends AbstractControl{
    
    public GameObjectControl(){
        // nothing to do here...
    }
    
    public void setHealth(float health){
        spatial.setUserData(GameObjectValues.HEALTH_KEY, health);
    }
    
    public float getHealth(){
        return spatial.getUserData(GameObjectValues.HEALTH_KEY);
    }
    
    public boolean isAlive(){
        return getHealth() > 0;
    }    
        
    public void setLocation(Vector3f newLocation){
        Vector3f oldPosition = spatial.getLocalTranslation();
        MapController.setNodeTo((int)oldPosition.x, (int)oldPosition.z, false);
        MapController.setNodeTo((int)newLocation.x, (int)newLocation.z, true);
        spatial.setLocalTranslation(newLocation);
    }
    
    public Vector3f getLocation(){
        return spatial.getLocalTranslation();
    }
    
    public void setPosX(int posX) {
        spatial.setUserData(GameObjectValues.POSX_KEY, posX);
    }
    
    public int getPosX() {
        return spatial.getUserData(GameObjectValues.POSX_KEY);
    }

    public void setPosZ(int posZ) {
        spatial.setUserData(GameObjectValues.POSZ_KEY, posZ);
    }
    
    public int getPosZ() {
        return spatial.getUserData(GameObjectValues.POSZ_KEY);
    }
     
    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
    }

    public Control cloneForSpatial(Spatial spatial) {
        final GameObjectControl clone = new GameObjectControl();
        clone.setSpatial(spatial);
        return clone;
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }
}
