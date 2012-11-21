
package beliar;


import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
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
            AppSettings appSettings = new AppSettings(true);
            appSettings.setSamples(8);
            appSettings.setBitsPerPixel(32);
            appSettings.setVSync(true);
            appSettings.setTitle("Beliar"); 
            appSettings.setWidth(1280);
            appSettings.setHeight(720);
            appSettings.setResolution(1280, 720);
            
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
        setDisplayFps(false);
        setDisplayStatView(false);
        screenManager = ScreenManager.getScreenManager();
        screenManager.initialize(stateManager, this);
    }
    
    private void initCursor(){
        flyCam.setDragToRotate(true);
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.setCursorVisible(true); 
    }
    
    private void initStates(){ 
       mainMenuState = new MainMenuState(stateManager, this);
       stateManager.attach(mainMenuState);
       mainMenuState.setEnabled(true);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        inputManager.deleteTrigger("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
        
    }
}