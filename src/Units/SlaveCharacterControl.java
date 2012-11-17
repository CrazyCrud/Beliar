/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import beliar.GameContainer;
import beliar.GameState;

/**
 *
 * @author andministrator
 */
public class SlaveCharacterControl extends AbstractControl{

    private Node node_building;
    private float float_timer;
    private boolean isBuilding = false;
    private boolean hasOrder = false;
    private boolean isTargetAccessible = false;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            if(hasOrder){
                    if(!(spatial.getControl(WalkControl.class).isMoving())){
                        buildConstruction(tpf);
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
    
    protected boolean hasOrder(){
        return this.hasOrder;
    }
    
    protected void setIsTargetAccessible(boolean isTargetAccessible) {
        this.isTargetAccessible = isTargetAccessible;
    }
    
    protected void build(Node building){
        System.out.println("SlaveCharacterControl: build()");
        this.node_building = building;
        setHasOrder(true);
    }

    private void buildConstruction(float tpf) {
        setIsBuilding(true);
        float_timer += tpf;
        if(float_timer > GameObjectValues.CONSTRUCTION_TIME){
            System.out.println("SlaveCharacterControl buildConstruction() finished");
            buildingFinished();
        }
    }
    
    private void buildingFinished(){
        resetTimer();
        setHasOrder(false);
        setIsBuilding(false);
        setIsTargetAccessible(false);
        setBuilding();
    }
    
    private void resetTimer(){
        float_timer = 0.0f;
    }
    
    private void setBuilding(){
        GameContainer.getInstance().getApplication().getStateManager().getState(GameState.class).buildSucessfull(node_building);
    }
}
