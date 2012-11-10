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
    
    protected static final int WORK_ANIM = 0;
    protected static final int IDLE_ANIM = 1;
    
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
        if(spatial == null || buildingControl == null){
            return;
        }
        if(isEnabled()){
            if(buildingControl.isActive()){
                if(!("work".equals(animChannel.getAnimationName()))){
                    setAnimation(WORK_ANIM);
                } 
            }else{
                if(!("idle".equals(animChannel.getAnimationName()))){
                    setAnimation(IDLE_ANIM);
                }
            }
        }
    }
    
    private void setAnimation(int whichAnimation){
        switch(whichAnimation){
            case WORK_ANIM:
                animChannel.setAnim("work", GameObjectValues.BLEND_TIME);
                break;
            case IDLE_ANIM:
                animChannel.setAnim("idle", GameObjectValues.BLEND_TIME);
                break;
        }
        animChannel.setLoopMode(LoopMode.Loop);
        animChannel.setSpeed(0.5f);
    }
    
    protected void setAnimation() {
        if(buildingControl.isActive()){
                animChannel.setAnim("work", GameObjectValues.BLEND_TIME);
            }else{
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
