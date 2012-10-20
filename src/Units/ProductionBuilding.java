/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.PlayerRessources;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import sun.net.www.content.image.png;

/**
 *
 * @author Martin
 */
public class ProductionBuilding extends Unit {

    private boolean isActive;
    //DEBUG
    private int progressPercentPerTick = 1;
    private int souluseperProduction = 2;
    private int percentStatus = 0;

    public ProductionBuilding(Spatial object, Material myMaterial, int posX, int posZ, int healthPointsStart) {
        super(object, myMaterial, healthPointsStart, healthPointsStart, posX, posZ);
    }

    private boolean checkForRessources() {
        return false;
    }

    private void startProduction() {
        percentStatus = 0;
        if (checkForRessources()) {
            getSouslForProduction();
        }
        percentStatus++;
    }

    private void getSouslForProduction() {
        if (checkForRessources()) {
            PlayerRessources.soulsCount -= souluseperProduction;
            changeAnim("work", 0.5f, 'l');
        } else {
            System.out.println("Keine Güter");
            changeAnim("idle", 0.5f, 'l');
            /* Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            getObject().setMaterial(mat);*/
        }
    }

    private void getGoods() {
        System.out.println("Güter abgeliefert");
    }

    private void progressProduction() {
        percentStatus += progressPercentPerTick;
    }

    public void setActive(boolean isActive) {
        System.out.println("SetActive");
        changeAnim("work", 0.5f, 'l');
        
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void update() {
        System.out.println("UPDATEBUILDING" + super.getObject().getName());
        if (isActive) {
            if (percentStatus < 100 && percentStatus > 0) {
                progressProduction();
            } else {
                getGoods();
                startProduction();
            }
        }
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
         }
}
