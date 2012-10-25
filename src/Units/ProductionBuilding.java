/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import beliar.GameContainer;
import beliar.PlayerRessources;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author Martin
 */
public class ProductionBuilding extends Unit {

    private char type;
    private int size;
    private boolean hasGoodies;
    private boolean isActive;
    //DEBUG
    private int progressPercentPerTick = 1;
    private int souluseperProduction = 2;
    private int percentStatus = 0;

    public ProductionBuilding(Spatial object, Material myMaterial, int posX, int posZ, int healthPointsStart,char type,int size) {
        super(object, myMaterial, healthPointsStart, healthPointsStart, posX, posZ);
        this.type = type;
        this.size = size;
        
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
            percentStatus++;
        } else {
            System.out.println("Keine Güter");
            changeAnim("idle", 0.5f, 'l');
            /* Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            getObject().setMaterial(mat);*/
        }
    }
    
    public boolean hasGoodies()
    {
        return hasGoodies;
    }
    
    public int getGoods() {
        switch (type)
        {
            case 'a':
                switch(size)
                {
                    case 0: return GameContainer.ADAMSMALL;
                    case 1: return GameContainer.ADAMMIDDLE;
                    case 2: return GameContainer.ADAMBIG;
                }
                break;
                
           case 'k':
                switch(size)
                {
                    case 0: return GameContainer.KYTHOSSMALL;
                    case 1: return GameContainer.KYTHOSMIDDLE;
                    case 2: return GameContainer.KYTHOSBIG;
                }
                break;
                                
          case 'm':
                switch(size)
                {
                    case 0: return GameContainer.MARASMALL;
                    case 1: return GameContainer.MARAMIDDLE;
                    case 2: return GameContainer.MARABIG;
                }
                break;
        }
        hasGoodies = false;
        return 0;
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
                hasGoodies=true;
                startProduction();
            }
        }
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
         }
    
        
    public char getType()
    {
        return type;
    }
    
    public int gezSize()
    {
        return size;
    }
}
