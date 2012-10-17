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
import java.sql.Time;

/**
 *
 * @author Martin
 */
public abstract class building {
    
    private Spatial object;
    private AnimChannel channel;
    private AnimControl control;
    private Material myMaterial;
    private int posX;
    private int posZ;
    
    private int healthPoints;
    
    private AnimEventListener myListener;

    public building(Spatial object,int posX,int posZ) {
        this.object = object;
        //this.myMaterial;
        this.posX = posX;
        this.posZ = posZ;
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

    //abstracts
    public abstract void update();
}
