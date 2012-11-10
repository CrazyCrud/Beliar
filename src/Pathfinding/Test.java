/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinding;

import Map.MapController;
import java.util.List;

/**
 *
 * @author andministrator
 */
public class Test {
    public static void checkPath(){
        Pathfinder pathFinder = new Pathfinder();
        List<Node> path = pathFinder.search(MapController.getNode(0, 0), MapController.getNode(1, 12));
        for(Node node: path){
            System.out.println("Path: " + node.getXPos() + ", " + node.getYPos());
        }
    }
}
