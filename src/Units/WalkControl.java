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
/**
 *
 * @author andministrator
 */
public class WalkControl extends AbstractControl{

    private Pathfinder pathFinder;
    
    public WalkControl(){
        pathFinder = new Pathfinder();
    }
    
    public void setSpeed(float speed){
        spatial.setUserData(GameObjectValues.SPEED_KEY, speed);
    }
    
    public float getSpeed(){
        return spatial.getUserData(GameObjectValues.SPEED_KEY);
    }
    
    protected void findPath(int xPos, int zPos){
        Node start = MapController.getNode(spatial.getControl(GameObjectControl.class).getPosX(), 
                spatial.getControl(GameObjectControl.class).getPosY());
        Node target = MapController.getNode(xPos, zPos);
        Object result = pathFinder.search(start, target);
        if(result != null){
            spatial.getControl(GameObjectControl.class).setPosX(xPos);
            spatial.getControl(GameObjectControl.class).setPosY(zPos);
            spatial.getControl(GameObjectControl.class).setLocation(new Vector3f(xPos, 
                    GameObjectValues.Z_POSITION, zPos));
        }
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        if(spatial == null){
            return;
        }
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
