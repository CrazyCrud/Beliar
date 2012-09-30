package Container;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;


//HIER KOMMT DER TESTCOMITTT!!!!!
public class GameContainer {
    
    //Prices BUILDINGS //ADAM//KYTHOS//MARA
        public final static int[] COSTADAMBIG       ={100,0,0};
        public final static int[] COSTADAMMIDDLE    ={100,0,0};
        public final static int[] COSTADAMSMALL     ={100,0,0};

        public final static int[] COSTKYTHOSBIG     ={100,0,0};
        public final static int[] COSTKYTHOSMIDDLE  ={100,0,0};
        public final static int[] COSTKYTHOSSMALL   ={100,0,0};

        public final static int[] COSTMARABIG       ={100,0,0};
        public final static int[] COSTMARAMIDDLE    ={100,0,0};
        public final static int[] COSTMARASMALL     ={100,0,0};
    
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

	//Darkness
	public final static float darkness=0.5f;
	
	//Science
	public final static int BIGSCIENCE= 20000;
	public final static int MIDDLESCIENCE= 10000;
	public final static int SMALLSCIENCE= 5000;
	
	public final static int healthCentreREngeneration = 1;
        
        
        private Node rootNode;
        private Application app;
        private MouseInput mouseInput;
        
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
}
