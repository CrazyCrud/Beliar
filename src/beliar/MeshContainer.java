/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.util.HashMap;

public class MeshContainer {

    private AssetManager assetManager;
    
    
    public HashMap<String,Spatial> limbo= new HashMap<String, Spatial>();
    
    public HashMap<String,Spatial> adamHall= new HashMap<String, Spatial>();
    public HashMap<String,Spatial> caveOfTheBeast= new HashMap<String, Spatial>();
    public HashMap<String,Spatial> tombOfMemory= new HashMap<String, Spatial>();
    
    public HashMap<String,Spatial> farm= new HashMap<String, Spatial>();
    public HashMap<String,Spatial> chamberOfPleasure= new HashMap<String, Spatial>();
    
    public HashMap<String,Spatial> libary= new HashMap<String, Spatial>();
    public HashMap<String,Spatial> workshop= new HashMap<String, Spatial>();
    public HashMap<String,Spatial> soulChamber= new HashMap<String, Spatial>();
    
    
    public Spatial hellCenter;
    public Spatial slave;
    //TESTELEPHANT
    public Spatial mesh;
        
        
    public MeshContainer(AssetManager assetManager) {
        this.assetManager = assetManager;
        initLimbo();
        initHallOfAnarchy();
        initCaveOfTheBeast();
        initTombOfMemories();
        initUnits();
        
       hellCenter = assetManager.loadModel("Scenes/hellcenter.j3o");
    }
    
    private void initUnits()
    {
        slave = assetManager.loadModel("Scenes/Units/slave.j3o");
    }
    private void initLimbo()
    {
        //RoomParts     "Scenes/limbo/room/[...]"
        limbo.put("straigt", assetManager.loadModel("Scenes/limbo/room/limboStraight.j3o"));
        limbo.put("curve", assetManager.loadModel("Scenes/limbo/room/limboCurve.j3o"));
        limbo.put("3cross", assetManager.loadModel("Scenes/limbo/room/limbo3cross.j3o"));
        limbo.put("4cross", assetManager.loadModel("Scenes/limbo/room/limbo4cross.j3o"));
        limbo.put("side", assetManager.loadModel("Scenes/limbo/room/limboSide.j3o"));
        limbo.put("end", assetManager.loadModel("Scenes/limbo/room/limboEnd.j3o"));
    }
    
    private void initHallOfAnarchy()
    {
        //RoomParts
        adamHall.put("straigt", assetManager.loadModel("Scenes/adamHall/room/adamStraight.j3o"));
        adamHall.put("curve",   assetManager.loadModel("Scenes/adamHall/room/adamCurve.j3o"));
        adamHall.put("3cross",  assetManager.loadModel("Scenes/adamHall/room/adam3cross.j3o"));
        adamHall.put("4cross",  assetManager.loadModel("Scenes/adamHall/room/adam4cross.j3o"));
        adamHall.put("side",    assetManager.loadModel("Scenes/adamHall/room/adamSide.j3o"));
        adamHall.put("end",     assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
        
        //Buildings
        adamHall.put("production1", assetManager.loadModel("Scenes/adamHall/buildings/adamBuilding_1.j3o"));
        adamHall.put("production2", assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
        adamHall.put("production3", assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
    }
    
    private void initCaveOfTheBeast()
    {
        //RoomParts
        caveOfTheBeast.put("straigt", assetManager.loadModel("Scenes/kythosHall/room/kythosStraight.j3o"));
        caveOfTheBeast.put("curve",   assetManager.loadModel("Scenes/kythosHall/room/kythosCurve.j3o"));
        caveOfTheBeast.put("3cross",  assetManager.loadModel("Scenes/kythosHall/room/kythos3cross.j3o"));
        caveOfTheBeast.put("4cross",  assetManager.loadModel("Scenes/kythosHall/room/kythos4cross.j3o"));
        caveOfTheBeast.put("side",    assetManager.loadModel("Scenes/kythosHall/room/kythosSide.j3o"));
        caveOfTheBeast.put("end",     assetManager.loadModel("Scenes/kythosHall/room/kythosEnd.j3o"));
        
        //Buildings
        caveOfTheBeast.put("production1", assetManager.loadModel("Scenes/kythosHall/buildings/kythosBuilding_1.j3o"));
        caveOfTheBeast.put("production2", assetManager.loadModel("Scenes/kythosHall/buildings/kythosBuilding_2.j3o"));
        caveOfTheBeast.put("production3", assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
    }
    
    private void initTombOfMemories()
    {
        //RoomParts
        tombOfMemory.put("straigt", assetManager.loadModel("Scenes/maraHall/room/maraStraight.j3o"));
        tombOfMemory.put("curve",   assetManager.loadModel("Scenes/maraHall/room/maraCurve.j3o"));
        tombOfMemory.put("3cross",  assetManager.loadModel("Scenes/maraHall/room/mara3cross.j3o"));
        tombOfMemory.put("4cross",  assetManager.loadModel("Scenes/maraHall/room/mara4cross.j3o"));
        tombOfMemory.put("side",    assetManager.loadModel("Scenes/maraHall/room/maraSide.j3o"));
        tombOfMemory.put("end",     assetManager.loadModel("Scenes/maraHall/room/maraEnd.j3o"));
        
        //tombOfMemory.put("production1", assetManager.loadModel("Scenes/maraHall/buildings/maraBuilding_1.j3o"));
        //tombOfMemory.put("production2", assetManager.loadModel("Scenes/maraHall/buildings/maraBuilding_2.j3o"));
        //tombOfMemory.put("production3", assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
        
        tombOfMemory.put("production1", assetManager.loadModel("Scenes/kythosHall/buildings/kythosBuilding_1.j3o"));
        tombOfMemory.put("production2", assetManager.loadModel("Scenes/kythosHall/buildings/kythosBuilding_2.j3o"));
        tombOfMemory.put("production3", assetManager.loadModel("Scenes/adamHall/room/adamEnd.j3o"));
        
    }
}
