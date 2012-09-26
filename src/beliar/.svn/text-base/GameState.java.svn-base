/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.collision.CollisionResults;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.debug.Grid;
import com.jme3.shadow.PssmShadowRenderer;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author andministrator
 */
public class GameState extends AbstractAppState{
    
    // DEBUG
    private int buildingType = 3;
    
    private ViewPort viewPort;
    private Node rootNode;
    private Node localRootNode = new Node("GameScreen Node");
    private Node guiNode;
    private AssetManager assetManager;

    private Camera mCam;
    private int scrollArea;
    private int scrollSpeed;
    //Light&Shadow
    private DirectionalLight directionLight;
    private PointLight cameraLight;
    private PssmShadowRenderer shadowMaker;
   
    //Meshes
    private MeshContainer mMeshContainer;
    
    
    //GAME
    private InGameInputs inGameInputs;
    private GameSimulation gameSimulation;
    
    private Node pickAble;
    private Node creatures;
    private Node buildings;
    private Node mapNode;
    private Node marker;
    
    private RTSCam rtsCam;
    
    
    private MapHandler mMaphandler;
    
    private SoundManager mSoundManager;
    
    private SimpleApplication app;
    private MouseInput mouseInput;
    private InputManager inputManager;
    private AudioRenderer audioRenderer;
    private AppStateManager stateManager;
    private Camera cam;
    private FlyByCamera flyCam;
    
    private ViewPort guiViewPort;
    
    //INPUT
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    private ScreenManager screenManager;
    
    
 public GameState(AppStateManager stateManager, SimpleApplication app){
        System.out.println("GameState");  
        this.app = (SimpleApplication) ((SimpleApplication)app);
        this.rootNode = ((SimpleApplication)app).getRootNode();
        this.viewPort = ((SimpleApplication)app).getViewPort();
        this.guiNode = ((SimpleApplication)app).getGuiNode();
        this.assetManager = ((SimpleApplication)app).getAssetManager();
        this.inputManager = ((SimpleApplication)app).getInputManager();
        this.audioRenderer = ((SimpleApplication)app).getAudioRenderer();
        this.guiViewPort = ((SimpleApplication)app).getGuiViewPort();
        this.cam = ((SimpleApplication)app).getCamera();
        this.flyCam = ((SimpleApplication)app).getFlyByCamera();
        this.stateManager = stateManager;
        this.inputManager.setCursorVisible(true); 
        GameContainer gameContainer = GameContainer.getInstance();
        this.mouseInput = gameContainer.getMouseInput(); 
        this.screenManager = ScreenManager.getScreenManager();
        
        initStates();
    }
    
    private void initStates(){
        System.out.println("GameState: initStates()");
        inGameInputs = new InGameInputs(stateManager, app);
        gameSimulation = new GameSimulation(stateManager, app);  
    }
 
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        System.out.println("GameState: initialize()");  
        
        super.initialize(stateManager,app);  
       
        initGame();
    }
    
    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("GameState: setEnabled");
            showInput();
        }else{
            System.out.println("GameState: setEnabled(false)");
            
        }
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("GameState: stateAttached()");
        rootNode.attachChild(localRootNode);
        stateManager.attach(inGameInputs);
        stateManager.attach(gameSimulation);
        //showInput();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager)
    {
        System.out.println("GameState: stateDetached()");
        rootNode.detachChild(localRootNode);
        localRootNode.detachAllChildren();
        stateManager.detach(inGameInputs);
        stateManager.detach(gameSimulation);
        stateManager.detach(this);
    }
    
    @Override
    public void update(float tpf){
        if(isEnabled()){
            Vector3f position = rtsCam.getPosition();
            
            position.x+=3;
            position.z-=2;
            cameraLight.setPosition(position);
        }else{
            
        }
    }

    private void showInput(){
        System.out.println("GameState: showInput()");
        screenManager.switchToGameScreen(inGameInputs);
        /*
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
        nifty = niftyDisplay.getNifty();
        
        nifty.registerScreenController(inGameInputs);
        nifty.addXml("Interface/Hud.xml");
        nifty.gotoScreen("inGameInputs");
        guiViewPort.addProcessor(niftyDisplay);
         * */
    }
    
    private void removeInput(){
        
    }
    
    private void initGame(){
        System.out.println("GameState: initGame()");
        initCamera();
        initKeys();
        
        initLight();
        
        initSceneNodes();
        initMeshLibary();
        
        initMap();
        initScene();
        initSound();

       // attachGrid(new Vector3f(0, 0, 0), 1024, ColorRGBA.Blue);
       // attachCoordinateAxes(new Vector3f(-1, 0, -1));
    }
    
    private void attachGrid(Vector3f pos, int size, ColorRGBA color){
      Geometry g = new Geometry("wireframe grid", new Grid(size, size, 0.2f) );
      Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      mat.getAdditionalRenderState().setWireframe(true);
      mat.setColor("Color", color);
      
      g.setMaterial(mat);
      g.center().move(pos);
      localRootNode.attachChild(g);
    }
    
    private void initCamera()
    {
        rtsCam = new RTSCam(cam, localRootNode);
       
        System.out.println("InitCamera");
        flyCam.setEnabled(false);
        
         
        rtsCam.registerWithInput(inputManager);
        rtsCam.setCenter(new Vector3f(20,0.5f,20));


        //Fog
        FilterPostProcessor fpp=new FilterPostProcessor(assetManager);
        FogFilter fog=new FogFilter();
        fog.setFogColor(new ColorRGBA(0, 0, 0, 1.0f));
        fog.setFogDistance(50);
        fog.setFogDensity(5.5f);
        fpp.addFilter(fog);
        viewPort.addProcessor(fpp);
        
    }
    
    private void initLight()
    {
        System.out.println("InitLight");
        float radiusCameraLight = 2.0f;
        ColorRGBA lightColor = new ColorRGBA(0.8f, 0.4f, 0.2f,1.0f);
        Vector3f lightDirection= new Vector3f(-1, -1, -1);
        
      //DirectionalLight
        directionLight = new DirectionalLight();
        directionLight.setColor(lightColor);
        directionLight.setName("Light_Main");
        directionLight.setDirection(lightDirection);
        localRootNode.addLight(directionLight);
      
      //CameraLight
        cameraLight = new PointLight();
        cameraLight.setColor(lightColor);
        cameraLight.setPosition(rtsCam.getPosition());
        cameraLight.setName("CameraLight");
        cameraLight.setRadius(radiusCameraLight);
        localRootNode.addLight(cameraLight);
      
      
      //Shadow
        shadowMaker = new PssmShadowRenderer(assetManager, 1024, 3);
        shadowMaker.setDirection(lightDirection);
        viewPort.addProcessor(shadowMaker);
      
      //AmbientLight
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        localRootNode.addLight(al);

    }
    
    private void initMeshLibary()
    {
        //Aufgabe: Alle Objekte werden geladen und in der Meshbiliothek hinterlegt
        System.out.println("InitMesh");
        mMeshContainer = new MeshContainer(assetManager);
    }
    
    private void initSceneNodes()
    {
        pickAble = new Node("pickable");
        creatures = new Node("creatures");
        marker = new Node("marker");
        mapNode = new Node("mapNode");
        buildings = new Node("buildings");
    }
    
    private void initMap()
    {
        System.out.println("InitMap");    
        
        //MapHanderl ergaenzen mit String
        mMaphandler = new MapHandler(mMeshContainer,mapNode,assetManager);  
    }
    private void initSound()
    {
        mSoundManager = new SoundManager(assetManager, localRootNode);
        mSoundManager.playMusic("01");
    }
    private void initScene()
    {   //setzt die Objekte entsprechend der Vorgaben aus der Map
        System.out.println("InitScene");

       
        
        //Markers
        pickAble.attachChild(mapNode);
        pickAble.attachChild(buildings);
        pickAble.attachChild(marker);
        
        localRootNode.attachChild(pickAble);
        localRootNode.attachChild(creatures);
    }
    
    
    private void attachCoordinateAxes(Vector3f pos){
      Arrow arrow = new Arrow(Vector3f.UNIT_X);
      arrow.setLineWidth(4); // make arrow thicker
      putShape(arrow, ColorRGBA.Red).setLocalTranslation(pos);

      arrow = new Arrow(Vector3f.UNIT_Y);
      arrow.setLineWidth(4); // make arrow thicker
      putShape(arrow, ColorRGBA.Green).setLocalTranslation(pos);

      arrow = new Arrow(Vector3f.UNIT_Z);
      arrow.setLineWidth(4); // make arrow thicker
      putShape(arrow, ColorRGBA.Blue).setLocalTranslation(pos);
    }

    private Geometry putShape(Mesh shape, ColorRGBA color){
      Geometry g = new Geometry("coordinate axis", shape);
      Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      mat.getAdditionalRenderState().setWireframe(true);
      mat.setColor("Color", color);
      g.setMaterial(mat);
      localRootNode.attachChild(g);
      return g;
    }

    private void initKeys()
    {
        inputManager.addMapping("pick target", 
                
                new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(0));
       
        inputManager.addListener(actionListener, "pick target");
        
        inputManager.addMapping("mouseMove",
                new MouseAxisTrigger(mouseInput.AXIS_X, false),
                new MouseAxisTrigger(mouseInput.AXIS_Y, false));
        
    } 
    
    private AnalogListener actionListener = new AnalogListener() {

        public void onAnalog(String name, float value, float tpf) {
            
            CollisionResults results = new CollisionResults();
            
            Vector2f click2D = inputManager.getCursorPosition();
            Vector3f click3D = cam.getWorldCoordinates(new Vector2f(click2D.x, click2D.y), 0f);
            Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2D.x, click2D.y), 1f).subtractLocal(click3D).normalizeLocal();
            
            Ray ray = new Ray(click3D,dir);
            pickAble.collideWith(ray, results);
            Vector3f pt = new Vector3f();
            for (int i = 0; i < results.size(); i++) {
            
            // (For each “hit”, we know distance, impact point, geometry.)
            float dist = results.getCollision(i).getDistance();
             pt = results.getCollision(i).getContactPoint();
            String target = results.getCollision(i).getGeometry().getName();
            
            System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
            
            }
            
            
            if(results.size() > 0)
            {
                Geometry target = results.getClosestCollision().getGeometry();
                
                //Handling      
                Vector3f position = target.getLocalTranslation();
                com.jme3.math.Transform transformation = target.getWorldTransform();
                
                System.out.println("PositionMAIN"+transformation.toString());
                mMaphandler.handleBuilding(transformation.getTranslation(),buildingType);
                mapNode.detachChild(target);
            }
            
            if(results.size()<=0)
            {
              System.out.println("No Selection");
            }
            
            else
            {
                results.clear();
            }
            
        }
    };
    
    public void buildSomething(int key){
        
        System.out.println("buildSomething");
        
        // TODO: OpenBuildMenu
        buildingType = key;
        
    }
}
