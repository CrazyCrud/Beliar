/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author andministrator
 */
public class ScreenManager {
    
    private AppStateManager stateManager;
    private SimpleApplication app;
    private AssetManager assetManager;
    private InputManager inputManager;
    private AudioRenderer audioRenderer;
    private ViewPort viewPort;
    private ViewPort guiViewPort;
    
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    
    private static ScreenManager instance;
    
    private ScreenManager(){
        // nothing to do here...
    }
    
    public static ScreenManager getScreenManager(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }
    
    public void initialize(AppStateManager stateManager, SimpleApplication app){
        System.out.println("ScreenManager");
        this.stateManager = stateManager;
        this.app = app;
        this.assetManager = app.getAssetManager();
        this.inputManager = app.getInputManager();
        this.audioRenderer = app.getAudioRenderer();
        this.viewPort = app.getViewPort();
        this.guiViewPort = app.getGuiViewPort();
        
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, viewPort);
        nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);  
        GameContainer.getInstance().setDimensions(nifty.getRenderEngine().getWidth(), 
                nifty.getRenderEngine().getHeight());
    }
    
    public Nifty getNifty(){
        return nifty;
    }
    
    public void setGameState(ScreenController gameInputs){
        
    }
    
    public void setPauseState(ScreenController gameInputs){
        
    }
    
    public void switchToMainMenuScreen(ScreenController mainMenuInputs){
        System.out.println("ScreenManager: switchToMainMenuScreen()");
        nifty.registerScreenController(mainMenuInputs);
        nifty.addXml("Interface/MainMenu.xml");
        nifty.gotoScreen("inMainMenuInputs");
    }
    
    public void switchToOptionMenuScreen(ScreenController optionMenuInputs){
        nifty.registerScreenController(optionMenuInputs);
        nifty.addXml("Interface/OptionMenu.xml");
        nifty.gotoScreen("inOptionInputs");
    }
    
    public void switchToLoadingScreen(ScreenController loadingScreen){
        nifty.registerScreenController(loadingScreen);
        nifty.addXml("Interface/LoadingGame.xml");
        nifty.gotoScreen("loadingGame");
    }
    
    public void switchToGameScreen(ScreenController gameInputs){
        nifty.registerScreenController(gameInputs);
        nifty.addXml("Interface/Hud.xml");
        nifty.gotoScreen("inGameInputs");
    }
    
    public void switchToPauseScreen(ScreenController pauseInputs){
        nifty.registerScreenController(pauseInputs);
        nifty.addXml("Interface/PauseMenu.xml");
        nifty.gotoScreen("inPauseInputs");
    }
    
    public void switchToEndScreen(ScreenController endScreenInputs){
        nifty.registerScreenController(endScreenInputs);
        nifty.addXml("Interface/EndScreen.xml");
        nifty.gotoScreen("inEndScreenInputs");
    }

    protected void removeScreen() {
        nifty.removeScreen("inMainMenuInputs");
    }
    
    protected void configureNifty(){
        if(niftyDisplay == null){
            niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), 
                    app.getAudioRenderer(), app.getViewPort());
            nifty = niftyDisplay.getNifty();
            app.getViewPort().addProcessor(niftyDisplay);
        }else if(!niftyDisplay.isInitialized()){
            niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), 
                    app.getAudioRenderer(), app.getViewPort());
            nifty = niftyDisplay.getNifty();
            app.getViewPort().addProcessor(niftyDisplay);
        }
    }
}
