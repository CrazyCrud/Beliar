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
import de.lessvoid.nifty.tools.SizeValue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
    private Element citation, progressBar;
    
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
        progressBar = nifty.getScreen("loadingGame").findElementByName("progressbar");
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
        System.out.println("System" + System.getProperty("user.dir"));
        try{
            File txtFile = new File("assets/Interface/Text/Dante.txt");
            FileInputStream inputStream = new FileInputStream(txtFile);
            //InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-1"));
            InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bfReader = new BufferedReader(streamReader);
            String nextLine = bfReader.readLine();
           
            while(nextLine != null){
                danteQuotes.add(nextLine);
                nextLine = bfReader.readLine();
            }
            
            bfReader.close();
        }catch(Exception e){
            
        }
    }
    
    private void showText(){
        System.out.println("InLoadingInputs: showText() " + danteQuotes.size());
        String text = danteQuotes.get((int)(Math.random() * danteQuotes.size()));
        citation.getRenderer(TextRenderer.class).setText(text);
    }
    
    public void setProgress(final float progress) {
        System.out.println("InLoadingGameState: setProgress()");
        final int MIN_WIDTH = 32;
        int pixelWidth = (int) (MIN_WIDTH + (progressBar.getParent().getWidth() - MIN_WIDTH) * progress);
        progressBar.setConstraintWidth(new SizeValue(pixelWidth + "px"));
        progressBar.getParent().layoutElements();
    }
    
}
