/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Map.MapController;
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

    private float float_buildTimer, float_moveTimer, float_removeTimer;
    private LinkedList<Building> list_buildings = new LinkedList<Building>();
    private static final int BUILD_TIMER = 0;
    private static final int MOVE_TIMER = 1;
    private static final int REMOVE_TIMER = 2;
    private boolean isBuilding = false;
    private boolean hasOrder = false;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            if(spatial.getControl(GameObjectControl.class).isAlive())
            {
                if(anyBuildingLeft()){
                setHasOrder(true);
                    if(hasReachedBuilding()){
                        if(!(spatial.getControl(WalkControl.class).isMoving())){
                            buildConstruction(tpf);
                            moveAwayFromBuilding();
                        }
                    }else{
                        walkToContruction();
                    }            
                }else{
                    setHasOrder(false);
                }
            }
            else{
                updateTimer(REMOVE_TIMER, tpf);
                if(checkTimer(REMOVE_TIMER)){
                    removeSlave();
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
    
    protected void build(Building building){
        System.out.println("SlaveCharacterControl: build()");
        list_buildings.addFirst(building);
    }
    
    private void walkToContruction(){
        if(!(spatial.getControl(WalkControl.class).isMoving())){
            if(isBuildingAccessible(list_buildings.getLast().getSpatial().getControl(GameObjectControl.class).getPosX(),
                list_buildings.getLast().getSpatial().getControl(GameObjectControl.class).getPosZ())){
                //TODO
            }else{
                list_buildings.removeLast();
            }
        } 
    }
    
    private boolean isBuildingAccessible(int x, int z) {
        if(spatial.getControl(WalkControl.class).findPath(x, z)){
            System.out.println("SlaveCharacterControl : isBuildingReachable() true");
            return true;
        }else{
            System.out.println("SlaveCharacterControl : isBuildingReachable() false");
            return false;
        }
    }
    
    private boolean hasReachedBuilding(){
        if(spatial.getControl(GameObjectControl.class).getPosX() == list_buildings.getLast().getSpatial().
                getControl(GameObjectControl.class).getPosX()){
            if(spatial.getControl(GameObjectControl.class).getPosZ() == list_buildings.getLast().getSpatial().
                getControl(GameObjectControl.class).getPosZ()){
                return true;
            }
        }
        return false;
    }

    private void buildConstruction(float tpf) {
        setIsBuilding(true);
        float_buildTimer += tpf;
        if(float_buildTimer > GameObjectValues.CONSTRUCTION_TIME){
            System.out.println("SlaveCharacterControl buildConstruction() finished");
            buildingFinished();
        }
    }
    
    private void buildingFinished(){
        resetTimer(BUILD_TIMER);
        resetTimer(MOVE_TIMER);
        setHasOrder(false);
        setIsBuilding(false);
        setBuilding();
    }
    
    private void resetTimer(int whichTimer){
        if(whichTimer == BUILD_TIMER){
            float_buildTimer = 0.0f;
            return;
        }else if(whichTimer == MOVE_TIMER){
            float_moveTimer = 0.0f;
        }else{
            float_removeTimer = 0.0f;
        }        
    }
    
    private void updateTimer(int whichTimer, float value){
        if(whichTimer == BUILD_TIMER){
            
        }else if(whichTimer == MOVE_TIMER){
            
        }else{
            float_removeTimer += value;
        } 
    }
    
    private boolean checkTimer(int whichTimer) {
        if(whichTimer == BUILD_TIMER){
            return false;
        }else if(whichTimer == MOVE_TIMER){
            return false;
        }else{
            if(float_removeTimer > 10.0f){
                return true;
            }else{
                return false;
            }
        }
    }
    
    private void setBuilding(){
        Building building = list_buildings.removeLast();
        int type = building.getType();
        if(type == GameContainer.ADAM_BUILDING || type == GameContainer.KYTHOS_BUILDING ||
                type == GameContainer.MARA_BUILDING){
            BuildingController.addProductionBuilding((ProductionBuilding)building);
        }
        GameContainer.getInstance().getApplication().getStateManager().getState(GameState.class).
                buildSucessfull((Node)building.getSpatial());
    }
    
    private boolean anyBuildingLeft(){
        return !(list_buildings.isEmpty()); 
    }
    
    private void removeSlave() {
        spatial.getControl(SlaveAnimationControl.class).setEnabled(false);
        spatial.getControl(WalkControl.class).setEnabled(false);
        spatial.getControl(GameObjectControl.class).setEnabled(false);
        spatial.removeFromParent();
        spatial.removeControl(GameObjectControl.class);
        spatial.removeControl(WalkControl.class);
        spatial.removeControl(SlaveAnimationControl.class);
        spatial.removeControl(this);
    }

    private void moveAwayFromBuilding() {
        int x = spatial.getControl(GameObjectControl.class).getPosX();
        int z = spatial.getControl(GameObjectControl.class).getPosZ();
        if(!(MapController.isNodeCovered(x - 1, z)) && spatial.getControl(WalkControl.class).findPath(x - 1, z)){
            return;
        }else if(!(MapController.isNodeCovered(x + 1, z)) && spatial.getControl(WalkControl.class).findPath(x + 1, z)){
            return;
        }else if(!(MapController.isNodeCovered(x, z + 1)) && spatial.getControl(WalkControl.class).findPath(x, z + 1)){
            return;
        }else if(!(MapController.isNodeCovered(x, z - 1)) && spatial.getControl(WalkControl.class).findPath(x, z - 1)){
            return;
        }
    }
}
