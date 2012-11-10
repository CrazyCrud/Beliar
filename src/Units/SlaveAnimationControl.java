/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
/**
 *
 * @author andministrator
 */
public class SlaveAnimationControl extends AbstractControl{

    protected static final int WALK_ANIM = 0;
    protected static final int IDLE_ANIM = 1;
    protected static final int BUILD_ANIM = 2;
    protected static final int DIE_ANIM = 3;
    protected AnimControl animControl;
    protected AnimChannel animChannel;
    protected WalkControl walkControl;
    
    public SlaveAnimationControl(){
        // nothing to do here...
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
                if(!("Idle".equals(animChannel.getAnimationName()))){
                    setAnimation(IDLE_ANIM);
                }
            }else{
                if(!("Walk".equals(animChannel.getAnimationName()))){
                    setAnimation(WALK_ANIM);
                }
            }
            if(!(spatial.getControl(GameObjectControl.class).isAlive())){
                if(!("Die".equals(animChannel.getAnimationName()))){
                     setAnimation(DIE_ANIM);                   
                }
            }
        }
    }
    
    protected void setAnimation(int whichAnim){
        animChannel.setSpeed(0.5f);
        animChannel.setLoopMode(LoopMode.Loop); 
        switch(whichAnim){
            case WALK_ANIM:
                animChannel.setAnim("Walk", GameObjectValues.BLEND_TIME);
                animChannel.setSpeed(walkControl.getSpeed());
                break;
            case IDLE_ANIM:
                animChannel.setAnim("Idle", GameObjectValues.BLEND_TIME);
                break;
            case BUILD_ANIM:
                animChannel.setAnim("Build", GameObjectValues.BLEND_TIME);
                break;
            case DIE_ANIM:
                animChannel.setAnim("Die", GameObjectValues.BLEND_TIME);
                animChannel.setLoopMode(LoopMode.DontLoop); 
                break;
        }        
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        SlaveAnimationControl clone = new SlaveAnimationControl();
        clone.setSpatial(spatial);
        return clone;
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }
}