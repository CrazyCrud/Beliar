/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author andministrator
 */
public abstract class Warrior extends Unit{

    protected Node node_warrior;
    protected int int_attackDamage, int_type;
    protected float float_criticalStrike;
    
    public Warrior(int healtPoints, int posX, int posZ, int speed, int attackDamage, int type){
        super(healtPoints, posX, posZ, speed);
        createWarrior(attackDamage, type);
    }
    
    private void createWarrior(int attackDamage, int type){
        this.int_attackDamage = attackDamage;
        this.int_type = type;
        this.float_criticalStrike = (float) Math.random() * 0.1f;
    }
    
    protected abstract void setUpControllers(); 
    
    @Override
    protected void addControllers(){
        if(spatial == null){
            return;
        }
        
        super.addControllers();
        spatial.addControl(new WarriorBehaviourControl());
        spatial.addControl(new WarriorAnimationControl());
    }
    
    @Override
    protected void initControllerValues() {
        super.initControllerValues();
        spatial.getControl(WarriorBehaviourControl.class).setAttackDamage(int_attackDamage);
        spatial.getControl(WarriorBehaviourControl.class).setCriticalStrike(float_criticalStrike);
        spatial.getControl(WarriorBehaviourControl.class).setType(int_type);
    }
    
    protected void positionWarrior(Vector3f spwanLocation) {
        spatial.getControl(GameObjectControl.class).setLocation(spwanLocation);
    }
}
