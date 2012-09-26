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
public class PauseState extends AbstractAppState{
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    private InPauseInputs inPauseInputs;
    ScreenManager screenManager;
    
    public PauseState(AppStateManager stateManager, SimpleApplication app){
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
            System.out.println("PauseState: setEnabled");
            inPauseInputs.setEnabled(true);
            showInput();
        }else{
            //inPauseInputs.setEnabled(false);
        }
    }
        
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("PauseState: attach");
        stateManager.attach(inPauseInputs);
        inPauseInputs.setEnabled(false);
        //showInput();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager)
    {
        System.out.println("PauseState: detached");
        stateManager.detach(inPauseInputs);
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
        inPauseInputs = new InPauseInputs(stateManager, app);
    }

    private void showInput() {
        screenManager.switchToPauseScreen(inPauseInputs);
    }

    
    
}
