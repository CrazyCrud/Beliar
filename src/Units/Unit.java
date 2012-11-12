/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

/**
 *
 * @author andministrator
 */
public abstract class Unit extends GameObject{

    protected int int_speed, int_orientation;

    public Unit(int healtPoint, int posX, int posY, int speed) {
        super(healtPoint, posX, posY);
  
        this.int_speed = speed;
        this.int_orientation = GameObjectValues.NO_DIRECTION_CHANGE;
    }
    
    @Override
    protected void addControllers(){
        if(spatial == null){
            return;
        }
        super.addControllers();
        this.spatial.addControl(new WalkControl());
    }

    @Override
    protected void initControllerValues() {
        if(spatial == null){
            return;
        }
        super.initControllerValues();
        spatial.getControl(WalkControl.class).setSpeed(int_speed);
        spatial.getControl(WalkControl.class).setDirection(int_orientation);
    }
    
    @Override
    protected void removeGameObject(){
        super.removeGameObject();
        spatial.removeControl(WalkControl.class);
    }
}