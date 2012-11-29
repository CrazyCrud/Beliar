/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioNode.Status;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;

/**
 *
 * @author andministrator
 */
public class MainMenuState extends AbstractAppState{
    private SimpleApplication app;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private InMainMenuInputs inMainMenuInputs;
    private ScreenManager screenManager;
    
    private LoadingGameState loadingGameState;
    private OptionState optionState;
    private Node rootNode;
    private AudioNode menuTheme, startGame, otherItems;
    
    public MainMenuState(AppStateManager stateManager, SimpleApplication app){
        System.out.println("MainMenuState: Constructor");
        this.app = app;
        this.stateManager = stateManager;
        this.inputManager = this.app.getInputManager();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        
    }
    
    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("MainMenuState: setEnabled");
            //showInput();
            //initAudio();
            //playAudio();
        }else{
            stopAudio();
            inMainMenuInputs.setEnabled(false);
        }
    }
        
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("MainMenuState: attach");
        initValues();
        initStates();
        attachInput();
        showInput();
        initAudio();
        playAudio();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager)
    {
        System.out.println("MainMenuState: detached");
        stateManager.detach(inMainMenuInputs);
    }
    
    @Override
    public void update(float tpf){
        if(isEnabled()){
            Status menuThemeStatus = menuTheme.getStatus();
            if(menuThemeStatus == AudioNode.Status.Stopped ||
                    menuThemeStatus == AudioNode.Status.Paused){
                menuTheme.play();
                System.out.println("AudioNode is stopped");
            }
        }else{
            
        }
    }

    private void initValues() {
        this.screenManager = ScreenManager.getScreenManager();
        this.rootNode = app.getRootNode();
        //this.inputManager.setCursorVisible(true);
        //this.app.getFlyByCamera().setDragToRotate(true);
    }
    
    private void initStates() {
        inMainMenuInputs = new InMainMenuInputs(stateManager, app,this);
        loadingGameState = new LoadingGameState(stateManager, app);
        optionState = new OptionState(stateManager, app);
    }
    
    private void attachInput(){
        stateManager.attach(inMainMenuInputs);
        inMainMenuInputs.setEnabled(false);
    }
    
    private void initAudio(){
        startGame = new AudioNode(app.getAssetManager(), "Sounds/sounds/UI/startgame.ogg", false);
        otherItems = new AudioNode(app.getAssetManager(), "Sounds/sounds/UI/click.ogg", false);
        menuTheme = new AudioNode(app.getAssetManager(), "Sounds/music/mainmenu.ogg", false);
        startGame.setVolume(0.275f);
        otherItems.setVolume(0.35f);
        menuTheme.setVolume(0.35f);
        rootNode.attachChild(startGame);
        rootNode.attachChild(otherItems);
    }

    private void showInput() {
        inMainMenuInputs.setEnabled(true);
        screenManager.switchToMainMenuScreen(inMainMenuInputs);
    }
    
    private void playAudio(){
        rootNode.attachChild(menuTheme);
    }
    
    private void stopAudio(){
        menuTheme.stop();
        rootNode.detachChild(menuTheme);
    }
    
    public void loadGame(){
        stateManager.getState(MainMenuState.class).setEnabled(false);
        stateManager.getState(InMainMenuInputs.class).setEnabled(false);
        //stateManager.detach(stateManager.getState(MainMenuState.class));
        stateManager.attach(loadingGameState);
        stateManager.getState(LoadingGameState.class).setEnabled(true);
    } 
    
    public void loadSettings(){
        //stateManager.getState(MainMenuState.class).setEnabled(false);
        stateManager.getState(InMainMenuInputs.class).setEnabled(false);
        stateManager.attach(optionState);
        stateManager.getState(OptionState.class).setEnabled(true);
    }
    
    public void playUISound(String name)
    {
        if(name.equals("startGame"))
            startGame.play();
        else
            otherItems.play();
    }
}
