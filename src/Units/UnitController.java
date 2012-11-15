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
    
    public static Node createSlave(int posX, int posY){
        return UnitModel.getInstance().createSlave(posX, posY);
    }
    
    public static Node createMelee(int posX, int posY){
        return UnitModel.getInstance().createWarrior(posX, posY, GameObjectValues.MELEE);
    }
    
    public static Node createRangers(int posX, int posY){
        return UnitModel.getInstance().createWarrior(posX, posY, GameObjectValues.RANGERS);
    }
    
    public static Node createMagicians(int posX, int posY){
        return UnitModel.getInstance().createWarrior(posX, posY, GameObjectValues.MAGICIAN);
    }
    
    public static ArrayList<Unit> getUnits(){
        return UnitModel.getInstance().getUnits();
    }
}
