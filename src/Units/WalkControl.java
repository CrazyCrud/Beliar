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
    private Node node_target, node_start, node_current;
    private float float_timer, float_moveTimer;
    private int int_direction;
    private static final int MOVE_TIMER = 0;
    private static final int RANDOMMOVE_TIMER = 1;
    private boolean bool_isMoving;
    
    public WalkControl(){
        pathFinder = new Pathfinder();
        bool_isMoving = false;
        float_timer = float_moveTimer = 0.0f;
        int_direction = GameObjectValues.NO_DIRECTION_CHANGE;
    }
    
    public void setSpeed(float speed){
        spatial.setUserData(GameObjectValues.SPEED_KEY, speed);
    }
    
    public float getSpeed(){
        return spatial.getUserData(GameObjectValues.SPEED_KEY);
    }
    
    protected boolean findPath(int xPos, int zPos){
        boolean isTargetAccessible = isTargetAccessible(xPos, zPos);
        if(isTargetAccessible){
            list_path.remove(0);
            setMoving(true);
            return true;
        }else{
            setMoving(false);
            return false;
        }
    }
    
    private boolean isTargetAccessible(int xPos, int zPos){
        node_start = MapController.getNode(spatial.getControl(GameObjectControl.class).getPosX(), 
                spatial.getControl(GameObjectControl.class).getPosZ());
        node_current = node_start;
        node_target = MapController.getNode(xPos, zPos);
        list_path = (List)pathFinder.search(node_start, node_target);
        return list_path == null ? false : true;
    }
    
    protected boolean isMoving(){
        return bool_isMoving;
    }
    
    protected void setMoving(boolean isMoving){
        bool_isMoving = isMoving;
    }
    
    protected void setDirection(int direction){
        int_direction = direction;
        spatial.setUserData(GameObjectValues.ORIENTATION_KEY, int_direction);
    }
    
    protected int getDirection(){
        return int_direction;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
        if(isEnabled()){
            if(!(spatial.getControl(GameObjectControl.class).isAlive())){
                return;
            }else{
                if(isMoving()){
                    setOrientation();
                    walkInsideTile();
                    if(isTimeToMove()){
                        setPosition();
                        resetTimer(MOVE_TIMER);
                    }else{
                        updateTimer(MOVE_TIMER, tpf);
                    }
                }else{
                    moveRandom(tpf);
                } 
            }
        }
    }
    
    private boolean isTimeToMove(){
        return float_timer > GameObjectValues.MOVEMENT_PERIOD ? true : false;
    }
    
    private void updateTimer(int whichTimer, float timeToUpdate){
        switch(whichTimer){
            case MOVE_TIMER:
                float_timer += (timeToUpdate + getSpeed()); 
                break;
            case RANDOMMOVE_TIMER:
                float_moveTimer += timeToUpdate;
                break;
        }
        
    }
    
    private void resetTimer(int whichTimer){
        switch(whichTimer){
            case MOVE_TIMER:
                float_timer = 0.0f;
                break;
            case RANDOMMOVE_TIMER:
                float_moveTimer = 0.0f;
        }
        
    }    
    
    private void setOrientation(){
        if(list_path.isEmpty()){
            return;
        }
        Node nextNode = list_path.get(0);
        int newXPos = nextNode.getXPos();
        int newZPos = nextNode.getYPos();
        spatial.lookAt(new Vector3f((float)newXPos, (float)GameObjectValues.Y_POSITION_UNITS, (float)newZPos), 
                GameObjectValues.UP_VECTOR);
    }
    
    private void walkInsideTile(){
        if(list_path.isEmpty()){
            return;
        }
        Node nextNode = list_path.get(0);
        int xPos = node_current.getXPos();
        int zPos = node_current.getYPos();
        int newXPos = nextNode.getXPos();
        int newZPos = nextNode.getYPos();
        float tileMovement = 0.0125f;
        if(list_path.size() == 1){
            //float_timer += getSpeed();
        }
        
        // Erster Wert bewegt das Spatial vertikal, der dritte Wert horizontal
        if(newXPos > xPos){
            spatial.move(new Vector3f(tileMovement, (float)GameObjectValues.Y_POSITION_UNITS, 0f));
        }else if(newXPos < xPos){
            spatial.move(new Vector3f(-tileMovement, (float)GameObjectValues.Y_POSITION_UNITS, 0f));
        }
        if(newZPos > zPos){
            spatial.move(new Vector3f(0f, (float)GameObjectValues.Y_POSITION_UNITS, tileMovement));
        }else if(newZPos < zPos){
            spatial.move(new Vector3f(0f, (float)GameObjectValues.Y_POSITION_UNITS, -tileMovement));
        }
    }
    
    private void setPosition(){
        if(list_path.isEmpty()){
            setMoving(false);
            clearMovement();
            return;
        }
        
        Node nextNode = list_path.get(0);
        int newXPos = nextNode.getXPos();
        int newZPos = nextNode.getYPos();
        node_current = nextNode;
        spatial.getControl(GameObjectControl.class).setPosX(newXPos);
        spatial.getControl(GameObjectControl.class).setPosZ(newZPos);
        spatial.getControl(GameObjectControl.class).setLocation(new Vector3f(newXPos, 
                GameObjectValues.Y_POSITION_UNITS, newZPos));
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
    
    private void moveRandom(float updateTimer){
        if(isMoving()){
            return;
        }else if(spatial.getControl(SlaveCharacterControl.class) != null){
            if(spatial.getControl(SlaveCharacterControl.class).isIsBuilding()){
                return;
            }
        }
        if(isTimeForRandomMove()){
            int x = spatial.getControl(GameObjectControl.class).getPosX();
            int z = spatial.getControl(GameObjectControl.class).getPosZ();
            int direction = (int)Math.round(Math.random() * 3);
            switch(direction){
                case 0:
                    if(MapController.isNodeCovered(x - 1, z)){
                        break;
                    }else{
                        findPath(x - 1, z);
                        break;
                    }
                case 1:
                    if(MapController.isNodeCovered(x + 1, z)){
                        break;
                    }else{
                        findPath(x + 1, z);
                        break;
                    }
                case 2:
                    if(MapController.isNodeCovered(x, z + 1)){
                        break;
                    }else{
                        findPath(x, z + 1);
                        break;
                    }
                case 3:
                    if(MapController.isNodeCovered(x, z - 1)){
                        break;
                    }else{
                        findPath(x, z - 1);
                        break;
                    }
            }
            resetTimer(RANDOMMOVE_TIMER);
        }else{
            updateTimer(RANDOMMOVE_TIMER, updateTimer);
        }
    }
    
    private boolean isTimeForRandomMove(){
        return float_moveTimer > 10.0f? true: false;   
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        final WalkControl clone = new WalkControl();
        clone.setSpatial(spatial);
        return clone;
    }    
}
