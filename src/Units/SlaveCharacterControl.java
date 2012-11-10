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
public class SlaveCharacterControl extends AbstractControl{

    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){

        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        SlaveCharacterControl clone = new SlaveCharacterControl();
        clone.setSpatial(spatial);
        return clone;
    }  
}
