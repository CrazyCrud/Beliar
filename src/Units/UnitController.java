/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

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
    
    public static ArrayList<Unit> getUnits(){
        return UnitModel.getInstance().getUnits();
    }
    
    public static boolean isSlaveAvailable(){
        return UnitModel.getInstance().isSlaveAvailable();
    } 
    
    public static Node getSlave(){
        return UnitModel.getInstance().getSlave();
    }
    
    public static void moveUnitTo(Node unit, int xPos, int zPos){
        UnitModel.getInstance().moveUnitTo(unit, xPos, zPos);
    }
}
