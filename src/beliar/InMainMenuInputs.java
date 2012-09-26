/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
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
            //this.app.getRootNode().attachChild(rootNode);
        }else{
            System.out.println("InMainMenuInputs: setEnabled(false)");
        }
  }
  
  public void onStartGame(){
      System.out.println("InMainMenuInputs: onStartGame()");
      //stateManager.getState(MainMenuState.class).setEnabled(false);
      //stateManager.getState(InMainMenuInputs.class).setEnabled(false);
      //stateManager.getState(GameState.class).setEnabled(true);
      
      stateManager.getState(MainMenuState.class).initializeGame();
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
