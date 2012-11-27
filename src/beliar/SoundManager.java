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
    private AudioNode background, warrior1, warrior2,
            warrior3, warrior4;
    private static AudioNode slave1, slave2, slave3, slave4;
    
    private HashMap<String,AudioNode> uiSounds = new HashMap<String, AudioNode>();
    
    public static final int PLACE_BUILDING = 0;
    public static final int CLICK = 1;
    
    protected SoundManager(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        initBackgroundMusic();
        initUISound();
        initInGameSounds();
   }
    
   private void initBackgroundMusic()
   { 
        background = new AudioNode(assetManager, "Sounds/music/ingame.ogg", false);
        background.setVolume(1.5f);
        background.setLooping(true);
        this.rootNode.attachChild(background);
   }
   
   private void initInGameSounds(){
       slave1 = new AudioNode(assetManager, "Sounds/sounds/DortKommeIchNichtHin.ogg", false);
       slave1.setVolume(0.5f);
       slave2 = new AudioNode(assetManager, "Sounds/sounds/GrabenGrabenGraben.ogg", false);
       slave2.setVolume(0.5f);
       slave3 = new AudioNode(assetManager, "Sounds/sounds/JaMeister.ogg", false);
       slave3.setVolume(0.5f);
       slave4 = new AudioNode(assetManager, "Sounds/sounds/WirdErledigt.ogg", false);
       slave4.setVolume(0.5f);
       warrior1 = new AudioNode(assetManager, "Sounds/sounds/SchonGenug.ogg", false);
       warrior2 = new AudioNode(assetManager, "Sounds/sounds/WerIstDerErste.ogg", false);
       warrior3 = new AudioNode(assetManager, "Sounds/sounds/WiewaereEsMitBlut.ogg", false);
       warrior4 = new AudioNode(assetManager, "Sounds/sounds/ZuBefehl.ogg", false);
       this.rootNode.attachChild(slave1);
       this.rootNode.attachChild(slave2);
       this.rootNode.attachChild(slave3);
       this.rootNode.attachChild(slave4);
       this.rootNode.attachChild(warrior1);
       this.rootNode.attachChild(warrior2);
       this.rootNode.attachChild(warrior3);
       this.rootNode.attachChild(warrior4);
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
        rootNode.attachChild(track);
        track.play();
   }
   
   protected void playBackgroundMusic()
   {
       background.play();
   }
   
   protected boolean isMusicPlaying(){
       Status musicStatus = background.getStatus();
       if(musicStatus == Status.Playing){
           return true;
       }else{
           System.out.println("SoundManager: isMusicPlaying() not playing");
           return false;
       }
   }
   
   public static void playSlaveSound(int whichSound){
       switch(whichSound){
           case GameContainer.SLAVE_BUILD:
               slave2.play();
               break;
           case GameContainer.SLAVE_BUILD_2:
               slave4.play();
               break;
           case GameContainer.SLAVE_NOT_REACHABLE:
               slave1.play();
               break;
           case GameContainer.SLAVE_RANDOM:
               slave3.play();
               break;
       }
   }
   
   protected void playWarriorSound(){
       switch((int)(Math.round(Math.random() * 3.0))){
           case 0:
               warrior1.play();
               break;
           case 1:
               warrior2.play();
               break;
           case 2:
               warrior3.play();
               break;
           case 3:
               warrior4.play();
               break;
       }
   }
}
