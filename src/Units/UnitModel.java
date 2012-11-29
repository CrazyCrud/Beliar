/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.GameContainer;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
/**
 *
 * @author andministrator
 */
public class UnitModel {
    
    private static UnitModel unitModel;

    private ArrayList<Unit> list_melee, list_ranger, list_magician;
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
        list_melee = new ArrayList<Unit>();
        list_ranger = new ArrayList<Unit>();
        list_magician = new ArrayList<Unit>();
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
        /*
         * Box bosShape = new Box(Vector3f.ZERO, 
                0.10f, 0.10f, 0.10f);
        Geometry geo = new Geometry("unitMarker", bosShape);
        Material mat = new Material(GameContainer.getInstance().getApplication().getAssetManager(), 
                "MatDefs/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        geo.setMaterial(mat);
        geo.move(0.0f, 0.95f, 0.0f);
         * 
         */
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
                for(Unit unit: list_melee){
                    System.out.println("UnitModel: markUnits() melee: " + unit);
                    Node melee = (Node)unit.getSpatial();
                    melee.attachChild(marker.clone());
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
}
