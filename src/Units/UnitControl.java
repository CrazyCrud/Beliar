/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
