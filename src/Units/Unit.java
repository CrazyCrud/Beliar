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

    protected int int_speed;

    public Unit(int healtPoint, int posX, int posY, int speed) {
        super(healtPoint, posX, posY);
  
        this.int_speed = speed;
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
    }
    
    @Override
    protected void removeGameObject(){
        super.removeGameObject();
        spatial.removeControl(WalkControl.class);
    }
}