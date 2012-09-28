/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author andministrator
 */
public class LoadingGameState extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private SimpleApplication app;
    private GameState gameState;
    private PauseState pauseState;
    private ScreenManager screenManager;
    
    private Node rootNode;
    private AudioNode loadingTheme;
    private boolean load;
    private int frameCount;
    
    public LoadingGameState(AppStateManager stateManager, Application app){
        System.out.println("LoadingGameState: Constructor");
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    public void bind(Nifty nifty, Screen screen) {
        
    }

    public void onStartScreen() {
        System.out.println("LoadingGameState: onStartScreen");
    }

    public void onEndScreen() {
        System.out.println("LoadingGameState: onEndScreen");
    }
    
        @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("LoadingGameState: stateAttached");
        initValues(stateManager, app);
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("LoadingGameState: stateDetached");
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("LoadingGameState: setEnabled");
            showInput();
            initAudio();
            playAudio();
            loadGame();
        }else{
            System.out.println("LoadingGameState: setEnabled(false)");
        }
    }
    
    @Override 
    public void update(float tpf){
        if(isEnabled()){
            super.update(tpf);
            
            if(load){
                if(frameCount == 1){
                    System.out.println("LoadingGameState: update(1)");
                    gameState = new GameState(stateManager, app);
                }else if(frameCount == 2){
                    System.out.println("LoadingGameState: update(2)");
                    pauseState = new PauseState(stateManager, app);
                }else if(frameCount == 3){
                    gameState.initializeGame();
                    System.out.println("LoadingGameState: update(3)");
                }else if(frameCount > 30){
                    System.out.println("LoadingGameState: update(30)");
                    stopAudio();
                    startGame();
                }
                frameCount++;
            }
        }else{
              
        }      
    }
    
    private void initValues(AppStateManager stateManager, Application app){
        rootNode = this.app.getRootNode();
        screenManager = ScreenManager.getScreenManager();
        load = false;
        frameCount = 0;
    }
    
    private void initAudio(){
        loadingTheme = new AudioNode(this.app.getAssetManager(), 
                "Sounds/music/loading.ogg", false);
        loadingTheme.setLooping(true);
        loadingTheme.setVolume(0.8f);
    }
    
    private void playAudio(){
        rootNode.attachChild(loadingTheme);
        loadingTheme.play();
    }
    
    private void stopAudio(){
        loadingTheme.stop();
    }
    
    private void showInput(){
        System.out.println("LoadingGameState: showInput()");
        screenManager.switchToLoadingScreen(this);
    }
    
    private void loadGame(){
        System.out.println("LoadingGameState: loadGame()");
        load = true;
    }
    
    private void startGame(){
      System.out.println("LoadingGameState: startGame()");

      stateManager.getState(LoadingGameState.class).setEnabled(false);
      stateManager.detach(stateManager.getState(LoadingGameState.class));
      stateManager.attach(gameState);
      stateManager.attach(pauseState);
      stateManager.getState(GameState.class).setEnabled(true);
    }
}
