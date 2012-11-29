package beliar;

import com.jme3.app.Application;
import com.jme3.input.MouseInput;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import java.awt.DisplayMode;
import java.util.LinkedList;
import java.util.List;


public class GameContainer {
    
        //Adresses
        public final static String materialAdress = "Materials/";
    
        //Prices BUILDINGS //ADAM//KYTHOS//MARA
        public static final int ADAM_BUILDING = 1;
        public static final int KYTHOS_BUILDING = 2;
        public static final int MARA_BUILDING = 3;
        public static final int ADAM_SMALL_SIZE = 1;
        public static final int ADAM_MIDDLE_SIZE = 2;
        public static final int ADAM_BIG_SIZE = 3;
        public static final int KYTHOS_SMALL_SIZE = 1;
        public static final int KYTHOS_MIDDLE_SIZE = 2;
        public static final int KYTHOS_BIG_SIZE = 3;
        public static final int MARA_SMALL_SIZE = 1;
        public static final int MARA_MIDDLE_SIZE = 2;
        public static final int MARA_BIG_SIZE = 3;
        public static final int STANDARD_PRODUCTION_REG = 2;
        public static int PRODUCTION_REG = STANDARD_PRODUCTION_REG;
        public final static int COSTSLAVE = 1;
        
        public final static int[] COSTADAMBIG       ={250,0,0};
        public final static int[] COSTADAMMIDDLE    ={100,0,0};
        public final static int[] COSTADAMSMALL     ={50,0,0};

        public final static int[] COSTKYTHOSBIG     ={350,0,0};
        public final static int[] COSTKYTHOSMIDDLE  ={275,0,0};
        public final static int[] COSTKYTHOSSMALL   ={150,0,0};

        public final static int[] COSTMARABIG       ={750,250,0};
        public final static int[] COSTMARAMIDDLE    ={450,125,0};
        public final static int[] COSTMARASMALL     ={225,75,0};
        
        public final static int[] COSTMELEE         ={20,50,10};
        public final static int[] COSTRANGER        ={10,70,30};
        public final static int[] COSTMAGICIAN      ={10,90,60};
    
        //GAMETICKER in seconds
    	public final static int UPDATE_PERIOD_SOUL_PRODUCTION = 10;
        public final static int UPDATE_PERIOD_GOODS_PRODUCTION = 10;
        
	//RessourcesProduction
	public final static int ADAMBIG = 30;
	public final static int ADAMMIDDLE = 20;
	public final static int ADAMSMALL = 10;
	
	public final static int KYTHOSBIG = 35;
	public final static int KYTHOSMIDDLE = 25;
	public final static int KYTHOSSMALL = 10;
	
	public final static int MARABIG = 40;
	public final static int MARAMIDDLE = 15;
	public final static int MARASMALL = 5;
        
        //Production: 1Soul = ?
        public final static int SOULADAM = 3;
        public final static int SOULKYTHOS = 2;
        public final static int SOULMARA = 1;
        
	//Darkness
	public final static float darkness = 0.5f;
        public final static int freeSouls = 10;
        public final static int soulsRate = 100;
	
	//Science
	public final static int BIGSCIENCE = 20000;
	public final static int MIDDLESCIENCE = 10000;
	public final static int SMALLSCIENCE = 5000;
	
	public final static int healthCentreRengeneration = 1;
        
        // Map
        public static int MAP_SIZE = 64;
        public static int SCROLLING_OFFET = 20;
        public static float ZOOM_FACTOR = 0.01f;
        public static int SCREEN_WIDTH, SCREEN_HEIGHT;
        
        //Sounds
        public static final int SLAVE_NOT_REACHABLE = 0;
        public static final int SLAVE_BUILD = 1;
        public static final int SLAVE_BUILD_2 = 2;
        public static final int SLAVE_RANDOM = 3;
        public static final int SLAVE_DIGGING = 4;
        
        // Gamesettings
        public static final String SETTINGS_KEY = "de.beliar";
        
        public static final int QUEST_1 = 0;
        public static final int QUEST_2 = 1;
        public static final int QUEST_3 = 2;
        private LinkedList<String> list_quests = new LinkedList<String>();
        
        private Node rootNode;
        private Application app;
        private AppSettings appSettings;
        private DisplayMode [] displayModes;
        private MouseInput mouseInput;
        private int screenWidth, screenHeight;
        private static GameContainer Instance;
        
        
        
        private GameContainer(){
            // Nothing to do here...
        }
        
        public static GameContainer getInstance(){
            if(Instance == null){
                Instance = new GameContainer();
            }
            
            return Instance;
        }
        
        public void setRootNode(Node rootNode){
            if(rootNode == null){
                return;
            }
            
            this.rootNode = rootNode;
        }
        
        public Node getRootNode(){
            return rootNode;
        }
        
        public void setApplication(Application app){
            if(app == null){
                return;
            }
            
            this.app = app;
        }
        
        public Application getApplication(){
            return app;
        }
        
        public void setMouseInput(MouseInput mouseInput){
            if(mouseInput == null){
                return;
            }
            
            this.mouseInput = mouseInput;
        }
        
        public MouseInput getMouseInput(){
            return mouseInput;
        }
        
        public void setDimensions(int width, int height){
            this.screenWidth = width;
            this.screenHeight = height;
            SCREEN_WIDTH = this.screenWidth;
            SCREEN_HEIGHT = this.screenHeight;
        }
        
        public int getScreenWidth(){
            return screenWidth;
        }
        
        public int getScreenHeight(){
            return screenHeight;
        }
        
        public void addQuest(String quest){
            list_quests.addLast(quest);
        }
        
        public String getQuests(int whichQuest){
            if(whichQuest > -1 && whichQuest < list_quests.size()){
                return list_quests.get(whichQuest);
            }
            return null;
        }

    protected AppSettings getAppSettings() {
        return appSettings;
    }

    protected void setAppSettings(AppSettings appSettings) {
        this.appSettings = appSettings;
    }
    
    protected void setDisplayModes(DisplayMode [] displayModes){
        this.displayModes = displayModes;
    }
    
    protected DisplayMode [] getDisplayModes(){
        return displayModes;
    }
}
