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
        this.node_slaveUnits.attachChild(slave);
    }
    
    private void attachWarriorUnit(Node warrior){
        this.node_warriorUnits.attachChild(warrior);
    }
    
    protected List<Spatial> getSlaves(){
        return node_slaveUnits.getChildren();
    }
    
    protected int getSlaveNumbers(){
        return node_slaveUnits.getChildren().size();
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
    
    protected void moveUnitTo(Node unit, int xPos, int zPos){
        unit.getControl(WalkControl.class).findPath(xPos, zPos);
    }
}
