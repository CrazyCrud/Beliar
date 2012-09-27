/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;

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
            inMainMenuInputs.setEnabled(true);
            showInput();
        }else{
            inMainMenuInputs.setEnabled(false);
        }
    }
        
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("MainMenuState: attach");
        initValues();
        initStates();
        attachInput();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager)
    {
        System.out.println("MainMenuState: detached");
        stateManager.detach(inMainMenuInputs);
    }
    
    @Override
    public void update(float tpf){

    }

    private void initValues() {
        this.screenManager = ScreenManager.getScreenManager();
        this.inputManager.setCursorVisible(true);
        this.app.getFlyByCamera().setDragToRotate(true);
    }
    
    private void initStates() {
        inMainMenuInputs = new InMainMenuInputs(stateManager, app);
        loadingGameState = new LoadingGameState(stateManager, app);
    }
    
    private void attachInput(){
        stateManager.attach(inMainMenuInputs);
        inMainMenuInputs.setEnabled(false);
    }

    private void showInput() {
        screenManager.switchToMainMenuScreen(inMainMenuInputs);
    }
    
    public void loadGame(){
        stateManager.getState(MainMenuState.class).setEnabled(false);
        stateManager.getState(InMainMenuInputs.class).setEnabled(false);
        //stateManager.detach(stateManager.getState(MainMenuState.class));
        stateManager.attach(loadingGameState);
        stateManager.getState(LoadingGameState.class).setEnabled(true);
    }
}
