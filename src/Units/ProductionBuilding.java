/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.PlayerRessources;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import sun.net.www.content.image.png;

/**
 *
 * @author Martin
 */
public class ProductionBuilding extends building{

    private boolean isActive;
    //DEBUG
    private int progressPercentPerTick = 1;
    private int souluseperProduction = 2;
    
    private int percentStatus = 0;

    public ProductionBuilding(Spatial object, int posX, int posZ) {
        super(object, posX, posZ);
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
            //getObject().setMaterial();
        }
        else
        {
            System.out.println("Keine Güter");
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
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void update() {
        System.out.println("UPDATEBUILDING"+super.getObject().getName());
                if (isActive) {
            if (percentStatus < 100 && percentStatus > 0) {
                progressProduction();
            } else {
                getGoods();
                startProduction();
            }
        }
    }
    
    

}
