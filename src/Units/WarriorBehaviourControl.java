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
public class WarriorBehaviourControl extends AbstractControl{

    protected void setAttackDamage(int attackDamage){
        spatial.setUserData(GameObjectValues.ATTACK_DAMAGE_KEY, attackDamage);
    }
    
    protected int getAttackDamage(){
        return spatial.getUserData(GameObjectValues.ATTACK_DAMAGE_KEY);
    }
    
    protected void setCriticalStrike(float criticalStrike){
        spatial.setUserData(GameObjectValues.CRITICAL_STRIKE_KEY, criticalStrike);
    }
    
    protected float getCriticalStrike(){
        return spatial.getUserData(GameObjectValues.CRITICAL_STRIKE_KEY);
    }
    
    protected void setType(int type){
        spatial.setUserData(GameObjectValues.WARRIOR_TYPE_KEY, type);
    }
    
    protected int getType(){
        return spatial.getUserData(GameObjectValues.WARRIOR_TYPE_KEY);
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
        
        if(isEnabled()){
            
        }
    }
    
    protected void attack(Warrior enemy){
        
    }
    
    protected void getsAttackedBy(Warrior enemy){
        
    }
    
    protected void controlArea(){
        
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        Control clone = new WarriorBehaviourControl();
        clone.setSpatial(spatial);
        return clone;
    }
    
}
