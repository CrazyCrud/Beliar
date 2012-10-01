/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

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
public class UnitControl extends AbstractControl{

    private beliar.GameState gameState;
    
    public UnitControl(beliar.GameState gameState){
        this.gameState = gameState;
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        return null;
    }
    
    public void setHealth(float health){
        spatial.setUserData(UnitValues.HEALTH_KEY, health);
    }
    
    public float getHealth(){
        return spatial.getUserData(UnitValues.HEALTH_KEY);
    }
    
    public boolean isAlive(){
        return getHealth() > 0;
    }    
    
    public void setSpeed(float speed){
        spatial.setUserData(UnitValues.SPEED_KEY, speed);
    }
    
    public float getSpeed(){
        return spatial.getUserData(UnitValues.SPEED_KEY);
    }
    
    public void setLocation(Vector3f newLocation){
        spatial.setLocalTranslation(newLocation);
    }
    
    public Vector3f getLocation(){
        return spatial.getLocalTranslation();
    }
}
