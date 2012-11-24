/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author andministrator
 */
public class UnitModel {
    
    private static UnitModel unitModel;
    private ArrayList<Unit> list_units;
    private ArrayList<Slave> list_slaves;
    
    private UnitModel(){
        initValues();
    }
    
    public static UnitModel getInstance(){
        if(unitModel == null){
            unitModel = new UnitModel();
        }
        return unitModel;
    }
    
    private void initValues(){
        list_units = new ArrayList<Unit>();
        list_slaves = new ArrayList<Slave>();
    }
    
    protected Node createSlave(int posX, int posZ){
        Slave slave = new Slave(posX, posZ);
        list_slaves.add(slave);
        return (Node)slave.getSpatial();
    }
    
    protected Node createWarrior(int posX, int posZ, int whichWarrior){
        switch(whichWarrior){
            case GameObjectValues.MELEE:
                System.out.println("UnitModel: createWarrior Melee");
                Melee melee = new Melee(posX, posZ);
                list_units.add(melee);
                return (Node)melee.getSpatial();
            case GameObjectValues.RANGERS:
                break;
            case GameObjectValues.MAGICIAN:
                break;
        }
        return null;
    }
    
    protected Slave getSlave(){
        if(isSlaveAvailable()){
            for(Slave slave: list_slaves){
                if(slave.getSpatial().getControl(SlaveCharacterControl.class).hasOrder()){
                    continue;
                }else{
                    return slave;
                }
            }
            return list_slaves.get(0);
        }
        return null;
    }
    
    protected int getSlaveNumbers(){
        return list_slaves.size();
    }
    
    protected ArrayList<Unit> getUnits(){
        return list_units;
    }
    
    protected boolean isSlaveAvailable() {
        return getSlaveNumbers() > 0 ? true : false;
    }
    
    protected void moveUnitTo(Node unit, int xPos, int zPos){
        unit.getControl(WalkControl.class).findPath(xPos, zPos);
    }

    protected Node removeSlave() {
        if(list_slaves.isEmpty()){
            return null;
        }
        Slave slave = getSlave();
        slave.die();
        list_slaves.remove(slave);
        return (Node)slave.getSpatial();
    }
}
