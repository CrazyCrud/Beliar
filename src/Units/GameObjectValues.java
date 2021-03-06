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
    
    //Warrior
    public static final int MELEE = 0;
    public static final int ATTACK_DAMAGE_MELEE = 10;
    public static final int RANGERS = 1;
    public static final int ATTACK_DAMAGE_RANGER = 10;
    public static final int MAGICIAN = 2;
    public static final int ATTACK_DAMAGE_MAGICIAN = 10;
    public static final String ATTACK_DAMAGE_KEY = "AttackDamage";
    public static final String CRITICAL_STRIKE_KEY = "CriticalStrike";
    public static final String WARRIOR_TYPE_KEY = "WarriorType";
    
    //Health
    public static final String HEALTH_KEY = "HEALTH";
    public static final int HEALTH_VALUE_SLAVE = 100;
    public static final int HEALTH_VALUE_PRODUCTIONBUILDING = 100;
    
    //Speed
    public static final String SPEED_KEY = "Speed";
    public static final int SPEED_VALUE_SLAVE = 1;
    
    //Position
    public static final String POSX_KEY = "PosX";
    public static final String POSZ_KEY = "PosY";
    
    //Building
    public static final String BUILDING_TYPE = "TYPE";
    public static final String BUILDING_SIZE = "SIZE";
    public static final int ADAM_SMALL_SOULS_PER_PRODUCTION = 2;
    public static final int ADAM_MIDDLE_SOULS_PER_PRODUCTION = 4;
    public static final int ADAM_BIG_SOULS_PER_PRODUCTION = 6;
    public static final int KYTHOS_SMALL_SOULS_PER_PRODUCTION = 4;
    public static final int KYTHOS_MIDDLE_SOULS_PER_PRODUCTION = 4;
    public static final int KYTHOS_BIG_SOULS_PER_PRODUCTION = 8;
    public static final int MARA_SMALL_SOULS_PER_PRODUCTION = 6;
    public static final int MARA_MIDDLE_SOULS_PER_PRODUCTION = 8;
    public static final int MARA_BIG_SOULS_PER_PRODUCTION = 10;
    public static final int PROGRESS_PERCENT_PER_TICK = 1;
    public static final float Y_POSITION_BUILDINGS = 0f;
    public static final float Y_POSITION_UNITS = 0f;
    public static final float CONSTRUCTION_TIME = 18.0f;
    
    //Animation
    public static final float BLEND_TIME = 0.7f;
    
    //Movement
    public static final float MOVEMENT_PERIOD = 80.0f;
    
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
