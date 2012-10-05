/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.EndNotify;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
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

  private Nifty nifty;
  private Element textTop, textBottom, textMid, blendLogo;
  
  public InMainMenuInputs(AppStateManager stateManager, SimpleApplication app){
      System.out.println("InMainMenuInputs: Constructor");
      this.stateManager = stateManager;
      this.app = (SimpleApplication) app;
      
  }

  @Override
  public void bind(Nifty nifty, Screen screen) {
      System.out.println("InMainMenuInputs: bind()");
      this.nifty = nifty;
      initValues();
      moveElements();
  }
  
  @Override
  public void onStartScreen() {
      System.out.println("onStartScreen()");
  }
  @Override
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
  
  public int getOffset(){
      System.out.println("getOffset(): " + textTop.getWidth());
      return textTop.getWidth();
  }
  
  public int getOffsetLeft(){
      System.out.println("getOffsetLeft(): " + GameContainer.getInstance().getScreenWidth());
      return (-1) * GameContainer.getInstance().getScreenWidth();
  }
  
  public int getOffsetRight(){
      System.out.println("getOffsetRight(): " + GameContainer.getInstance().getScreenWidth());
      return (GameContainer.getInstance().getScreenWidth() * 2);
  }

  private void initValues(){
      textTop = nifty.getScreen("inMainMenuInputs").findElementByName("textTop");
      textBottom = nifty.getScreen("inMainMenuInputs").findElementByName("textBottom");
      textMid = nifty.getScreen("inMainMenuInputs").findElementByName("textMid");
      blendLogo = nifty.getScreen("inMainMenuInputs").findElementByName("blendLogo");
  }
  
  private void moveElements(){
      System.out.println("moveElements()");
      moveTextTop();
      moveTextMid();
      moveTextBottom();
      blendLogo();
  }
  
  private void moveTextTop(){
      textTop.startEffect(EffectEventId.onCustom, new MoveTopEnd(), "moveTopText");
  }
  
  private void moveTextMid(){
      textMid.startEffect(EffectEventId.onCustom, new MoveMidEnd(), "moveMidText"); 
  }
  
  private void moveTextBottom(){
      textBottom.startEffect(EffectEventId.onCustom, new MoveBottomEnd(), "moveBottomText");
  }
  
  private void blendLogo(){
      blendLogo.startEffect(EffectEventId.onCustom, new FadeInEnd(), "fadeInLogo");
      blendLogo.show();
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
  
  public void onDoNothing(){
      
  }
  
  public void blendOut(){
      
  }

  @Override
  public void update(float tpf) { 
    super.update(tpf);
    
  }
  
  class MoveTopEnd implements EndNotify {
    @Override
    public void perform() {
        //textPanelTop.startEffect(EffectEventId.onCustom, new MoveOutEnd(), "moveOut");
        System.out.println("moveIn has ended.");
        //textTop.setConstraintX(new SizeValue(Integer.toString(getOffsetLeft())));
        //textTop.layoutElements();
        moveTextTop();
    }
  }  
  
  class MoveMidEnd implements EndNotify {
    @Override
    public void perform() {
        //textPanelTop.startEffect(EffectEventId.onCustom, new MoveOutEnd(), "moveOut");
        System.out.println("moveIn has ended.");
        //textTop.setConstraintX(new SizeValue(Integer.toString(getOffsetLeft())));
        //textTop.layoutElements();
        moveTextMid();
    }
  } 
  
  class MoveBottomEnd implements EndNotify {
    @Override
    public void perform() {
        //textPanelTop.startEffect(EffectEventId.onCustom, new MoveOutEnd(), "moveOut");
        System.out.println("moveIn has ended.");
        //textTop.setConstraintX(new SizeValue(Integer.toString(getOffsetLeft())));
        //textTop.layoutElements();
        moveTextBottom();
    }
  } 
  
  class FadeInEnd implements EndNotify {
    @Override
    public void perform() {
        blendLogo.startEffect(EffectEventId.onCustom, new FadeOutEnd(), "fadeOutLogo");
    }
  }
  
  class FadeOutEnd implements EndNotify {
    @Override
    public void perform() {
        System.out.println("FadeOutEnd");
        //blendLogo.hide();
        //blendLogo();
    }
  }
  
}
