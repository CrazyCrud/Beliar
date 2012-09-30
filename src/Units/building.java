/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author Martin
 */
public class building {
    
    private Spatial object;
    private AnimChannel channel;
    private AnimControl control;
    private Material myMaterial;
    private int posX;
    private int posZ;
    
    private int healthPoints;
    private boolean isProdutive = false;
    
    private AnimEventListener myListener;

    public building(Spatial object,AnimEventListener myListener,int posX,int posZ) {
        this.object = object;
        this.posX = posX;
        this.posZ = posZ;
        this.myListener = myListener;
    }
    
    private void initAnimation()
    {
        
    }
    
    public void changeAnimation(String animName)
    {
        
    }
    
    public Spatial getObject()
    {
        return object;
    }
    
    public int getPosX()
    {
        return posX;
    }
    
    public int getPosZ()
    {
        return posZ;
    }
    
    public int getHealthPoints()
    {
        return healthPoints;
    }
    
    public boolean isProductive()
    {
        return isProdutive;
    }
}
