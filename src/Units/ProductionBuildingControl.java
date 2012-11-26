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
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
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
                if(int_percentStatus < 500 && int_percentStatus > 0) {
                    progressProduction();
                    return;
                }else{
                    setActive(false);
                    return;
                }
            }
            startProduction();
        }  
    }
    
    private void startProduction() {
        if(areSoulsAvailable()) {
            setActive(true);
            setValues();
            consumeSouls();
        }
    }
    
    private void setValues(){
        bool_hasGoodies = true;
        int_percentStatus = 1;
    }
    
    private boolean areSoulsAvailable() {
        return PlayerRessources.soulsCount > GameObjectValues.SOULUSE_PER_PRODUCTION? true: false;
    }
    
    private void consumeSouls(){
        PlayerRessources.soulsCount -= GameObjectValues.SOULUSE_PER_PRODUCTION;
    }
    
    private void progressProduction() {
        int_percentStatus += GameObjectValues.PROGRESS_PERCENT_PER_TICK;
    }
    
    protected void setActive(boolean isActive) {
        if(isActive){
            ((Node)spatial).detachChildNamed("buildingMarker");
        }else{
            Box bosShape = new Box(Vector3f.ZERO, 
                0.10f, 0.10f, 0.10f);
            Geometry geo = new Geometry("buildingMarker", bosShape);
            Material mat = new Material(GameContainer.getInstance().getApplication().getAssetManager(), 
                "MatDefs/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            geo.setMaterial(mat);
            geo.move(0.0f, 0.95f, 0.0f);
            ((Node)spatial).attachChild(geo.clone());
        }
        this.bool_isActive = isActive;
    }
    
    protected boolean isActive(){
        return bool_isActive;
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
