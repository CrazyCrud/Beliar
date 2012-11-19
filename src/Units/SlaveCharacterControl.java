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
import java.util.LinkedList;

/**
 *
 * @author andministrator
 */
public class SlaveCharacterControl extends AbstractControl{

    private float float_timer;
    private LinkedList<Node> list_buildings = new LinkedList<Node>();
    private boolean isBuilding = false;
    private boolean hasOrder = false;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(anyBuildingLeft()){
            setHasOrder(true);
            if(hasReachedBuilding()){
                if(!(spatial.getControl(WalkControl.class).isMoving())){
                    buildConstruction(tpf);
                }
            }else{
                walkToContruction();
            }            
        }else{
            setHasOrder(false);
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
    
    protected void build(Node building){
        System.out.println("SlaveCharacterControl: build()");
        list_buildings.addFirst(building);
    }
    
    private void walkToContruction(){
        if(!(spatial.getControl(WalkControl.class).isMoving())){
            if(isBuildingAccessible(list_buildings.getLast().getControl(GameObjectControl.class).getPosX(),
                list_buildings.getLast().getControl(GameObjectControl.class).getPosZ())){
                //TODO
            }else{
                list_buildings.removeLast();
            }
        } 
    }
    
    private boolean isBuildingAccessible(int x, int z) {
        if(spatial.getControl(WalkControl.class).findPath(x, z)){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean hasReachedBuilding(){
        if(spatial.getControl(GameObjectControl.class).getPosX() == list_buildings.getLast().
                getControl(GameObjectControl.class).getPosX()){
            if(spatial.getControl(GameObjectControl.class).getPosZ() == list_buildings.getLast().
                getControl(GameObjectControl.class).getPosZ()){
                return true;
            }
        }
        return false;
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
        setBuilding();
    }
    
    private void resetTimer(){
        float_timer = 0.0f;
    }
    
    private void setBuilding(){
        Node building = list_buildings.removeLast();
        GameContainer.getInstance().getApplication().getStateManager().getState(GameState.class).buildSucessfull(building);
    }
    
    private boolean anyBuildingLeft(){
        return !(list_buildings.isEmpty()); 
    }
}
