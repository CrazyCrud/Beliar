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
import Pathfinding.Pathfinder;
import Pathfinding.Node;
import Map.MapController;
import com.jme3.math.Vector3f;
import java.util.List;
/**
 *
 * @author andministrator
 */
public class WalkControl extends AbstractControl{

    private Pathfinder pathFinder;
    private List<Node> list_path;
    private Node node_target, node_start;
    private float float_timer;
    private boolean bool_isMoving;
    
    public WalkControl(){
        pathFinder = new Pathfinder();
        bool_isMoving = false;
        float_timer = 0.0f;
    }
    
    public void setSpeed(float speed){
        spatial.setUserData(GameObjectValues.SPEED_KEY, speed);
    }
    
    public float getSpeed(){
        return spatial.getUserData(GameObjectValues.SPEED_KEY);
    }
    
    protected void findPath(int xPos, int zPos){
        node_start = MapController.getNode(spatial.getControl(GameObjectControl.class).getPosX(), 
                spatial.getControl(GameObjectControl.class).getPosY());
        node_target = MapController.getNode(xPos, zPos);
        list_path = (List)pathFinder.search(node_start, node_target);
        if(list_path != null){
            setMoving(true);
        }else{
            setMoving(false);
        }
    }
    
    protected boolean isMoving(){
        return bool_isMoving;
    }
    
    protected void setMoving(boolean isMoving){
        bool_isMoving = isMoving;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
        if(isEnabled()){
            if(isMoving()){
                if(isTimeToMove()){
                    setPosition();
                    resetTimer();
                }else{
                    updateTimer(tpf);
                }
            } 
        }
    }
    
    private boolean isTimeToMove(){
        return float_timer > GameObjectValues.MOVEMENT_PERIOD ? true : false;
    }
    
    private void updateTimer(float timeToUpdate){
        float_timer += (timeToUpdate + getSpeed()); 
    }
    
    private void resetTimer(){
        float_timer = 0.0f;
    }    
    private void setPosition(){
        if(list_path.isEmpty()){
            setMoving(false);
            clearMovement();
            return;
        }
        
        Node nextNode = list_path.get(0);
        int xPos = nextNode.getXPos();
        int zPos = nextNode.getYPos();
        spatial.getControl(GameObjectControl.class).setPosX(xPos);
        spatial.getControl(GameObjectControl.class).setPosY(zPos);
        spatial.getControl(GameObjectControl.class).setLocation(new Vector3f(xPos, 
                GameObjectValues.Y_POSITION, zPos));
        list_path.remove(nextNode);
    }
    
    private void clearMovement(){
        clearPath();
        clearNodes();
    }
    
    private void clearPath(){
        list_path.clear();
    }
    
    private void clearNodes(){
        node_start = node_target = null;
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        final WalkControl clone = new WalkControl();
        clone.setSpatial(spatial);
        return clone;
    }

    protected boolean isWalking() {
        return true;
    }
    
}
