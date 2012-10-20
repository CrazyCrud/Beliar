/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author andministrator
 */
public abstract class Unit implements AnimEventListener {

    private AnimChannel channel;
    private AnimControl control;
    private Spatial object;
    private Material myMaterial;
    private int healthPointMaximum;
    private int healthPoints;
    private int posX;
    private int posY;

    public Unit(Spatial object, Material mymaterial, int healtPoint, int healtPointMax, int posX, int posY) {

        //Position
        this.posX = posX;
        this.posY = posY;

        //Object
        this.object = object;
        this.myMaterial = mymaterial;

        //Animation
        control = object.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();

        //HealthPoint
        this.healthPointMaximum = healtPointMax;
        this.healthPoints = healtPoint;
    }

    public void changeAnim(String animName, float blendTime, char loopMode) {
        channel.setAnim(animName, blendTime);
        System.out.println("ChangeAnimationOfUnit");
        switch (loopMode) {
            case 'l':
                channel.setLoopMode(LoopMode.Loop);
                break;
            case 'n':
                channel.setLoopMode(LoopMode.DontLoop);
                break;
            case 'c':
                channel.setLoopMode(LoopMode.Cycle);
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public Spatial getObject() {
        return object;
    }

    public Material getMaterial() {
        return myMaterial;
    }

    public AnimChannel getAnimChannel() {
        return channel;
    }

    public int getHealthPointMax() {
        return healthPointMaximum;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    abstract public void update();
}
