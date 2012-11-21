/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioNode.Status;
import com.jme3.scene.Node;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
public class SoundManager {
    
    private AssetManager assetManager;
    private Node rootNode; 
    private AudioNode background;
    
    private HashMap<String,AudioNode> uiSounds = new HashMap<String, AudioNode>();
    
    public static final int PLACE_BUILDING = 0;
    public static final int CLICK = 1;
    
    public SoundManager(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        initBackgroundMusic();
        initUISound();
   }
    
   private void initBackgroundMusic()
   { 
        background = new AudioNode(assetManager, "Sounds/music/ingame.ogg", false);
        background.setVolume(1.5f);
        background.setLooping(true);
        this.rootNode.attachChild(background);
   }
   
   private void initUISound()
   {
       uiSounds.put("click",new AudioNode(assetManager,"Sounds/sounds/UI/click.ogg",false));
       uiSounds.put("createRoom", new AudioNode(assetManager,"Sounds/sounds/UI/createRoom.ogg",false));
       uiSounds.put("placeBuilding", new AudioNode(assetManager,"Sounds/sounds/UI/placeBuilding.ogg",false));
   }
   
   public void playUISound(String name)
   {
        AudioNode track = uiSounds.get(name);
        track.setPositional(false);
        track.play();
        rootNode.attachChild(track);
   }
   
   public void playBackgroundMusic()
   {
       background.play();
   }
   
   public boolean isMusicPlaying(){
       Status musicStatus = background.getStatus();
       if(musicStatus == Status.Playing){
           return true;
       }else{
           System.out.println("SoundManager: isMusicPlaying() not playing");
           return false;
       }
   }
}
