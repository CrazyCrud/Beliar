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
public class InPauseInputs extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private SimpleApplication app;
    
    public InPauseInputs(AppStateManager stateManager, Application app){
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    public void bind(Nifty nifty, Screen screen) {
        
    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {
        
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("InPauseInputs: stateAttached");
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("InPauseInputs: stateDetached");
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("InPauseInputs: setEnabled");
            //this.app.getRootNode().attachChild(rootNode);
        }else{
            System.out.println("InPauseInputs: setEnabled(false)");
        }
    }
    
    public void onBackToGame(){
        //stateManager.detach(stateManager.getState(PauseState.class));
        //stateManager.detach(this);
        stateManager.getState(PauseState.class).setEnabled(false);
        stateManager.getState(InPauseInputs.class).setEnabled(false);
        stateManager.getState(GameState.class).setEnabled(true);
    }
    
    public void onBackToMainScreen(){
        System.out.println("InPauseInputs: onBackToMainMenu");
        stateManager.detach(stateManager.getState(GameState.class));
        stateManager.getState(PauseState.class).setEnabled(false);
        stateManager.getState(MainMenuState.class).setEnabled(true);
    }
    
    public void onExitGame(){
        app.stop();
    }
}
