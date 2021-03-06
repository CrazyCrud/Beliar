/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Map.MapController;
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
            checkForRise();
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

    private void checkForRise() {
        int x = spatial.getControl(GameObjectControl.class).getPosX();
        int z = spatial.getControl(GameObjectControl.class).getPosZ();
        //int xSoulAbyss = (int)MapController.getSoulAbyssPosition().x;
        //int zSoulAbyss = (int)MapController.getSoulAbyssPosition().z;
        Pathfinding.Node position = MapController.getNode(x, z);
        
        if(position.getType() == 11){
            notifyWarriorRise();
            removeWarrior();
        }
    }
    
    private void removeWarrior(){
        spatial.getControl(WarriorAnimationControl.class).setEnabled(false);
        spatial.getControl(WalkControl.class).setEnabled(false);
        spatial.getControl(GameObjectControl.class).setEnabled(false);
        spatial.removeFromParent();
        spatial.removeControl(GameObjectControl.class);
        spatial.removeControl(WalkControl.class);
        spatial.removeControl(WarriorAnimationControl.class);
        spatial.removeControl(this);
    }
    
    private void notifyWarriorRise(){
        UnitModel.getInstance().notiftyWarriorRise();
    }
}
