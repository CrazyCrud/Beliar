package beliar;

import com.jme3.app.Application;
import com.jme3.input.MouseInput;
import com.jme3.scene.Node;


public class GameContainer {
    
    //Adresses
        public final static String materialAdress = "Materials/";
    
    
    //Prices BUILDINGS //ADAM//KYTHOS//MARA
        public static final int ADAM_BUILDING = 1;
        public static final int KYTHOS_BUILDING = 2;
        public static final int MARA_BUILDING = 3;
        
        public final static int[] COSTADAMBIG       ={500,0,0};
        public final static int[] COSTADAMMIDDLE    ={200,0,0};
        public final static int[] COSTADAMSMALL     ={100,0,0};

        public final static int[] COSTKYTHOSBIG     ={700,0,0};
        public final static int[] COSTKYTHOSMIDDLE  ={550,0,0};
        public final static int[] COSTKYTHOSSMALL   ={300,0,0};

        public final static int[] COSTMARABIG       ={1500,500,0};
        public final static int[] COSTMARAMIDDLE    ={900,250,0};
        public final static int[] COSTMARASMALL     ={450,150,0};
    
        //GAMETICKER in seconds
    	public final static int UPDATE_PERIOD_SOUL_PRODUCTION = 1;
        public final static int UPDATE_PERIOD_GOODS_PRODUCTION = 10;
        
	//RessourcesProduction
	public final static int ADAMBIG=10;
	public final static int ADAMMIDDLE=5;
	public final static int ADAMSMALL=2;
	
	public final static int KYTHOSBIG=10;
	public final static int KYTHOSMIDDLE=5;
	public final static int KYTHOSSMALL=2;
	
	public final static int MARABIG=5;
	public final static int MARAMIDDLE=3;
	public final static int MARASMALL=1;
        
        //Production: 1Soul = ?
        public final static int SOULADAM=3;
        public final static int SOULKYTHOS =2;
        public final static int SOULMARA=1;
        
	//Darkness
	public final static float darkness=0.5f;
        public final static int freeSouls = 10;
        public final static int soulsRate = 100;
	
	//Science
	public final static int BIGSCIENCE= 20000;
	public final static int MIDDLESCIENCE= 10000;
	public final static int SMALLSCIENCE= 5000;
	
	public final static int healthCentreRengeneration = 1;
        
        public static int SCROLLING_OFFET = 20;
        public static float ZOOM_FACTOR = 0.1f;
        public static int SCREEN_WIDTH, SCREEN_HEIGHT;
        
        private Node rootNode;
        private Application app;
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
        
}
