/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.GameContainer;
import beliar.SoundManager;
import com.jme3.scene.Node;
import java.util.ArrayList;
/**
 *
 * @author andministrator
 */
public class UnitModel {
    
    private static UnitModel unitModel;

    private ArrayList<Unit> list_melee, list_ranger, list_magician;
    private ArrayList<Slave> list_slaves;
    private int int_warriorsRisedUp;
    
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
        list_melee = new ArrayList<Unit>();
        list_ranger = new ArrayList<Unit>();
        list_magician = new ArrayList<Unit>();
        list_slaves = new ArrayList<Slave>();
        int_warriorsRisedUp = 0;
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
                list_melee.add(melee);
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
    
    protected ArrayList<Unit> getMelees(){
        return list_melee;
    }
    
    protected ArrayList<Unit> getRangers(){
        return list_ranger;
    }
    
    protected ArrayList<Unit> getMagicians(){
        return list_magician;
    }
    
    protected boolean isSlaveAvailable() {
        return getSlaveNumbers() > 0 ? true : false;
    }
    
    protected void moveUnitTo(Node unit, int xPos, int zPos){
        if(unit == null || unit.getControl(WalkControl.class) == null){
            return;
        }
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
    
    protected void markUnits(int whichUnit) {
        Node marker = (Node)GameContainer.getInstance().getApplication().getAssetManager().
                loadModel("Models/banner/banner.j3o");
        marker.setName("unitMarker");
        marker.move(0.0f, 2.0f, 0.0f);
        marker.scale(0.5f);
        switch(whichUnit){
            case GameObjectValues.MELEE:
                if(list_melee.isEmpty()){
                    return;
                }
                boolean areWarriorsOnField = false;
                for(Unit unit: list_melee){
                    System.out.println("UnitModel: markUnits() melee: " + unit);
                    Node melee = (Node)unit.getSpatial();
                    if(melee.getControl(WarriorBehaviourControl.class) == null){
                        continue;
                    }
                    areWarriorsOnField = true;
                    melee.attachChild(marker.clone());
                }
                if(areWarriorsOnField){
                    SoundManager.playWarriorMarkedSound();
                }
                break;
            case GameObjectValues.RANGERS:
                if(list_ranger.isEmpty()){
                    return;
                }
                for(Unit unit: list_ranger){
                    Node ranger = (Node)unit.getSpatial();
                    ranger.attachChild(marker.clone());
                }
                break;
            case GameObjectValues.MAGICIAN:
                if(list_magician.isEmpty()){
                    return;
                }
                for(Unit unit: list_magician){
                    Node magician = (Node)unit.getSpatial();
                    magician.attachChild(marker.clone());
                }
                break;
        }
    }

    protected void unmarkUnits(int whichUnit) {
        switch(whichUnit){
            case GameObjectValues.MELEE:
                if(list_melee.isEmpty()){
                    return;
                }
                for(Unit unit: list_melee){
                    Node melee = (Node)unit.getSpatial();
                    melee.detachChildNamed("unitMarker");
                }
                break;
            case GameObjectValues.RANGERS:
                if(list_ranger.isEmpty()){
                    return;
                }
                for(Unit unit: list_ranger){
                    Node ranger = (Node)unit.getSpatial();
                    ranger.detachChildNamed("unitMarker");
                }
                break;
            case GameObjectValues.MAGICIAN:
                if(list_magician.isEmpty()){
                    return;
                }
                for(Unit unit: list_magician){
                    Node magician = (Node)unit.getSpatial();
                    magician.detachChildNamed("unitMarker");
                }
                break;
        }
    }

    protected void notiftyWarriorRise() {
        int_warriorsRisedUp++;
    }
    
    protected int getWarriorsRisedUp() {
        return int_warriorsRisedUp;
    }
}
