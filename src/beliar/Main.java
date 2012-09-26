package beliar;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Main extends SimpleApplication{

    private ScreenManager screenManager;
    private GameState gameState;
    private PauseState pauseState;
    private InGameInputs inGameInputs;
    private InPauseInputs inPauseInputs;
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    
    public static void main(String[] args) {
            AppSettings appSettings = new AppSettings(true);
            appSettings.setSamples(8);
            appSettings.setBitsPerPixel(32);
            appSettings.setVSync(true);
            appSettings.setTitle("Beliar"); 
            appSettings.setWidth(1280);
            appSettings.setHeight(720);
            try{
                appSettings.setIcons(new BufferedImage[]{ 
                    ImageIO.read(new File("assets/Images/logo.png"))});
            }
            catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Main app = new Main();
            app.setSettings(appSettings);
            app.start();
    }

    @Override
    public void simpleInitApp(){
        initGame();
        initScreen();
        initStates();  
    }
    
    private void initGame(){
        GameContainer gameContainer = GameContainer.getInstance();
        gameContainer.setRootNode(rootNode);
        gameContainer.setMouseInput(mouseInput);
        gameContainer.setApplication(this);
    }
    
    private void initScreen(){
        screenManager = ScreenManager.getScreenManager();
        screenManager.initialize(stateManager, this);
    }
    
    private void initStates(){ 
       gameState = new GameState(stateManager, this);
       pauseState = new PauseState(stateManager, this);
       //pauseState = new PauseState(this);
       //inPauseInputs = new InPauseInputs(stateManager, this);
       stateManager.attach(gameState);
       stateManager.attach(pauseState);
       gameState.setEnabled(true);
       pauseState.setEnabled(false);
    }
    
    private void initInput(){
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
        nifty = niftyDisplay.getNifty();
        
        nifty.registerScreenController(inGameInputs);
        nifty.registerScreenController(inPauseInputs);
        nifty.addXml("Interface/Hud.xml");
        nifty.addXml("Interface/OptionsMenu.xml");
        nifty.gotoScreen("inGameInputs");
        guiViewPort.addProcessor(niftyDisplay);
    }
    
    @Override
    public void simpleUpdate(float tpf) {

    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
        
    }
}