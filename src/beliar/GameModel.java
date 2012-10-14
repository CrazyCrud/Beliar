/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*Aufgaben:
 * Speichert alle Werte der Map zwischen
 * 
 */
package beliar;

import com.jme3.math.Vector3f;

/**
 *
 * @author Martin
 */
public class GameModel {
    
    private int sizeX;
    private int sizeY;
    private int terrainMap[][];
    private boolean positionBuildings[][];
    private int positionSoulAbyss[][] = new int[20][20];
    private int positionEnter[][];
    private int positionArtefacts[][];
    private Vector3f positionHellCenter = new Vector3f(Vector3f.ZERO);
    
    
}
