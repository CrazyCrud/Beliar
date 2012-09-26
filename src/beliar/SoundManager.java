/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
public class SoundManager {
    
    private AssetManager assetManager;
    private Node rootNode; 
    private HashMap<String,AudioNode> backgroundMusic = new HashMap<String, AudioNode>();

    public SoundManager(AssetManager assetManager, Node rootNode) {
    this.assetManager = assetManager;
    this.rootNode = rootNode;
    
    backgroundMusic.put("01", new AudioNode(assetManager, "Sounds/music/background_1.ogg",true));
    backgroundMusic.put("02", new AudioNode(assetManager, "Sounds/music/background_1.ogg",true));
    
    }
   
    
    public void playMusic(String trackName)
    {
        //TODO: Ändern des Tracks via Daten!
        AudioNode track = backgroundMusic.get(trackName);
        track.setPositional(false);
        //track.play();
        rootNode.attachChild(track);
    }
    
    
}
