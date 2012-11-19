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

    private float float_buildTimer, float_moveTimer;
    private LinkedList<Node> list_buildings = new LinkedList<Node>();
    private static final int BUILD_TIMER = 0;
    private static final int MOVE_TIMER = 1;
    private boolean isBuilding = false;
    private boolean hasOrder = false;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(anyBuildingLeft()){
            setHasOrder(true);
            if(hasReachedBuilding()){
                if(!(spatial.getControl(WalkControl.class).isMoving())){
                    buildConstruction(tpf);
                    resetTimer(MOVE_TIMER);
                }
            }else{
                walkToContruction();
            }            
        }else{
            setHasOrder(false);
            moveRandom();
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
        float_buildTimer += tpf;
        if(float_buildTimer > GameObjectValues.CONSTRUCTION_TIME){
            System.out.println("SlaveCharacterControl buildConstruction() finished");
            buildingFinished();
        }
    }
    
    private void buildingFinished(){
        resetTimer(BUILD_TIMER);
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
        }        
    }
    
    private void setBuilding(){
        Node building = list_buildings.removeLast();
        GameContainer.getInstance().getApplication().getStateManager().getState(GameState.class).buildSucessfull(building);
    }
    
    private boolean anyBuildingLeft(){
        return !(list_buildings.isEmpty()); 
    }
    
    private void moveRandom(){
        if(spatial.getControl(WalkControl.class).isMoving()){
            return;
        }else{
            if(float_moveTimer != 0.0f){
                float_moveTimer += 1.0f;
                if(float_moveTimer > 1000.0f){
                    resetTimer(MOVE_TIMER);
                }
                return;
            }
            float_moveTimer += 1.0f;
            int x = spatial.getControl(GameObjectControl.class).getPosX();
            int z = spatial.getControl(GameObjectControl.class).getPosZ();
            int direction = (int)Math.round(Math.random() * 4);
            switch(direction){
                case 0:
                    if(spatial.getControl(WalkControl.class).findPath(x - 1, z)){
                        return;
                    }
                case 1:
                    if(spatial.getControl(WalkControl.class).findPath(x + 1, z)){
                        return;
                    }
                case 2:
                    if(spatial.getControl(WalkControl.class).findPath(x, z + 1)){
                        return;
                    }
                case 3:
                    if(spatial.getControl(WalkControl.class).findPath(x, z - 1)){
                        return;
                    }
            }
        }
    }
}
