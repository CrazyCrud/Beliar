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

    private float float_timer;
    private int int_xTarget, int_zTarget;
    private boolean isBuilding = false;
    private boolean hasOrder = false;
    private boolean isTargetAccessible = false;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            if(hasOrder){
                if(!isTargetAccessible){
                    findPathToConstruction(int_xTarget, int_zTarget);
                }else{
                    if(!(spatial.getControl(WalkControl.class).isMoving())){
                        buildConstruction(tpf);
                    }
                }
            }
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
    
    protected boolean isIsBuilding() {
        return isBuilding;
    }

    protected void setIsBuilding(boolean isBuilding) {
        this.isBuilding = isBuilding;
    }
    
    protected void setHasOrder(boolean hasOrder) {
        this.hasOrder = hasOrder;
    }
    
    protected void setIsTargetAccessible(boolean isTargetAccessible) {
        this.isTargetAccessible = isTargetAccessible;
    }
    
    protected void build(int x, int z){
        setTargetConstruction(x, z);
        setHasOrder(true);
    }
    
    private void setTargetConstruction(int x, int z) {
        int_xTarget = x;
        int_zTarget = z;
    }

    private void findPathToConstruction(int x, int z) {
        setIsTargetAccessible(false);
        if(spatial.getControl(WalkControl.class).findPath(x - 1, z)){
            setIsTargetAccessible(true);
        }else if(spatial.getControl(WalkControl.class).findPath(x + 1, z)){
            setIsTargetAccessible(true);
        }else if(spatial.getControl(WalkControl.class).findPath(x, z - 1)){
            setIsTargetAccessible(true);
        }else if(spatial.getControl(WalkControl.class).findPath(x, z + 1)){
            setIsTargetAccessible(true);
        }      
    }

    private void buildConstruction(float tpf) {
        setIsBuilding(true);
        float_timer += tpf;
        if(float_timer > GameObjectValues.CONSTRUCTION_TIME){
            buildingFinished();
        }
    }
    
    private void buildingFinished(){
        resetTimer();
        setHasOrder(false);
        setIsBuilding(false);
        setIsTargetAccessible(false);
    }
    
    private void resetTimer(){
        float_timer = 0.0f;
    }
}
