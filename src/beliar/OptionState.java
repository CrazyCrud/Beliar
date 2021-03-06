/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;

/**
 *
 * @author andministrator
 */
public class OptionState extends AbstractAppState{
    private AppStateManager stateManager;
    private SimpleApplication app;
    private ScreenManager screenManager;
    private InOptionInputs inOptionInputs;
    private Node rootNode;

    public OptionState(AppStateManager stateManager, Application app){
        System.out.println("OptionState: Constructor");
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("OptionState: stateAttached");
        initValues();
        initStates();
        attachInput();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("OptionState: stateDetached");
        stateManager.detach(inOptionInputs);
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("OptionState: setEnabled");
            showInput();
        }else{
            System.out.println("OptionState: setEnabled(false)");
            inOptionInputs.setEnabled(false);
        }
    }

    @Override 
    public void update(float tpf){
        if(isEnabled()){
     
        }
    }

    private void initValues() {
        rootNode = this.app.getRootNode();
        screenManager = ScreenManager.getScreenManager();
    }

    private void initStates() {
        inOptionInputs = new InOptionInputs(stateManager, app);
    }

    private void attachInput() {
        stateManager.attach(inOptionInputs);
        inOptionInputs.setEnabled(false);
    }
    
    private void showInput() {
        inOptionInputs.setEnabled(true);
        screenManager.switchToOptionMenuScreen(inOptionInputs);
    }
    
    protected void changeSettings(AppSettings appSettings){
        this.app.setSettings(appSettings);
        GameContainer.getInstance().setAppSettings(appSettings);
        try {
            appSettings.save(GameContainer.SETTINGS_KEY);
            stateManager.getState(MainMenuState.class).setEnabled(false);
            this.app.restart();
        } catch (BackingStoreException ex) {
            Logger.getLogger(OptionState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
