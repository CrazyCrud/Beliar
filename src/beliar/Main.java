
package beliar;


import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Main extends SimpleApplication{

    private ScreenManager screenManager;
    private MainMenuState mainMenuState;
    
    public static void main(String[] args) {
            System.out.println("Main: Start the game");
            AppSettings appSettings = new AppSettings(true);
            appSettings.setSamples(8);
            appSettings.setBitsPerPixel(32);
            appSettings.setVSync(true);
            appSettings.setTitle("Beliar"); 
            appSettings.setResolution(1280, 720);
            appSettings.setFullscreen(true);
            try{
                appSettings.setIcons(new BufferedImage[]{ 
                    ImageIO.read(new File("assets/Images/logo.png"))});
            }
            catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Main app = new Main();
            app.setSettings(appSettings);
            GameContainer.getInstance().setAppSettings(appSettings);
            app.setShowSettings(false);
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
        setGraphicModes();
        setDisplayFps(false);
        setDisplayStatView(false);
        screenManager = ScreenManager.getScreenManager();
        //screenManager.initialize(stateManager, this);
    }
    
    private void setGraphicModes(){
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode [] displayModes = device.getDisplayModes();
        GameContainer.getInstance().setDisplayModes(displayModes);
    }
    
    private void initCursor(){
        System.out.println("Main: initCurosr()");
        flyCam.setDragToRotate(true);
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.setCursorVisible(true); 
    }
    
    private void initStates(){ 
       System.out.println("Main: initStates()");
       mainMenuState = new MainMenuState(stateManager, this);
       stateManager.attach(mainMenuState);
       mainMenuState.setEnabled(true);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        // inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
        
    }
}