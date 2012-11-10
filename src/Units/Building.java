/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

/**
 *
 * @author andministrator
 */
public abstract class Building extends GameObject{

    public Building(int healthPoints, int posX, int posZ){
        super(healthPoints, posX, posZ);
        System.out.println("Building: set at " + posX + ", " + posZ);
    }    
}
