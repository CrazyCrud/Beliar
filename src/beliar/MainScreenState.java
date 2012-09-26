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
public class MainScreenState extends AbstractAppState implements ScreenController{
      /** Nifty GUI ScreenControl methods */ 
  private Nifty nifty;
  private Screen screen;
  private SimpleApplication app;
  private AssetManager assetManager;
  private AppStateManager stateManager;
  private InputManager inputManager;
  private ViewPort viewPort;
  private Node rootNode = new Node("Root Node");
  
  public MainScreenState(){
      
  }
  
  public Node getRootNode(){
      return rootNode;
  }
    
  public void bind(Nifty nifty, Screen screen) {
    this.nifty = nifty;
    this.screen = screen;
  }
 
  public void onStartScreen(){ 
  
  }
 
  public void onEndScreen(){ 
      
  }
  
  public void startGame(){
      System.out.println("-----startGame()-----");
  }
 
  /** jME3 AppState methods */ 
 
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app = (SimpleApplication)app;
    this.stateManager = stateManager;
    assetManager = this.app.getAssetManager();
    inputManager = this.app.getInputManager();
    viewPort = this.app.getGuiViewPort();
  }
  
  @Override
  public void setEnabled(boolean enabled){
      super.setEnabled(enabled);
      
      if(enabled){
          
      }else{
          
      }
  }
  
  @Override
  public void cleanup(){
      super.cleanup();
  }
 
  @Override
  public void update(float tpf) { 
    super.update(tpf);
  }
}
