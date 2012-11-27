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
    private static AudioNode slave1, slave2, slave3, slave4, warrior1, warrior2,
            warrior3, warrior4, souls;
    private static AudioNode buildBuilding, buildRoom, quest;
    private HashMap<String,AudioNode> uiSounds = new HashMap<String, AudioNode>();
    
    public static final int PLACE_BUILDING = 0;
    public static final int CLICK = 1;
    public static final int CLICK_2 = 2;
    
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
       slave1.setVolume(0.2f);
       slave2 = new AudioNode(assetManager, "Sounds/sounds/GrabenGrabenGraben.ogg", false);
       slave2.setVolume(0.2f);
       slave3 = new AudioNode(assetManager, "Sounds/sounds/JaMeister.ogg", false);
       slave3.setVolume(0.2f);
       slave4 = new AudioNode(assetManager, "Sounds/sounds/WirdErledigt.ogg", false);
       slave4.setVolume(0.2f);
       buildBuilding = new AudioNode(assetManager, "Sounds/sounds/Build.ogg", false);
       buildBuilding.setVolume(0.040f);
       buildRoom = new AudioNode(assetManager, "Sounds/sounds/buildRoom.ogg", false);
       buildRoom.setVolume(0.2f);
       warrior1 = new AudioNode(assetManager, "Sounds/sounds/SchonGenug.ogg", false);
       warrior2 = new AudioNode(assetManager, "Sounds/sounds/WerIstDerErste.ogg", false);
       warrior3 = new AudioNode(assetManager, "Sounds/sounds/WiewaereEsMitBlut.ogg", false);
       warrior4 = new AudioNode(assetManager, "Sounds/sounds/ZuBefehl.ogg", false);
       souls = new AudioNode(assetManager, "Sounds/sounds/Souls.ogg", false);
       souls.setVolume(0.1f);
       quest = new AudioNode(assetManager, "Sounds/sounds/Quest.ogg", false);
       quest.setVolume(0.2f);
       this.rootNode.attachChild(slave1);
       this.rootNode.attachChild(slave2);
       this.rootNode.attachChild(slave3);
       this.rootNode.attachChild(slave4);
       this.rootNode.attachChild(buildBuilding);
       this.rootNode.attachChild(warrior1);
       this.rootNode.attachChild(warrior2);
       this.rootNode.attachChild(warrior3);
       this.rootNode.attachChild(warrior4);
       this.rootNode.attachChild(souls);
       this.rootNode.attachChild(quest);
   }
   
   private void initUISound()
   {
       uiSounds.put("click",new AudioNode(assetManager,"Sounds/sounds/UI/click.ogg",false));
       uiSounds.put("click2",new AudioNode(assetManager,"Sounds/sounds/UI/click2.ogg",false));
       uiSounds.put("createRoom", new AudioNode(assetManager,"Sounds/sounds/UI/createRoom.ogg",false));
       uiSounds.put("placeBuilding", new AudioNode(assetManager,"Sounds/sounds/UI/placeBuilding.ogg",false));
   }
   
   public void playUISound(String name)
   {
        AudioNode track = uiSounds.get(name);
        track.setVolume(0.5f);
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
           case GameContainer.SLAVE_DIGGING:
               if(buildBuilding.getStatus() == Status.Playing){
                   return;
               }
               buildBuilding.play();
               break;
       }
   }
   
   public static void playRandomWarriorSound(){
       switch((int)(Math.round(Math.random() * 2.0))){
           case 0:
               warrior1.play();
               break;
           case 1:
               warrior2.play();
               break;
           case 2:
               warrior3.play();
               break;
       }
   }
   
   public static void playWarriorMarkedSound(){
       warrior4.play();
   }
   
   public static void playSouls(){
       souls.play();
   }
   
   public static void buildRoom(){
       if(buildRoom.getStatus().equals(Status.Playing)){
           return;
       }
       buildRoom.play();
   }
   
   public static void playQuestFinished(){
       quest.play();
   }
}
