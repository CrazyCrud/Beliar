/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import Units.ProductionBuilding;
import beliar.GameContainer;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import com.jme3.collision.CollisionResults;
import com.jme3.collision.bih.BIHNode;
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
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.PssmShadowRenderer;
import controller.mapController;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author andministrator
 */
public class GameState extends AbstractAppState {

    private ViewPort viewPort;
    private Node rootNode;
    private Node localRootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private Camera mCam;
    private int scrollArea;
    private int scrollSpeed;
    //Light&Shadow
    private DirectionalLight directionLight;
    private PointLight cameraLight;
    private PssmShadowRenderer shadowMaker;
    private FilterPostProcessor fpp;
    //Meshes
    private MeshContainer mMeshContainer;
    private Spatial selection;
    private Vector3f mousePositionWorld = new Vector3f(0, 0, 0);
    private int typeBuildingRoom = 0;
    //Animation
    private AnimChannel channel;
    private AnimControl control;
    //GAME
    private InGameInputs inGameInputs;
    private GameSimulation gameSimulation;
    private Node pickAble;
    private Node creatures;
    private Node buildings;
    private Node mapNode;
    private Node marker;
    private RTSCam rtsCam;
    private mapController mMapController;
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

    public GameState(AppStateManager stateManager, SimpleApplication app) {
        System.out.println("GameState");
        this.app = (SimpleApplication) ((SimpleApplication) app);
        this.rootNode = ((SimpleApplication) app).getRootNode();
        this.viewPort = ((SimpleApplication) app).getViewPort();
        this.guiNode = ((SimpleApplication) app).getGuiNode();
        this.assetManager = ((SimpleApplication) app).getAssetManager();
        this.inputManager = ((SimpleApplication) app).getInputManager();
        this.audioRenderer = ((SimpleApplication) app).getAudioRenderer();
        this.guiViewPort = ((SimpleApplication) app).getGuiViewPort();
        this.cam = ((SimpleApplication) app).getCamera();
        this.flyCam = ((SimpleApplication) app).getFlyByCamera();
        this.stateManager = stateManager;
        this.inputManager.setCursorVisible(true);
        GameContainer gameContainer = GameContainer.getInstance();
        this.mouseInput = gameContainer.getMouseInput();
        this.screenManager = ScreenManager.getScreenManager();

        initValues();
        initStates();
    }

    private void initValues() {
        localRootNode = new Node("GameScreen Node");
    }

    private void initStates() {
        System.out.println("GameState: initStates()");
        inGameInputs = new InGameInputs(stateManager, app, this);
        gameSimulation = new GameSimulation(stateManager, app);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        System.out.println("GameState: initialize()");

        super.initialize(stateManager, app);


    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            System.out.println("GameState: setEnabled");
            showInput();
        } else {
            System.out.println("GameState: setEnabled(false)");

        }
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("GameState: stateAttached()");
        rootNode.attachChild(localRootNode);
        stateManager.attach(inGameInputs);
        stateManager.attach(gameSimulation);
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        System.out.println("GameState: stateDetached()");

        localRootNode.removeFromParent();
        localRootNode.detachAllChildren();
        rootNode.detachChild(localRootNode);

        inputManager.clearMappings();
        inputManager.removeListener(actionListener);
        viewPort.clearScenes();
        viewPort.removeProcessor(fpp);
        viewPort.removeProcessor(shadowMaker);

        stateManager.detach(stateManager.getState(InGameInputs.class));
        stateManager.detach(stateManager.getState(GameSimulation.class));
    }

    @Override
    public void update(float tpf) {
        if (isEnabled()) {
            Vector3f position = rtsCam.getPosition();

            position.x += 3;
            position.z -= 2;
            cameraLight.setPosition(position);
            inGameInputs.ressourcesChanged();

            if (selection != null) {
                System.out.println("Ich rendere ein Gebaude");
                selection.setLocalTranslation(mousePositionWorld);

            }

        } else {
        }
    }

    public void initializeGame() {
        initGame();
    }

    private void showInput() {
        System.out.println("GameState: showInput()");
        screenManager.switchToGameScreen(inGameInputs);
    }

    private void initGame() {
        System.out.println("GameState: initGame()");
        initCamera();
        initKeys();

        initLight();

        initSceneNodes();
        initMeshLibary();

        initMap();
        initScene();
        initSound();

        attachGrid(new Vector3f(0, 0, 0), 1024, ColorRGBA.Blue);
        attachCoordinateAxes(new Vector3f(-1, 0, -1));
    }

    private void attachGrid(Vector3f pos, int size, ColorRGBA color) {
        Geometry g = new Geometry("wireframe grid", new Grid(size, size, 0.2f));
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", color);

        g.setMaterial(mat);
        g.center().move(pos);
        localRootNode.attachChild(g);
    }

    private void initCamera() {
        rtsCam = new RTSCam(cam, localRootNode);

        System.out.println("InitCamera");
        flyCam.setEnabled(false);


        rtsCam.registerWithInput(inputManager);
        rtsCam.setCenter(new Vector3f(20, 0.5f, 20));


        //Fog
        fpp = new FilterPostProcessor(assetManager);
        FogFilter fog = new FogFilter();
        fog.setFogColor(new ColorRGBA(0, 0, 0, 1.0f));
        fog.setFogDistance(50);
        fog.setFogDensity(5.5f);
        fpp.addFilter(fog);
        viewPort.addProcessor(fpp);

    }

    private void initLight() {
        System.out.println("InitLight");
        float radiusCameraLight = 2.0f;
        ColorRGBA lightColor = new ColorRGBA(0.8f, 0.4f, 0.2f, 1.0f);
        Vector3f lightDirection = new Vector3f(-1, -1, -1);

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

    private void initMeshLibary() {
        //Aufgabe: Alle Objekte werden geladen und in der Meshbiliothek hinterlegt
        System.out.println("InitMesh");
        mMeshContainer = new MeshContainer(assetManager);
    }

    private void initSceneNodes() {
        pickAble = new Node("pickable");
        creatures = new Node("creatures");
        marker = new Node("marker");
        mapNode = new Node("mapNode");
        buildings = new Node("buildings");

        pickAble.addLight(cameraLight);
        pickAble.addLight(directionLight);

        creatures.addLight(cameraLight);
        creatures.addLight(directionLight);

        marker.addLight(cameraLight);
        marker.addLight(directionLight);

        mapNode.addLight(cameraLight);
        mapNode.addLight(directionLight);

        buildings.addLight(cameraLight);
        buildings.addLight(directionLight);
    }

    private void initMap() {
        System.out.println("InitMap");

        //MapHanderl ergaenzen mit String
        mMaphandler = new MapHandler(mMeshContainer, mapNode, assetManager);
    }

    private void initSound() {
        mSoundManager = new SoundManager(assetManager, localRootNode);
        mSoundManager.playMusic("03");
    }

    private void initScene() {   //setzt die Objekte entsprechend der Vorgaben aus der Map
        System.out.println("InitScene");

        //Markers
        pickAble.attachChild(mapNode);
        pickAble.attachChild(buildings);

        localRootNode.attachChild(marker);
        localRootNode.attachChild(pickAble);
        localRootNode.attachChild(creatures);

    }

    private void attachCoordinateAxes(Vector3f pos) {
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

    private Geometry putShape(Mesh shape, ColorRGBA color) {
        Geometry g = new Geometry("coordinate axis", shape);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", color);
        g.setMaterial(mat);
        localRootNode.attachChild(g);
        return g;
    }

    private void initKeys() {
        inputManager.addMapping("pick target",
                new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(0));

        inputManager.addMapping("moveMouse",
                new MouseAxisTrigger(mouseInput.AXIS_X, true),
                new MouseAxisTrigger(mouseInput.AXIS_Y, true));


        inputManager.addListener(actionListener, "pick target");
        inputManager.addListener(actionListener, "moveMouse");



    }
    private AnalogListener actionListener = new AnalogListener() {

        public void onAnalog(String name, float value, float tpf) {

            if (name.equals("pick target")) {


                CollisionResults results = checkColision();

                //Raum ausheben//
                if ((results.size() > 0) && (PlayerRessources.selectionRoom > 0)) {
                   
                    Geometry target = results.getClosestCollision().getGeometry();

                    com.jme3.math.Transform transformation = target.getWorldTransform();

                    mMaphandler.handleBuilding(transformation.getTranslation(), PlayerRessources.selectionRoom);
                    mapNode.detachChild(target);
                    if(PlayerRessources.selectedBuilding!=null)
                    {
                    stopBuilding();
                    }
                } //gebaude bauen
                else if ((results.size() > 0) && (selection != null)) {
                    System.out.println("Gebäude platzieren?");

                    int[] bill = GameContainer.COSTADAMSMALL;

                    if ((checkFieldType((int) mousePositionWorld.x, (int) mousePositionWorld.z) == true) && (gameSimulation.checkCostsBuilding(bill) == true)) {
                        selection = PlayerRessources.selectedBuilding;
                        if (selection == null) {
                            System.out.println("!!!ERROR!!!");
                        }
                        selection.setMaterial(assetManager.loadMaterial(PlayerRessources.loadingStringMaterial));
                        //buildings.attachChild(selection);
                        
                        ProductionBuilding myBuild = new ProductionBuilding(selection,assetManager.loadMaterial(PlayerRessources.loadingStringMaterial), (int)mousePositionWorld.x, (int)mousePositionWorld.z,100);
                        myBuild.setActive(true);
                        //PlayerRessources.buildings.add(myBuild);
                        Node selectionToBuild = (Node) selection;
                        buildings.attachChild(selectionToBuild);
                        stopBuilding();
                        gameSimulation.reduceRessources(bill);
                        mSoundManager.playUISound("placeBuilding");
                        mMaphandler.placeBuilding((int) mousePositionWorld.x, (int) mousePositionWorld.z);
                        inGameInputs.ressourcesChanged();
                    } else {
                        System.out.println("Bauen verweigert");
                    }
                }


                if (results.size() <= 0) {
                    System.out.println("No Selection");
                } else {
                    results.clear();
                }
            } else if (name.equals("moveMouse")) {
                CollisionResults results = checkColision();

                if ((results.size() > 0) && selection != null) {
                    Geometry target = results.getClosestCollision().getGeometry();
                    mousePositionWorld.set(target.getWorldTranslation());
                    checkFieldType((int) mousePositionWorld.x, (int) mousePositionWorld.z);
                }
            }
        }
    };

    public void handleBuildSelection(char typeRoom, int size) {

        System.out.println("HandleBuildSelection");


        String loadingString = "production".concat(String.valueOf(size));
        String loadingStringMaterial;
        switch (typeRoom) {
            case 'a':
                System.out.println("ADAM" + loadingString);
                typeBuildingRoom = ValuesTerrain.HALLOFANARCHY;
                System.out.println("ADAMMATERIAL:");


                PlayerRessources.selectedBuilding = mMeshContainer.adamHall.get(loadingString).clone();
                loadSelectedMaterial("adamBuilding_", size);



                break;

            case 'k':
                System.out.println("KYTHOS" + loadingString);
                typeBuildingRoom = ValuesTerrain.CAVEOFBEAST;
                loadSelectedMaterial("kythosBuilding_", size);
                PlayerRessources.selectedBuilding = mMeshContainer.caveOfTheBeast.get(loadingString).clone();

                break;

            case 'm':
                System.out.println("MARA" + loadingString);
                typeBuildingRoom = ValuesTerrain.TOMBOFMEMORY;
                PlayerRessources.selectedBuilding = mMeshContainer.tombOfMemory.get(loadingString).clone();

                break;

        }
        PlayerRessources.selectionRoom = 0;
        initBuildSelection();


    }

    private void loadSelectedMaterial(String type, int size) {
        PlayerRessources.loadingStringMaterial = GameContainer.materialAdress.concat(type + String.valueOf(size) + ".j3m");
    }

    private void initBuildSelection() {
        System.out.println("initBuildingSelection");
        PlayerRessources.selectedBuilding.addLight(directionLight);
        PlayerRessources.selectedBuilding.addLight(cameraLight);
        selection = PlayerRessources.selectedBuilding;
        selection.move(mousePositionWorld);

        if (selection != null) {
            marker.attachChild(selection);
        } else {
            System.err.println("ERROR DETECTED!");
        }
    }

    private CollisionResults checkColision() {
        CollisionResults results = new CollisionResults();

        //System.out.println("MOUSEMOVE");
        Vector2f click2D = inputManager.getCursorPosition();
        Vector3f click3D = cam.getWorldCoordinates(new Vector2f(click2D.x, click2D.y), 0f);
        Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2D.x, click2D.y), 1f).subtractLocal(click3D).normalizeLocal();

        Ray ray = new Ray(click3D, dir);
        pickAble.collideWith(ray, results);
        Vector3f pt = new Vector3f();
        for (int i = 0; i < results.size(); i++) {

            // (For each “hit”, we know distance, impact point, geometry.)
            float dist = results.getCollision(i).getDistance();
            pt = results.getCollision(i).getContactPoint();
            String target = results.getCollision(i).getGeometry().getName();

            //System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");

        }
        return results;
    }

    private boolean checkFieldType(int x, int y) {
        int cellType = mMaphandler.getCellType((int) mousePositionWorld.x, (int) mousePositionWorld.z);
        System.out.println("CellType:" + cellType);

        if ((cellType == typeBuildingRoom) && mMaphandler.getPlacesBuildingAt(x, y)) {
            System.out.println("Bauerlaubnis erteilt");
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Green);
            selection.setMaterial(mat);


            return true;
        } else {
            System.out.println("Bauerlaubnis verweigert");
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            selection.setMaterial(mat);
            return false;
        }

    }

    public void stopBuilding() {
        selection = null;
        PlayerRessources.selectedBuilding = null;
        PlayerRessources.selectionRoom = 0;
        marker.detachAllChildren();
    }
    
    public void playSoundEffect(String name)
    {
        mSoundManager.playUISound("click");
    }
}
