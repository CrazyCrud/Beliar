package beliar;

import Units.ProductionBuilding;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

public class PlayerRessources {
	
	public static boolean isLost = false;
	
	public static int populationLimit = 0;
	public static int populationCount = 0;
	
	public static int adam = 600; // 200
	public static int kythos = 600; // 100
	public static int mara = 600; // 10
	
	public static int darkness = 20;
	public static int healthCentre = 100;
	
	//BuildingCount
	public static int adamCreatorBIG = 0;
	public static int kythosCreatorBIG = 0;
	public static int maraCreatorBIG = 0;
	
	public static int adamCreatorMIDDLE = 0;
	public static int kythosCreatorMIDDLE = 0;
	public static int maraCreatorMIDDLE = 0;
	
	public static int adamCreatorSMALL = 0;
	public static int kythosCreatorSMALL = 0;
	public static int maraCreatorSMALL = 0;
	
	//Creatures
	public static int creaturesAtDarkness = 0;
        
        //Soulabyss
        public static int soulAbyssOfPlayer = 1;
        private static int soulsCount = 0;
        public static int chanceForSalvation = 0;
         
        //Production
        public static int percentAdam = 30;
        public static int percentKythos = 30;
        public static int percentMara = 30;
        public static int slavesToBuild = 0;
        
        //SelectionRoom/Build
        public static int selectionRoom = 0;
        public static Spatial selectedBuilding = null;
        public static String  loadingStringMaterial = null;
        
	
        //BuildingList
        public static ArrayList<ProductionBuilding> buildings = new ArrayList<ProductionBuilding>();

        public static int getSoulsCount() {
            return soulsCount;
        }

        public static void setSoulsCount(int value) {
            soulsCount += value;
            if(soulsCount < 0){
                soulsCount = 0;
            }
        } 
}
