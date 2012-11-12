/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.jme3.math.Vector3f;

/**
 *
 * @author andministrator
 */
public class GameObjectValues {
    
    //Warrior types
    public static final int MELEE = 0;
    public static final int RANGERS = 1;
    public static final int MAGICIAN = 2;
    
    //Health
    public static final String HEALTH_KEY = "HEALTH";
    public static final int HEALTH_VALUE_SLAVE = 100;
    public static final int HEALTH_VALUE_PRODUCTIONBUILDING = 100;
    
    //Speed
    public static final String SPEED_KEY = "Speed";
    public static final int SPEED_VALUE_SLAVE = 1;
    
    //Position
    public static final String POSX_KEY = "PosX";
    public static final String POSY_KEY = "PosY";
    
    //Building
    public static final String BUILDING_TYPE = "TYPE";
    public static final String BUILDING_SIZE = "SIZE";
    public static final int SOULUSE_PER_PRODUCTION = 2;
    public static final int PROGRESS_PERCENT_PER_TICK = 1;
    public static final float Y_POSITION = 0f;
    
    //Animation
    public static final float BLEND_TIME = 0.7f;
    
    //Movement
    public static final float MOVEMENT_PERIOD = 100.0f;
    
    //Orientation
    public static final String ORIENTATION_KEY = "Orientation";
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int NORTH_EAST = 4;
    public static final int SOUTH_EAST = 5;
    public static final int SOUTH_WEST = 6;
    public static final int NORTH_WEST = 7;
    public static final int NO_DIRECTION_CHANGE = 8;
    public static final Vector3f UP_VECTOR = new Vector3f(0f, 1f, 0f);
}
