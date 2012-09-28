/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andministrator
 */
public class InLoadingInputs extends AbstractAppState implements ScreenController{

    private AppStateManager stateManager;
    private Application app;
    private Nifty nifty;
    private Element citation;
    
    private String dante1 = "Die Not der Menschen, die da unten zittern, "
            + "verfäbrt mir das Gesicht, und Mitleid ist, was du als Furcht verstehst.";
    private String dante2 = "Ich bin der Eingang in die Stadt der Schmerzen, "
            + "ich bin der Eingang in das ewige Leid.";
    private List<String> danteQuotes = new ArrayList<String>();
    
    public InLoadingInputs(AppStateManager stateManager, Application app){
        System.out.println("InLoadingInputs: Constructor");
        this.app = app;
        this.stateManager = stateManager;
    }
    
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("InLoadingInputs: bind()");
        this.nifty = nifty;
        citation = nifty.getScreen("loadingGame").findElementByName("citation");
        showText();
    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {
        
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        System.out.println("InLoadingInputs: stateAttached");
        initValues();
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
        }else{
            System.out.println("LoadingGameState: setEnabled(false)");
        }
    }
    
    private void initValues(){
        danteQuotes.add(dante1);
        danteQuotes.add(dante2);
    }
    
    private void showText(){
        System.out.println("LoadingGameState: " + (int)(Math.random() * danteQuotes.size()));
        String text = danteQuotes.get((int)(Math.random() * danteQuotes.size()));
        citation.getRenderer(TextRenderer.class).setText(text);
    }
    
}
