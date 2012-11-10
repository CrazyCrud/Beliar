/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import beliar.PlayerRessources;
import beliar.GameContainer;
/**
 *
 * @author andministrator
 */
public class ProductionBuildingControl extends AbstractControl{

    private boolean bool_hasGoodies, bool_isActive;
    private int int_percentStatus = 0;
    
    @Override
    protected void controlUpdate(float tpf) {
        if(isEnabled()){
            System.out.println("BuildingControl: update()");
            if(bool_isActive) {
                if(int_percentStatus < 100 && int_percentStatus > 0) {
                    progressProduction();
                    return;
                }
            }
            startProduction();
        }  
    }
    
    private void startProduction() {
        setActive(true);
        setValues();
        if(checkForRessources()) {
            getSouslForProduction();
        }
    }
    
    private void setValues(){
        bool_hasGoodies = true;
        int_percentStatus = 1;
    }
    
    private boolean checkForRessources() {
        return false;
    }

    private void getSouslForProduction() {
        if (checkForRessources()) {
            setActive(true);
            consumeSouls();
        } else {
            setActive(false);
        }
    }
    
    private void consumeSouls(){
        PlayerRessources.soulsCount -= GameObjectValues.SOULUSE_PER_PRODUCTION;
        int_percentStatus++;
    }
    
    private void progressProduction() {
        int_percentStatus += GameObjectValues.PROGRESS_PERCENT_PER_TICK;
    }
    
    protected void setActive(boolean isActive) {
        this.bool_isActive = isActive;
        //notifyAnimationControl();
    }
    
    protected boolean isActive(){
        return bool_isActive;
    }
    
    private void notifyAnimationControl() {
        spatial.getControl(BuildingAnimationControl.class).setAnimation();
    }
    
    protected int getGoods(){
        int type = spatial.getUserData(GameObjectValues.BUILDING_TYPE);
        int size = spatial.getUserData(GameObjectValues.BUILDING_SIZE);
        switch(type)
        {
            case GameContainer.ADAM_BUILDING:
                switch(size)
                {
                    case 0: return GameContainer.ADAMSMALL;
                    case 1: return GameContainer.ADAMMIDDLE;
                    case 2: return GameContainer.ADAMBIG;
                }
                break;
                
           case GameContainer.KYTHOS_BUILDING:
                switch(size)
                {
                    case 0: return GameContainer.KYTHOSSMALL;
                    case 1: return GameContainer.KYTHOSMIDDLE;
                    case 2: return GameContainer.KYTHOSBIG;
                }
                break;
                                
          case GameContainer.MARA_BUILDING:
                switch(size)
                {
                    case 0: return GameContainer.MARASMALL;
                    case 1: return GameContainer.MARAMIDDLE;
                    case 2: return GameContainer.MARABIG;
                }
                break;
        }
        return 0;
    }
    
    protected boolean hasGoodies() {
        return bool_hasGoodies;
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    public Control cloneForSpatial(Spatial spatial) {
        ProductionBuildingControl clone = new ProductionBuildingControl();
        clone.setSpatial(spatial);
        return clone;
    }
}
