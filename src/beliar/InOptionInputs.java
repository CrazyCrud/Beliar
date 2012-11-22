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
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.RadioButtonGroup;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author andministrator
 */
public class InOptionInputs extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private SimpleApplication app;
    private Nifty nifty;
    Element radioButtonElement1, radioButtonElement2, radioButtonElement3, 
            radioButtonElement4, radioButtonElement5, checkBoxElement;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    CheckBox checkBox;
    
    public InOptionInputs(AppStateManager stateManager, Application app){
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("InOptionsInputs: bind()");
        this.nifty = nifty;
        initValues();
    }
    
    @Override
    public void onStartScreen() {
        
    }

    @Override
    public void onEndScreen() {
        
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("InOptionsInputs: stateAttached");
    }

    @Override
    public void stateDetached(AppStateManager stateManager){
        System.out.println("InOptionsInputs: stateDetached");
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("InOptionsInputs: setEnabled");
        }else{
            System.out.println("InOptionsInputs: setEnabled(false)");
        }
    }
    
    @Override
    public void update(float tpf) { 
        super.update(tpf);
    }
    
    public void onAbort(){
        backToMainMenu();
    }
    
    public void onSave(){
        getControls();
        computeSettings();
        backToMainMenu();
    }
  
    private void getControls() {
        radioButton1 = radioButtonElement1.getNiftyControl(RadioButton.class);
        radioButton2 = radioButtonElement2.getNiftyControl(RadioButton.class);
        radioButton3 = radioButtonElement3.getNiftyControl(RadioButton.class);
        radioButton4 = radioButtonElement4.getNiftyControl(RadioButton.class);
        radioButton5 = radioButtonElement5.getNiftyControl(RadioButton.class);
        checkBox = checkBoxElement.getNiftyControl(CheckBox.class);
    }

    private void computeSettings() {
        if(radioButton1.isActivated()){
            System.out.println("InOptionInputs: onSave() first radiobutton");
        }else if(radioButton2.isActivated()){
            System.out.println("InOptionInputs: onSave() second radiobutton");
        }
        if(radioButton3.isActivated()){
            System.out.println("InOptionInputs: onSave() third radiobutton");
        }else if(radioButton4.isActivated()){
            System.out.println("InOptionInputs: onSave() fourth radiobutton");
        }else if(radioButton5.isActivated()){
            System.out.println("InOptionInputs: onSave() fifth radiobutton");
        }
        if(checkBox.isChecked()){
            System.out.println("InOptionInputs: onSave() chekbox is checked");
        }
    }
    
    private void backToMainMenu(){
        stateManager.getState(OptionState.class).setEnabled(false);
        stateManager.getState(InOptionInputs.class).setEnabled(false);
        stateManager.getState(MainMenuState.class).setEnabled(true);
    }

    private void initValues() {
        radioButtonElement1 = nifty.getScreen("inOptionInputs").findElementByName("option-1");
        radioButtonElement2 = nifty.getScreen("inOptionInputs").findElementByName("option-2");
        radioButtonElement3 = nifty.getScreen("inOptionInputs").findElementByName("option-3");
        radioButtonElement4 = nifty.getScreen("inOptionInputs").findElementByName("option-4");
        radioButtonElement5 = nifty.getScreen("inOptionInputs").findElementByName("option-5");
        checkBoxElement = nifty.getScreen("inOptionInputs").findElementByName("simpleCheckBox");
    }
}
