/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author andministrator
 */
public class InOptionInputs extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private SimpleApplication app;
    
    public InOptionInputs(AppStateManager stateManager, Application app){
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    public void bind(Nifty nifty, Screen screen) {
        
    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {
        
    }
    
    public void onAbort(){
        backToMainMenu();
    }
    
    public void onSave(){
        backToMainMenu();
    }
    
    private void backToMainMenu(){
        stateManager.getState(OptionState.class).setEnabled(false);
        stateManager.getState(InOptionInputs.class).setEnabled(false);
        stateManager.getState(MainMenuState.class).setEnabled(true);
    }
    
}
