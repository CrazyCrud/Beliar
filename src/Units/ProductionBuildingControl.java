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
                    case 0: return GameContainer.ADAM_SMALL_SIZE;
                    case 1: return GameContainer.ADAM_MIDDLE_SIZE;
                    case 2: return GameContainer.ADAM_BIG_SIZE;
                }
                break;
                
           case GameContainer.KYTHOS_BUILDING:
                switch(size)
                {
                    case 0: return GameContainer.KYTHOS_SMALL_SIZE;
                    case 1: return GameContainer.KYTHOS_MIDDLE_SIZE;
                    case 2: return GameContainer.KYTHOS_BIG_SIZE;
                }
                break;
                                
          case GameContainer.MARA_BUILDING:
                switch(size)
                {
                    case 0: return GameContainer.MARA_SMALL_SIZE;
                    case 1: return GameContainer.MARA_MIDDLE_SIZE;
                    case 2: return GameContainer.MARA_BIG_SIZE;
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
