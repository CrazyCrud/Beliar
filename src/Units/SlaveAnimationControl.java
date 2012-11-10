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

    protected boolean bool_enabled;
    protected AnimControl animControl;
    protected AnimChannel animChannel;
    protected CharacterControl characterControl;
    
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
        characterControl = spatial.getControl(CharacterControl.class);
        
        if(animControl != null && characterControl != null){
            bool_enabled = true;
            animChannel = animControl.createChannel();
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            if(characterControl == null){
                return;
            }
            
            if(characterControl.getWalkDirection().length() == 0){
                if(!("Idle".equals(animChannel.getAnimationName()))){
                    animChannel.setAnim("Idle", GameObjectValues.BLEND_TIME);
                    animChannel.setLoopMode(LoopMode.Loop);
                }
            }else{
                if(!("Walk".equals(animChannel.getAnimationName()))){
                    animChannel.setAnim("Walk", GameObjectValues.BLEND_TIME);
                    animChannel.setLoopMode(LoopMode.Loop);
                }
            }
            if(!(spatial.getControl(GameObjectControl.class).isAlive())){
                if(!("Die".equals(animChannel.getAnimationName()))){
                    animChannel.setAnim("Die", GameObjectValues.BLEND_TIME);
                    animChannel.setLoopMode(LoopMode.DontLoop);
                }
            }
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