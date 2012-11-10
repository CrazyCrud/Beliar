/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
/**
 *
 * @author andministrator
 */
public class BuildingAnimationControl extends AbstractControl{

    protected AnimControl animControl;
    protected AnimChannel animChannel;
    protected ProductionBuildingControl buildingControl;
    
    public BuildingAnimationControl(){
        // nothing to do here...
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        
        if(spatial == null){
            return;
        }
        
        animControl = spatial.getControl(AnimControl.class);
        buildingControl = spatial.getControl(ProductionBuildingControl.class);
        
        if(animControl != null && buildingControl != null){
            animControl.setEnabled(true);
            animChannel = animControl.createChannel();
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
        if(isEnabled()){

        }
    }
    
    protected void setAnimation() {
        if(buildingControl.isActive()){
                System.out.println("BuildingAnimationControl: update(work) Animation of " + spatial.getName());
                animChannel.setAnim("work", GameObjectValues.BLEND_TIME);
            }else{
                System.out.println("BuildingAnimationControl: update(idle) Animation of " + spatial.getName());
                animChannel.setAnim("idle", GameObjectValues.BLEND_TIME);
            }
        animChannel.setLoopMode(LoopMode.Loop);
        animChannel.setSpeed(0.5f);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        final BuildingAnimationControl clone = new BuildingAnimationControl();
        clone.setSpatial(spatial);
        return clone;
    }
}
