/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

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
public class InMainMenuInputs extends AbstractAppState implements ScreenController{
      /** Nifty GUI ScreenControl methods */
    
  private AppStateManager stateManager;
  private SimpleApplication app;
  
  public InMainMenuInputs(AppStateManager stateManager, SimpleApplication app){
      System.out.println("InMainMenuInputs: Constructor");
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
        System.out.println("InMainMenuInputs: stateAttached");
    }
    
  @Override
  public void stateDetached(AppStateManager stateManager){
        System.out.println("InMainMenuInputs: stateDetached");
   }

  @Override
  public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("InMainMenuInputs: setEnabled");
        }else{
            System.out.println("InMainMenuInputs: setEnabled(false)");
        }
  }
  
  public void onStartGame(){
      System.out.println("InMainMenuInputs: onStartGame()");
      
      stateManager.getState(MainMenuState.class).loadGame();
  }
  
  public void onOptions(){
      System.out.println("InMainMenuInputs: onOptions()");
  }
  
  public void onExitGame(){
      System.out.println("InMainMenuInputs: onExitGame()");
      app.stop();
  }
  
  @Override
  public void update(float tpf) { 
    super.update(tpf);
  }
}
