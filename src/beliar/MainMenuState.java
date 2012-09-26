/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author andministrator
 */
public class MainMenuState extends AbstractAppState{
    private SimpleApplication app;
    private AppStateManager stateManager;
    private InMainMenuInputs inMainMenuInputs;
    private ScreenManager screenManager;
    
    public MainMenuState(AppStateManager stateManager, SimpleApplication app){
        initValues(stateManager, app);
        initStates();
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
            //inPauseInputs.setEnabled(false);
        }
    }
        
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("MainMenuState: attach");
        stateManager.attach(inMainMenuInputs);
        inMainMenuInputs.setEnabled(false);
        //showInput();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager)
    {
        System.out.println("MainMenuState: detached");
        stateManager.detach(inMainMenuInputs);
        stateManager.detach(this);
    }
    
    @Override
    public void update(float tpf){

    }

    private void initValues(AppStateManager stateManager, SimpleApplication app) {
        this.app = app;
        this.stateManager = stateManager;
        this.screenManager = ScreenManager.getScreenManager();
    }
    
    private void initStates() {
        inMainMenuInputs = new InMainMenuInputs(stateManager, app);
    }

    private void showInput() {
        screenManager.switchToMainMenuScreen(inMainMenuInputs);
    }
}
