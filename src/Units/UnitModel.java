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
    private Node node_slaveUnits, node_warriorUnits;
    
    private UnitModel(){
        initValues();
        initUnitNodes();
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
    
    private void initUnitNodes(){
        node_slaveUnits = new Node("SlaveUnits");
        node_warriorUnits = new Node("WarriorUnits");
    }
    
    protected Node createSlave(int posX, int posZ){
        Slave slave = new Slave(posX, posZ);
        list_slaves.add(slave);
        attachSlaveUnit((Node)slave.getSpatial());
        return (Node)slave.getSpatial();
    }
    
    protected Node createWarrior(int posX, int posZ, int whichWarrior){
        switch(whichWarrior){
            case GameObjectValues.MELEE:
                break;
            case GameObjectValues.RANGERS:
                break;
            case GameObjectValues.MAGICIAN:
                break;
        }
        return null;
    }
    
    private void attachSlaveUnit(Node slave){
        System.out.println("UnitModel attachSlaveUnit()");
        this.node_slaveUnits.attachChild(slave);
        System.out.println("UnitModel attachSlaveUnit() : " + node_slaveUnits.getChildren().size());
    }
    
    private void attachWarriorUnit(Node warrior){
        this.node_warriorUnits.attachChild(warrior);
    }
    
    protected List<Spatial> getSlaves(){
        return node_slaveUnits.getChildren();
    }
    
    protected Node getSlave(){
        if(isSlaveAvailable()){
            for(Slave slave: list_slaves){
                if(slave.getSpatial().getControl(SlaveCharacterControl.class).hasOrder()){
                    continue;
                }else{
                    return (Node)slave.getSpatial();
                }
            }
            return (Node)list_slaves.get(0).getSpatial();
        }
        return null;
    }
    
    protected int getSlaveNumbers(){
        //return node_slaveUnits.getChildren().size();
        return list_slaves.size();
    }
    
    protected List<Spatial> getWarriors(){
        return node_warriorUnits.getChildren();
    }
    
    protected int getWarriorNumbers(){
        return node_warriorUnits.getChildren().size();
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
}
