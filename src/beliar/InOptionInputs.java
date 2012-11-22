/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.awt.DisplayMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;

/**
 *
 * @author andministrator
 */
public class InOptionInputs extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private SimpleApplication app;
    private AppSettings appSettings;
    private Nifty nifty;
    private Element dropDownElement, radioButtonElement3, 
            radioButtonElement4, radioButtonElement5, checkBoxElement;
    private DisplayMode [] displayModes;
    private DropDown dropDown;
    private RadioButton radioButton3, radioButton4, radioButton5;
    private CheckBox checkBox;
    
    public InOptionInputs(AppStateManager stateManager, Application app){
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("InOptionsInputs: bind()");
        this.nifty = nifty;
        initValues();
        getControls();
        initScreenElements();
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
        computeSettings();
        backToMainMenu();
    }
    
    private void computeSettings() {
        int itemIndex = dropDown.getSelectedIndex();
        appSettings.setResolution(displayModes[itemIndex].getWidth(), displayModes[itemIndex].getHeight());
        appSettings.setDepthBits(displayModes[itemIndex].getBitDepth());
        appSettings.setFrequency(displayModes[itemIndex].getRefreshRate());
       
        if(radioButton3.isActivated()){
            System.out.println("InOptionInputs: computeSettings() 1x");
            appSettings.setSamples(0);
        }else if(radioButton4.isActivated()){
            System.out.println("InOptionInputs: computeSettings() 4x");
            appSettings.setSamples(4);
        }else if(radioButton5.isActivated()){
            System.out.println("InOptionInputs: computeSettings() 8x");
            appSettings.setSamples(8);
        }
        if(checkBox.isChecked()){
            System.out.println("InOptionInputs: computeSettings() vsync on");
            appSettings.setVSync(true);
        }else{
            System.out.println("InOptionInputs: computeSettings() vsync off");
            appSettings.setVSync(false);
        }
        this.app.getStateManager().getState(OptionState.class).changeSettings(appSettings);
    }
    
    private void backToMainMenu(){
        stateManager.getState(OptionState.class).setEnabled(false);
        stateManager.getState(InOptionInputs.class).setEnabled(false);
        stateManager.getState(MainMenuState.class).setEnabled(true);
    }

    private void initValues() {
        appSettings = new AppSettings(false);
        appSettings.copyFrom(GameContainer.getInstance().getAppSettings());
        displayModes = GameContainer.getInstance().getDisplayModes();

        dropDownElement = nifty.getScreen("inOptionInputs").findElementByName("dropDown2");
        radioButtonElement3 = nifty.getScreen("inOptionInputs").findElementByName("option-3");
        radioButtonElement4 = nifty.getScreen("inOptionInputs").findElementByName("option-4");
        radioButtonElement5 = nifty.getScreen("inOptionInputs").findElementByName("option-5");
        checkBoxElement = nifty.getScreen("inOptionInputs").findElementByName("simpleCheckBox");
    }
    
    private void getControls() {
        dropDown = dropDownElement.getNiftyControl(DropDown.class);
        radioButton3 = radioButtonElement3.getNiftyControl(RadioButton.class);
        radioButton4 = radioButtonElement4.getNiftyControl(RadioButton.class);
        radioButton5 = radioButtonElement5.getNiftyControl(RadioButton.class);
        checkBox = checkBoxElement.getNiftyControl(CheckBox.class);
    }
    
    private void initScreenElements(){
        for(DisplayMode mode: displayModes){
            int width = mode.getWidth();
            int height = mode.getHeight();
            int bitDepth = mode.getBitDepth();
            dropDown.addItem(width + "*" + height + ", " + bitDepth + "BPP");
        }
    }
}