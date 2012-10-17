package beliar;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;


//HIER KOMMT DER TESTCOMITTT!!!!!
public class GameContainer {
    
    //Adresses
    public final static String materialAdress = "Materials/";
    
    //Prices BUILDINGS //ADAM//KYTHOS//MARA
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
	public final static int UPDATE_PERIOD = 15;

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
	
	public final static int healthCentreREngeneration = 1;
        
        
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
        }
        
        public int getScreenWidth(){
            return screenWidth;
        }
        
        public int getScreenHeight(){
            return screenHeight;
        }
        
}
