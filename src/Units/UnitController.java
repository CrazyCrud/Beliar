/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.SoundManager;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author andministrator
 */
public class UnitController {
    
    public static Node createSlave(int posX, int posZ){
        return UnitModel.getInstance().createSlave(posX, posZ);
    }
    
    public static Node createMelee(int posX, int posZ){
        return UnitModel.getInstance().createWarrior(posX, posZ, GameObjectValues.MELEE);
    }
    
    public static Node createRangers(int posX, int posZ){
        return UnitModel.getInstance().createWarrior(posX, posZ, GameObjectValues.RANGERS);
    }
    
    public static Node createMagicians(int posX, int posZ){
        return UnitModel.getInstance().createWarrior(posX, posZ, GameObjectValues.MAGICIAN);
    }
    
    public static ArrayList<Unit> getMelees(){
        return UnitModel.getInstance().getMelees();
    }
    
    public static ArrayList<Unit> getRangers(){
        return UnitModel.getInstance().getRangers();
    }
    
    public static ArrayList<Unit> getMagicians(){
        return UnitModel.getInstance().getMagicians();
    }
    
    public static boolean isSlaveAvailable(){
        return UnitModel.getInstance().isSlaveAvailable();
    } 
    
    public static Node getSlave(){
        Slave slave = UnitModel.getInstance().getSlave();
        if(slave == null){
            return null;
        }else{
            return (Node)UnitModel.getInstance().getSlave().getSpatial();
        }       
    }
    
    public static Node removeSlave(){
        return UnitModel.getInstance().removeSlave();
    }
    
    public static void moveUnitTo(Node unit, int xPos, int zPos){
        UnitModel.getInstance().moveUnitTo(unit, xPos, zPos);
    }
    
    public static void moveMeleesTo(int xPos, int zPos){
        ArrayList<Unit> melees = getMelees();
        if(melees.isEmpty()){
            return;
        }
        SoundManager.playRandomWarriorSound();
        for(Unit unit: melees){
            moveUnitTo((Node)unit.getSpatial(), xPos, zPos);
        }
    }
    
    public static void moveRangersTo(int xPos, int zPos){
        ArrayList<Unit> rangers = getRangers();
        if(rangers.isEmpty()){
            return;
        }
        for(Unit unit: rangers){
            moveUnitTo((Node)unit.getSpatial(), xPos, zPos);
        }
    }
    
    public static void moveMagiciansTo(int xPos, int zPos){
        ArrayList<Unit> magicians = getMagicians();
        if(magicians.isEmpty()){
            return;
        }
        for(Unit unit: magicians){
            moveUnitTo((Node)unit.getSpatial(), xPos, zPos);
        }
    }

    public static void markUnits(int whichUnit) {
        UnitModel.getInstance().markUnits(whichUnit);
    }
    
    public static void unmarkUnits(int whichUnit){
        UnitModel.getInstance().unmarkUnits(whichUnit);
    }
}
