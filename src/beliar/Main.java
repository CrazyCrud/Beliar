<<<<<<< HEAD
package beliar;

import Container.GameContainer;
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
    private MainMenuState mainMenuState;
    private GameState gameState;
    private PauseState pauseState;
    private InGameInputs inGameInputs;
    private InPauseInputs inPauseInputs;
    private InMainMenuInputs inMainMenuInputs;
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
        initCursor();
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
    
    private void initCursor(){
        // inputManager.setCursorVisible(true); 
    }
    
    private void initStates(){ 
       //mainMenuState = new MainMenuState(stateManager, this);
       gameState = new GameState(stateManager, this);
       //pauseState = new PauseState(stateManager, this);
       //stateManager.attach(mainMenuState);
       stateManager.attach(gameState);
       //stateManager.attach(pauseState);
       //mainMenuState.setEnabled(false);
       gameState.setEnabled(true);
       //pauseState.setEnabled(false);
    }
    
    @Override
    public void simpleUpdate(float tpf) {

    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
        
    }
=======
package beliar;

import beliar.GameContainer;
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
    private MainMenuState mainMenuState;
    private GameState gameState;
    private PauseState pauseState;
    private InGameInputs inGameInputs;
    private InPauseInputs inPauseInputs;
    private InMainMenuInputs inMainMenuInputs;
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
        initCursor();
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
    
    private void initCursor(){
        // inputManager.setCursorVisible(true); 
    }
    
    private void initStates(){ 
       //mainMenuState = new MainMenuState(stateManager, this);
       gameState = new GameState(stateManager, this);
       //pauseState = new PauseState(stateManager, this);
       //stateManager.attach(mainMenuState);
       stateManager.attach(gameState);
       //stateManager.attach(pauseState);
       //mainMenuState.setEnabled(false);
       gameState.setEnabled(true);
       //pauseState.setEnabled(false);
    }
    
    @Override
    public void simpleUpdate(float tpf) {

    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
        
    }
>>>>>>> cd938b03cb9aa9b9a0b3791737685d2c9f874493
}