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
public class WarriorAnimationControl extends AbstractControl{

    protected static final int WALK_ANIM = 0;
    protected static final int IDLE_ANIM = 1;
    protected AnimControl animControl;
    protected AnimChannel animChannel;
    protected WalkControl walkControl;
    
    public WarriorAnimationControl(){
        // Nothing to do here...
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        
        if(spatial == null){
            return;
        }
        
        animControl = spatial.getControl(AnimControl.class);
        walkControl = spatial.getControl(WalkControl.class);
        
        if(animControl != null && walkControl != null){
            animControl.setEnabled(true);
            animChannel = animControl.createChannel();
        }
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            if(spatial == null || walkControl == null){
                return;
            }
            if(walkControl.isMoving()){
                if(!("walk".equals(animChannel.getAnimationName()))){
                    setAnimation(WALK_ANIM);
                }
            }else{     
                if(!("idle".equals(animChannel.getAnimationName()))){
                    setAnimation(IDLE_ANIM);
                }
            }
        }
    }
    
    protected void setAnimation(int whichAnim){
        animChannel.setSpeed(0.5f);
        animChannel.setLoopMode(LoopMode.Loop); 
        switch(whichAnim){
            case WALK_ANIM:
                animChannel.setAnim("walk", GameObjectValues.BLEND_TIME);
                animChannel.setSpeed(walkControl.getSpeed());
                break;
            case IDLE_ANIM:
                animChannel.setAnim("idle", GameObjectValues.BLEND_TIME);
                break;
        }        
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        WarriorAnimationControl clone = new WarriorAnimationControl();
        clone.setSpatial(spatial);
        return clone;
    }
    
}
