/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author andministrator
 */
public class EndScreenState extends AbstractAppState implements ScreenController{
    AppStateManager stateManager; 
    SimpleApplication app;
    AudioNode sound;
    
    public EndScreenState(AppStateManager stateManager, SimpleApplication app){
        this.stateManager = stateManager;
        this.app = app;
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("OptionState: stateAttached");
        
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("OptionState: stateDetached");
        
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("OptionState: setEnabled");
            showInput();
            playAudio();
        }else{
            System.out.println("OptionState: setEnabled(false)");
            
        }
    }

    @Override 
    public void update(float tpf){
        if(isEnabled()){
     
        }
    }

    public void bind(Nifty nifty, Screen screen) {
        
    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {
        
    }
    
    public void onExitGame(){
        this.app.stop();
    }

    private void showInput() {
        ScreenManager.getScreenManager().switchToEndScreen(this);
    }

    private void playAudio() {
        sound = new AudioNode(app.getAssetManager(), "Sounds/sounds/endscreen.ogg");
        app.getRootNode().attachChild(sound);
        sound.setVolume(0.4f);
        sound.play();
    }
}
