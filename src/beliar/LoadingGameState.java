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

/**
 *
 * @author andministrator
 */
public class LoadingGameState extends AbstractAppState{

    private AppStateManager stateManager;
    private SimpleApplication app;
    private GameState gameState;
    private PauseState pauseState;
    private EndScreenState endScreenState;
    private ScreenManager screenManager;
    private InLoadingInputs inLoadingInputs;
    
    private Node rootNode;
    private AudioNode loadingTheme;
    private boolean load;
    private int frameCount;

    public LoadingGameState(AppStateManager stateManager, Application app){
        System.out.println("LoadingGameState: Constructor");
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("LoadingGameState: stateAttached");
        initValues();
        initStates();
        attachInput();
    }

    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("LoadingGameState: stateDetached");
        stateManager.detach(inLoadingInputs);
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
            inLoadingInputs.setEnabled(false);
        }
    }

    @Override 
    public void update(float tpf){
        if(isEnabled()){
            super.update(tpf);

            if(load){
                if(frameCount == 1){
                    System.out.println("LoadingGameState: update(1)");
                    setProgressBar(0.4f);
                    gameState = new GameState(stateManager, app);
                }else if(frameCount == 2){
                    System.out.println("LoadingGameState: update(2)");
                    setProgressBar(0.6f);
                    pauseState = new PauseState(stateManager, app);
                    endScreenState = new EndScreenState(stateManager, app);
                }else if(frameCount == 3){
                    setProgressBar(1.0f);
                    gameState.initializeGame();
                    System.out.println("LoadingGameState: update(3)");
                }else if(frameCount > 30){
                    System.out.println("LoadingGameState: update(30)");
                    stopAudio();
                    stopInput();
                    startGame();
                }
                frameCount++;
            }
        }else{

        }      
    }

    private void initValues(){
        rootNode = this.app.getRootNode();
        screenManager = ScreenManager.getScreenManager();
        load = false;
        frameCount = 0;
    }
    
    private void initStates(){
        System.out.println("LoadingGameState: initStates()");
        inLoadingInputs = new InLoadingInputs(stateManager, app);
    }
    
    private void attachInput(){
        System.out.println("LoadingGameState: attachInput()");
        stateManager.attach(inLoadingInputs);
        inLoadingInputs.setEnabled(false);
    }

    private void initAudio(){
        loadingTheme = new AudioNode(this.app.getAssetManager(), 
                "Sounds/music/loading.ogg", false);
        loadingTheme.setLooping(true);
        loadingTheme.setVolume(0.5f);
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
        inLoadingInputs.setEnabled(true);
        screenManager.switchToLoadingScreen(inLoadingInputs);
    }
    
    private void stopInput(){
        inLoadingInputs.setEnabled(false);
    }

    private void loadGame(){
        System.out.println("LoadingGameState: loadGame()");
        load = true;
    }
    
    private void setProgressBar(float loadingState){
        inLoadingInputs.setProgress(loadingState);
    }

    private void startGame(){
      System.out.println("LoadingGameState: startGame()");

      stateManager.getState(LoadingGameState.class).setEnabled(false);
      stateManager.detach(stateManager.getState(LoadingGameState.class));
      stateManager.attach(gameState);
      stateManager.attach(pauseState);
      stateManager.attach(endScreenState);
      stateManager.getState(GameState.class).setEnabled(true);
    }
}
