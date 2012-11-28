/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Menu;
import de.lessvoid.nifty.controls.MenuItemActivatedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;
import java.util.List;
import org.bushe.swing.event.EventTopicSubscriber;

/**
 *
 * @author andministrator
 */
public class InGameInputs extends AbstractAppState implements ScreenController{

    private GameState myGameState;
    private AppStateManager stateManager;
    private SimpleApplication app;
    private Nifty nifty;
    private Screen screen;
    private Element menu, menuFirstRow, menuSecondRow, menuThirdRow, firstRowTop, firstRowBottom, 
            secondRowTop, secondRowBottom, thirdRowTop, thirdRowBottom;
    private Element optionsMenu;
    private int adamRooms, kythosRooms, maraRooms; 
    private static final int ADAM = 0;
    private static final int KYTHOS = 1;
    private static final int MARA = 2;
    protected static final int MELEE = 0;
    protected static final int RANGER = 1;
    protected static final int MAGICIAN = 2;
    
    private int menuState;
    private static final int MENU_CLEAR = 0;
    private static final int MENU_BUILD = 1;
    private static final int BUILD_ROOMS = 3;
    private static final int BUILD_ADAM = 4;
    private static final int BUILD_KYTHOS = 5;
    private static final int BUILD_MARA = 6;
    private static final int MENU_ARMY = 10;
    private static final int MENU_QUESTS = 20;

    
    private static final int BUILDING_LEVEL_ONE = 1;
    private static final int BUILDING_LEVEL_TWO = 2;
    private static final int BUILDING_LEVEL_THREE = 3;
    
    
    public InGameInputs(AppStateManager stateManager, Application app,GameState myState){
        System.out.println("InGameInputs");
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
        this.myGameState=myState;
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind");
        this.nifty = nifty;
        this.screen = screen;
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
        //createOptionsMenu();
        adamRooms = kythosRooms = maraRooms = 0;
        menu = nifty.getScreen("inGameInputs").findElementByName("menu");
        menuFirstRow = nifty.getScreen("inGameInputs").findElementByName("menuFirstRow");
        menuSecondRow = nifty.getScreen("inGameInputs").findElementByName("menuSecondRow");
        menuThirdRow = nifty.getScreen("inGameInputs").findElementByName("menuThirdRow");
        firstRowTop = nifty.getScreen("inGameInputs").findElementByName("firstRowTop");
        firstRowBottom = nifty.getScreen("inGameInputs").findElementByName("firstRowBottom");
        secondRowTop = nifty.getScreen("inGameInputs").findElementByName("secondRowTop");
        secondRowBottom = nifty.getScreen("inGameInputs").findElementByName("secondRowBottom");
        thirdRowTop = nifty.getScreen("inGameInputs").findElementByName("thirdRowTop");
        thirdRowBottom = nifty.getScreen("inGameInputs").findElementByName("thirdRowBottom");
        menuState = MENU_CLEAR;
        menu.hide();      
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        
    }

    @Override
    public void setEnabled(boolean enabled){
        super.setEnabled(enabled);
        if(enabled){
            System.out.println("InGameInputs: setEnabled");
            //this.app.getRootNode().attachChild(rootNode);
        }else{
            System.out.println("InGameInputs: setEnabled(false)");
        }
    }
    
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }
    
    public void onBuild(){
        System.out.println("onBuild: " + menuState);
        myGameState.playSoundEffect(SoundManager.CLICK_2);
        if(menu.isVisible()){
            if(menuState >= MENU_ARMY){
                clearMenu();
                setupBuildIcons();
            }else{
                menu.hide();
                clearMenu();
            }            
        } else{
            menu.show();
            setupBuildIcons();
        }
    }
    
        public void onArmy(){
        System.out.println("onArmy: " + menuState);
        myGameState.playSoundEffect(SoundManager.CLICK_2);
        if(menu.isVisible()){ 
            if(menuState < MENU_ARMY || menuState >= MENU_QUESTS){
                System.out.println("onArmy: menuState < MENU_ARMY");
                clearMenu();
                setupArmyIcons();
            }else{
                menu.hide();
                clearMenu();
            } 
        } else{
            menu.show();
            setupArmyIcons();
        }
    }
        
    public void onQuest(){
        System.out.println("onQuest");
        myGameState.playSoundEffect(SoundManager.CLICK_2);
        if(menu.isVisible()){ 
            if(menuState < MENU_QUESTS){
                System.out.println("onQuest: menuState < MENU_QUEST");
                clearMenu();
                setupQuests();
            }else{
                menu.hide();
                clearMenu();
            } 
        } else{
            menu.show();
            setupQuests();
        }
    }
    
    private void setupBuildIcons(){
        System.out.println("setupBuildIcons");
        menuState = MENU_BUILD;
        
        Element menuLabel = screen.findElementByName("menuText");
        menuLabel.getRenderer(TextRenderer.class).setText("Baumenü");
        Element textThirdRow = screen.findElementByName("textThirdRow");
        textThirdRow.getRenderer(TextRenderer.class).setText("Verfügbarer Räume");
        Element textfirstRow = screen.findElementByName("textFirstRow");
        textfirstRow.getRenderer(TextRenderer.class).setText("Gang anlegen");
        
        
        new ImageBuilder("path"){{
            filename("Images/build_path.png");
            height("95%");
            width("100%h");
            alignCenter();
            interactOnClick("onBuildPath()");   
                }}.build(nifty, screen, firstRowTop);
        
        new ImageBuilder("adamRoom"){{
            filename("Images/adam_room.png");
            //filename("Images/Adam.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("buildAdamMenu()");   
                }}.build(nifty, screen, thirdRowTop);
        
        new ImageBuilder("kythosRoom"){{
            filename("Images/kythos_room.png");
            //filename("Images/Kythos.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("buildKythosMenu()");   
                }}.build(nifty, screen, thirdRowTop);
        
        new ImageBuilder("maraRoom"){{
            filename("Images/mara_room.png");
            //filename("Images/Mara.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("buildMaraMenu()");   
                }}.build(nifty, screen, thirdRowTop);
    }

    private void setupArmyIcons(){    
        System.out.println("InGameInputs: setupArmyIcons");
        menuState = MENU_ARMY;
        
        Element menuText = screen.findElementByName("menuText");
        menuText.getRenderer(TextRenderer.class).setText("Einheiten");
        Element textSecondRow = screen.findElementByName("textSecondRow");
        textSecondRow.getRenderer(TextRenderer.class).setText("Krieger");
        Element textfirstRow = screen.findElementByName("textFirstRow");
        textfirstRow.getRenderer(TextRenderer.class).setText("Sklaven");
        Element textthirdRow = screen.findElementByName("textThirdRow");
        textthirdRow.getRenderer(TextRenderer.class).setText("Verfügbarer Einheiten anwählen");
        clearFirstRowBottom();
        TextBuilder tb = new TextBuilder("SoulBuilder");
        tb.textVAlignCenter();
        tb.textHAlignLeft();
        tb.paddingRight("20px");
        tb.alignLeft();
        tb.width("100%h");
        tb.height("100%");
        tb.font("Interface/Fonts/gill_16.fnt");
        Element textSouls = tb.build(nifty, screen, firstRowBottom);
        textSouls.getRenderer(TextRenderer.class).setText("Anzahl der Verdammten: " + PlayerRessources.getSoulsCount());
        
        new ImageBuilder("slave"){{
            filename("Images/slave_icon.png");
            height("95%");
            width("100%h");
            alignCenter();
            interactOnClick("onBuildSlave()");   
                }}.build(nifty, screen, firstRowTop);
        
        new ImageBuilder("melee"){{
            filename("Images/melee_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onBuildArmy(0)");   
                }}.build(nifty, screen, secondRowTop);
        
        new ImageBuilder("ranger"){{
            filename("Images/magician_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onBuildArmy(1)");   
                }}.build(nifty, screen, secondRowTop);
        
        new ImageBuilder("magician"){{
            filename("Images/ranger_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onBuildArmy(2)");   
                }}.build(nifty, screen, secondRowTop);
        
        new ImageBuilder("melee"){{
            filename("Images/melee_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onMoveArmy(0)");   
                }}.build(nifty, screen, thirdRowTop);
        
        new ImageBuilder("ranger"){{
            filename("Images/magician_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onMoveArmy(1)");   
                }}.build(nifty, screen, thirdRowTop);
        
        new ImageBuilder("magician"){{
            filename("Images/ranger_icon.png");
            height("95%");
            width("100%h");
            paddingRight("10px");
            alignCenter();
            interactOnClick("onMoveArmy(2)");   
                }}.build(nifty, screen, thirdRowTop);
    }
    
    private void setupQuests(){
        System.out.println("InGameInputs: setupQuests");
        menuState = MENU_QUESTS;
        Element menuText = screen.findElementByName("menuText");
        menuText.getRenderer(TextRenderer.class).setText("Deine Aufgaben");
        setUpQuestText();
        myGameState.playSoundEffect(SoundManager.CLICK_2);
    }
    
    public void onOptions(){
        System.out.println("InGameInputs: onOptions()");
        stateManager.getState(GameState.class).setEnabled(false);
        stateManager.getState(InGameInputs.class).setEnabled(false);
        stateManager.getState(PauseState.class).setEnabled(true);
    }
    
    public void onBuildPath(){
        myGameState.playSoundEffect(SoundManager.CLICK);
        PlayerRessources.selectionRoom = ValuesTerrain.HALL;
    }  
       
    public void buildAdamMenu(){
        myGameState.playSoundEffect(SoundManager.CLICK);
        switch(menuState){
            case MENU_BUILD:
                setupBuildingIcons(ADAM);
                setupRoomName(ADAM);
                menuState = BUILD_ADAM;
                break;
            case BUILD_ADAM:
                clearRoomText();
                clearSecondRow();
                menuState = MENU_BUILD;
                break;
           default:
                clearRoomText();
                clearSecondRow();
                setupRoomName(ADAM);
                setupBuildingIcons(ADAM);
                menuState = BUILD_ADAM;
        }
    }
    
    public void buildKythosMenu(){
        myGameState.playSoundEffect(SoundManager.CLICK);
        switch(menuState){
            case MENU_BUILD:
                setupRoomName(KYTHOS);
                setupBuildingIcons(KYTHOS);
                menuState = BUILD_KYTHOS;
                break;
            case BUILD_KYTHOS:
                clearRoomText();
                clearSecondRow();
                menuState = MENU_BUILD;
                break;
            default:
                clearSecondRow();
                clearRoomText();
                setupRoomName(KYTHOS);
                setupBuildingIcons(KYTHOS);
                menuState = BUILD_KYTHOS;
        }
    }
    
    public void buildMaraMenu(){
        myGameState.playSoundEffect(SoundManager.CLICK);
        switch(menuState){
            case MENU_BUILD:
                setupRoomName(MARA);
                setupBuildingIcons(MARA);
                menuState = BUILD_MARA;
                break;
            case BUILD_MARA:
                clearRoomText();
                clearSecondRow();
                menuState = MENU_BUILD;
                break;
            default:
                clearRoomText();
                clearSecondRow();
                setupRoomName(MARA);
                setupBuildingIcons(MARA);
                menuState = BUILD_MARA;
        }
    }
    
    private void setupRoomName(int whichRoom){
        
        System.out.println("setupRoomName");
        Element text = screen.findElementByName("textSecondRow");
        
        switch(whichRoom){
            case ADAM:
                text.getRenderer(TextRenderer.class).setText("Adam Raum");
                break;
            case KYTHOS:
                text.getRenderer(TextRenderer.class).setText("Kythos Raum");
                break;
            case MARA:
                text.getRenderer(TextRenderer.class).setText("Mara Raum");
                break;
        }
    }
    
    private void setupBuildingIcons(int whichBuilding){
        System.out.println("setupBuildingIcons " + whichBuilding);
        final int whichRoom = whichBuilding;        

        new ImageBuilder("buildRoom"){{
            filename("Images/build_room.png");
            alignCenter();
            height("95%");
            width("100%h");
            paddingRight("10px");
            interactOnClick("onBuildRoom(" + whichRoom + ")");
        }}.build(nifty, screen, secondRowTop);
        
        switch(whichBuilding){
            case ADAM:
                new ImageBuilder("buildAdamOne"){{
                    filename("Images/adam_1.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildAdamBuilding(1)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildAdamTwo"){{
                    filename("Images/adam_2.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildAdamBuilding(2)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildAdamThree"){{
                    filename("Images/adam_3.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildAdamBuilding(3)");   
                        }}.build(nifty, screen, secondRowTop);
                return;
            case KYTHOS:
               new ImageBuilder("buildKythosOne"){{
                    filename("Images/kythos_1.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildKythosBuilding(1)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildKythosTwo"){{
                    filename("Images/kythos_2.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildKythosBuilding(2)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildKythosThree"){{
                    filename("Images/kythos_3.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildKythosBuilding(3)");   
                        }}.build(nifty, screen, secondRowTop);
                return;
            case MARA:
                new ImageBuilder("buildMaraOne"){{
                    filename("Images/mara_1.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildMaraBuilding(1)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildMaraTwo"){{
                    filename("Images/mara_2.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildMaraBuilding(2)");   
                        }}.build(nifty, screen, secondRowTop);
                new ImageBuilder("buildMaraThree"){{
                    filename("Images/mara_3.png");
                    height("95%");
                    width("100%h");
                    paddingRight("10px");
                    alignCenter();
                    interactOnClick("buildMaraBuilding(3)");   
                        }}.build(nifty, screen, secondRowTop);
                return;
        }
    }
    
     public void onBuildRoom(String whichRoom){
        System.out.println("onBuildRoom()");
        myGameState.playSoundEffect(SoundManager.CLICK);
        clearSecondRowBottom();
        switch(Integer.parseInt(whichRoom)){
            case ADAM:
                PlayerRessources.selectionRoom = ValuesTerrain.HALLOFANARCHY;
                break;
            case KYTHOS:
                PlayerRessources.selectionRoom = ValuesTerrain.CAVEOFBEAST;
                break;
            case MARA:
                PlayerRessources.selectionRoom = ValuesTerrain.TOMBOFMEMORY;
                break;
        }
        myGameState.clearSelection();
    }
    
    public void buildAdamBuilding(String whichLevel){
        myGameState.playSoundEffect(SoundManager.CLICK);        
        myGameState.clearSelection();
        switch(Integer.parseInt(whichLevel)){
            case BUILDING_LEVEL_ONE:
                setUpBuildingCosts(ADAM, BUILDING_LEVEL_ONE);
                PlayerRessources.selectionRoom = 0;
                myGameState.handleBuildSelection(GameContainer.ADAM_BUILDING, GameContainer.ADAM_SMALL_SIZE);
                updateRessources(ADAM, BUILDING_LEVEL_ONE);
                return;
            case BUILDING_LEVEL_TWO:
                /*
                setUpBuildingCosts(ADAM, BUILDING_LEVEL_TWO);
                PlayerRessources.selectionRoom = 0;
                myGameState.handleBuildSelection(GameContainer.ADAM_BUILDING, GameContainer.ADAM_MIDDLE_SIZE);
                updateRessources(ADAM, BUILDING_LEVEL_TWO);
                 * 
                 */
                clearSecondRowBottom();
                return;
            case BUILDING_LEVEL_THREE:
                /*
                setUpBuildingCosts(ADAM, BUILDING_LEVEL_THREE);
                PlayerRessources.selectionRoom = 0;
                myGameState.handleBuildSelection(GameContainer.ADAM_BUILDING, GameContainer.ADAM_BIG_SIZE);
                updateRessources(ADAM, BUILDING_LEVEL_THREE);
                 * 
                 */
                clearSecondRowBottom();
                return;
        }
    }
    
    public void buildKythosBuilding(String whichLevel){
        myGameState.playSoundEffect(SoundManager.CLICK);
        myGameState.clearSelection();
        switch(Integer.parseInt(whichLevel)){
            case BUILDING_LEVEL_ONE:
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(KYTHOS, BUILDING_LEVEL_ONE);
                updateRessources(KYTHOS, BUILDING_LEVEL_ONE);
                myGameState.handleBuildSelection(GameContainer.KYTHOS_BUILDING, GameContainer.KYTHOS_SMALL_SIZE);
                return;
            case BUILDING_LEVEL_TWO:
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(KYTHOS, BUILDING_LEVEL_TWO);
                updateRessources(KYTHOS, BUILDING_LEVEL_TWO);
                myGameState.handleBuildSelection(GameContainer.KYTHOS_BUILDING, GameContainer.KYTHOS_MIDDLE_SIZE);
                return;
            case BUILDING_LEVEL_THREE:
                /*
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(KYTHOS, BUILDING_LEVEL_THREE);
                updateRessources(KYTHOS, BUILDING_LEVEL_THREE);
                myGameState.handleBuildSelection(GameContainer.KYTHOS_BUILDING, GameContainer.KYTHOS_BIG_SIZE);
                 * 
                 */
                clearSecondRowBottom();
                return;
        }
    }
    
    public void buildMaraBuilding(String whichLevel){
        myGameState.playSoundEffect(SoundManager.CLICK);
        myGameState.clearSelection();
        switch(Integer.parseInt(whichLevel)){
            case BUILDING_LEVEL_ONE:
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(MARA, BUILDING_LEVEL_ONE);
                updateRessources(MARA, BUILDING_LEVEL_ONE);
                myGameState.handleBuildSelection(GameContainer.MARA_BUILDING, GameContainer.MARA_SMALL_SIZE);
                return;
            case BUILDING_LEVEL_TWO:
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(MARA, BUILDING_LEVEL_TWO);
                updateRessources(MARA, BUILDING_LEVEL_TWO);
                myGameState.handleBuildSelection(GameContainer.MARA_BUILDING, GameContainer.MARA_MIDDLE_SIZE);
                return;
            case BUILDING_LEVEL_THREE:
                /*
                PlayerRessources.selectionRoom = 0;
                setUpBuildingCosts(MARA, BUILDING_LEVEL_THREE);
                updateRessources(MARA, BUILDING_LEVEL_THREE);
                 * 
                 */
                clearSecondRowBottom();
                return;
        }
    }
    
    private void setUpBuildingCosts(int whichRoom, int whichLevel){
        clearSecondRowBottom();
        TextBuilder tb = new TextBuilder("CostBuilder");
        tb.textVAlignCenter();
        tb.textHAlignLeft();
        tb.paddingRight("20px");
        tb.alignLeft();
        tb.width("100%h");
        tb.height("100%");
        tb.font("Interface/Fonts/gill_16.fnt");
        Element textCostAdam;
        Element textCostKythos;
        Element textCostMara;
        int [] cost = null; 
        switch(whichRoom){
            case ADAM:
                if(whichLevel == BUILDING_LEVEL_ONE){
                    cost = GameContainer.COSTADAMSMALL;
                }else if(whichLevel == BUILDING_LEVEL_TWO){
                    cost = GameContainer.COSTADAMMIDDLE;
                }else{
                    cost = GameContainer.COSTADAMBIG;
                }
                break;
            case KYTHOS:
                if(whichLevel == BUILDING_LEVEL_ONE){
                    cost = GameContainer.COSTKYTHOSSMALL;
                }else if(whichLevel == BUILDING_LEVEL_TWO){
                    cost = GameContainer.COSTKYTHOSMIDDLE;
                }else{
                    cost = GameContainer.COSTKYTHOSBIG;
                }
                break;
            case MARA:
                if(whichLevel == BUILDING_LEVEL_ONE){
                    cost = GameContainer.COSTMARASMALL;
                }else if(whichLevel == BUILDING_LEVEL_TWO){
                    cost = GameContainer.COSTMARAMIDDLE;
                }else{
                    cost = GameContainer.COSTMARABIG;
                }
                break;
        }
        new ImageBuilder("adamCost"){{
        filename("Images/Adam.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostAdam = tb.build(nifty, screen, secondRowBottom);
        textCostAdam.getRenderer(TextRenderer.class).setText("" + cost[0]);

        new ImageBuilder("kythosCost"){{
        filename("Images/Kythos.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostKythos = tb.build(nifty, screen, secondRowBottom);
        textCostKythos.getRenderer(TextRenderer.class).setText("" + cost[1]);

        new ImageBuilder("maraCost"){{
        filename("Images/Mara.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostMara = tb.build(nifty, screen, secondRowBottom);
        textCostMara.getRenderer(TextRenderer.class).setText("" + cost[2]);
    }
    
    public void onBuildSlave(){
        stateManager.getState(GameState.class).createSlave();
    }
    
    public void onBuildArmy(String whichUnit){
        System.out.println("InGameInputs: onBuildArmy() " + whichUnit);
        switch(Integer.parseInt(whichUnit)){
            case MELEE:
                setUpWarriorCosts(MELEE);
                break;
            case RANGER:
                clearSecondRowBottom();
                break;
            case MAGICIAN:
                clearSecondRowBottom();
                break;
        }
    }
    
    private void setUpWarriorCosts(int whichWarrior){
        clearSecondRowBottom();
        TextBuilder tb = new TextBuilder("CostBuilder");
        tb.textVAlignCenter();
        tb.textHAlignLeft();
        tb.paddingRight("20px");
        tb.alignLeft();
        tb.width("100%h");
        tb.height("100%");
        tb.font("Interface/Fonts/gill_16.fnt");
        Element textCostAdam;
        Element textCostKythos;
        Element textCostMara;
        int [] cost = null; 
        switch(whichWarrior){
            case MELEE:
                cost = GameContainer.COSTMELEE;
                new ImageBuilder("adamCost"){{
                filename("Images/buildwarrior_icon.png");
                height("80%");
                paddingRight("10px");
                width("100%h");
                alignCenter();
                interactOnClick("onBuildWarrior(0)");   
                    }}.build(nifty, screen, secondRowBottom);
                break;
            case RANGER:
                cost = GameContainer.COSTRANGER;
                new ImageBuilder("adamCost"){{
                filename("Images/buildwarrior_icon.png");
                height("80%");
                width("100%h");
                paddingRight("10px");
                alignCenter();
                interactOnClick("onDoNothing()");   
                    }}.build(nifty, screen, secondRowBottom);
                break;
            case MAGICIAN:
                cost = GameContainer.COSTMAGICIAN;
                new ImageBuilder("adamCost"){{
                filename("Images/buildwarrior_icon.png");
                height("80%");
                width("100%h");
                paddingRight("10px");
                alignCenter();
                interactOnClick("onDoNothing()");   
                    }}.build(nifty, screen, secondRowBottom);
                break;
        }
 
        new ImageBuilder("adamCost"){{
        filename("Images/Adam.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostAdam = tb.build(nifty, screen, secondRowBottom);
        textCostAdam.getRenderer(TextRenderer.class).setText("" + cost[0]);

        new ImageBuilder("kythosCost"){{
        filename("Images/Kythos.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostKythos = tb.build(nifty, screen, secondRowBottom);
        textCostKythos.getRenderer(TextRenderer.class).setText("" + cost[1]);

        new ImageBuilder("maraCost"){{
        filename("Images/Mara.png");
        height("80%");
        width("100%h");
        alignCenter();
        interactOnClick("onDoNothing()");   
            }}.build(nifty, screen, secondRowBottom);
        textCostMara = tb.build(nifty, screen, secondRowBottom);
        textCostMara.getRenderer(TextRenderer.class).setText("" + cost[2]);
    }
    
    public void onBuildWarrior(String whichWarrior){
        myGameState.playSoundEffect(SoundManager.CLICK);
        switch(Integer.parseInt(whichWarrior)){
            case MELEE:
                stateManager.getState(GameState.class).createMelee();
                break;
            case RANGER:
                break;
            case MAGICIAN:
                break;
        }
    }
    
    public void onMoveArmy(String whichWarrior){
        switch(Integer.parseInt(whichWarrior)){
            case MELEE:
                stateManager.getState(GameState.class).moveUnits(MELEE);
                break;
            case RANGER:
                break;
            case MAGICIAN:
                break;
        }
    }
    
    private void setUpQuestText(){
        TextBuilder tb = new TextBuilder("QuestBuilder");
        tb.childLayoutAbsoluteInside();
        tb.textHAlignLeft();
        tb.textVAlignCenter();
        tb.wrap(true);
        tb.width("100px");
        tb.height("5px");
        tb.font("Interface/Fonts/gill_16.fnt");
        Element textQuest1 = tb.build(nifty, screen, firstRowTop);
        textQuest1.setConstraintX(new SizeValue(0 + "px"));
        textQuest1.setConstraintY(new SizeValue(0 + "px"));
        Element textQuest2 = tb.build(nifty, screen, secondRowTop);
        textQuest2.setConstraintX(new SizeValue(0 + "px"));
        textQuest2.setConstraintY(new SizeValue(0 + "px"));
        Element textQuest3 = tb.build(nifty, screen, thirdRowTop);
        textQuest3.setConstraintX(new SizeValue(0 + "px"));
        textQuest3.setConstraintY(new SizeValue(0 + "px"));
        textQuest1.getRenderer(TextRenderer.class).setText(GameContainer.getInstance().getQuests(GameContainer.QUEST_1));
        if(app.getStateManager().getState(GameSimulation.class).isQuest1Completed()){
            textQuest2.getRenderer(TextRenderer.class).setText(GameContainer.getInstance().getQuests(GameContainer.QUEST_2));
            if(app.getStateManager().getState(GameSimulation.class).isQuest2Completed()){
                textQuest3.getRenderer(TextRenderer.class).setText(GameContainer.getInstance().getQuests(GameContainer.QUEST_3));
            }
        }
    }
    
    private void clearMenu(){
       System.out.println("clearMenu");
       clearText();
       clearThirdRow();
       clearSecondRow();
       clearFirstRow();
       PlayerRessources.selectionRoom = 0;
    }
    
    private void clearText(){
        Element textMenuLabel = screen.findElementByName("menuText");
        textMenuLabel.getRenderer(TextRenderer.class).setText("");
        Element textSecondRow = screen.findElementByName("textSecondRow");
        textSecondRow.getRenderer(TextRenderer.class).setText("");
        Element textFirstRow = screen.findElementByName("textFirstRow");
        textFirstRow.getRenderer(TextRenderer.class).setText("");
        Element textThirdRow = screen.findElementByName("textThirdRow");
        textThirdRow.getRenderer(TextRenderer.class).setText("");
    }
    
    private void clearRoomText(){
        Element textSecondRow = screen.findElementByName("textSecondRow");
        textSecondRow.getRenderer(TextRenderer.class).setText("");
    }
    
    private void clearFirstRow(){        
        List<Element> visibleElements = firstRowTop.getElements();
        
        for(Element element: visibleElements){
            element.markForRemoval();
        }
        
        visibleElements = firstRowBottom.getElements();
        for(Element element: visibleElements){
            element.markForRemoval();
        }
        
        firstRowTop.layoutElements();
        firstRowBottom.layoutElements();
    }
    
    private void clearSecondRow(){
        List<Element> visibleElements = secondRowTop.getElements();
        
        for(Element element: visibleElements){
            element.markForRemoval();
        }
        
        visibleElements = secondRowBottom.getElements();
        for(Element element: visibleElements){
            element.markForRemoval();
        }
        
        secondRowTop.layoutElements();
        secondRowBottom.layoutElements();
    }
    
    private void clearThirdRow(){
        List<Element> visibleElements = thirdRowTop.getElements();
        
        for(Element element: visibleElements){
            element.markForRemoval();
            element.getClass();
        }
        
        visibleElements = thirdRowBottom.getElements();
        for(Element element: visibleElements){
            element.markForRemoval();
        }
        
        thirdRowTop.layoutElements();
        thirdRowBottom.layoutElements();
    }
    
    private void clearFirstRowBottom(){
        List<Element> visibleElements = firstRowBottom.getElements();
        for(Element element : visibleElements){
            element.markForRemoval();
        }
        firstRowBottom.layoutElements();
    }
    
    private void clearSecondRowBottom(){
        List<Element> visibleElements = secondRowBottom.getElements();
        for(Element element : visibleElements){
            element.markForRemoval();
        }
        secondRowBottom.layoutElements();
    }
    
    private void updateRessources(int whichRessource, int whichLevel){
        switch(whichRessource){
            case ADAM:
                if(whichLevel == BUILDING_LEVEL_ONE){
                     PlayerRessources.adamCreatorSMALL++;
                }
                
                else if(whichLevel == BUILDING_LEVEL_TWO){   
                    PlayerRessources.adamCreatorMIDDLE++;
                }
                
                else if(whichLevel == BUILDING_LEVEL_THREE){            
                    PlayerRessources.adamCreatorBIG++;
                }
                break;
            case KYTHOS:
                if(whichLevel == BUILDING_LEVEL_ONE){
                    PlayerRessources.kythosCreatorSMALL++;
                }else if(whichLevel == BUILDING_LEVEL_TWO){
                    PlayerRessources.kythosCreatorMIDDLE++;
                }else if(whichLevel == BUILDING_LEVEL_THREE){
                    PlayerRessources.kythosCreatorBIG++;
                }
                break;
            case MARA:
                if(whichLevel == BUILDING_LEVEL_ONE){
                    PlayerRessources.maraCreatorSMALL++;
                }else if(whichLevel == BUILDING_LEVEL_TWO){
                    PlayerRessources.maraCreatorMIDDLE++;
                }else if(whichLevel == BUILDING_LEVEL_THREE){
                    PlayerRessources.maraCreatorBIG++;
                }
                break;
        }
    }
    
    public void ressourcesChanged(){
        Element adam = nifty.getScreen("inGameInputs").findElementByName("adamLabel");
        Element kythos = nifty.getScreen("inGameInputs").findElementByName("kythosLabel");
        Element mara = nifty.getScreen("inGameInputs").findElementByName("maraLabel");
        Element darkness = nifty.getScreen("inGameInputs").findElementByName("darknessLabel");
        adam.getRenderer(TextRenderer.class).setText(String.valueOf(PlayerRessources.adam));
        kythos.getRenderer(TextRenderer.class).setText(String.valueOf(PlayerRessources.kythos));
        mara.getRenderer(TextRenderer.class).setText(String.valueOf(PlayerRessources.mara));
        darkness.getRenderer(TextRenderer.class).setText(String.valueOf(PlayerRessources.darkness));
    }
    
    protected void soulCountChanged(){
        if(menuState == MENU_ARMY){
            clearFirstRowBottom();
            TextBuilder tb = new TextBuilder("SoulBuilder");
            tb.textVAlignCenter();
            tb.textHAlignLeft();
            tb.paddingRight("20px");
            tb.alignLeft();
            tb.width("100%h");
            tb.height("100%");
            tb.font("Interface/Fonts/gill_16.fnt");
            Element textSouls = tb.build(nifty, screen, firstRowBottom);
            textSouls.getRenderer(TextRenderer.class).setText("Anzahl der Verdammten: " + PlayerRessources.getSoulsCount());
        }
    }
    
    private void createOptionsMenu(){
        optionsMenu = nifty.createPopup("niftyPopupMenu");
        Menu myMenu = optionsMenu.findNiftyControl("#menu", Menu.class);
        myMenu.setWidth(new SizeValue("400px"));
        myMenu.addMenuItem("Click me!", "Images/quit.png", 
            new menuItem("quit", "Quit")); 
        nifty.subscribe(
            nifty.getScreen("inGameInputs"), 
            myMenu.getId(), 
            MenuItemActivatedEvent.class, 
            new MenuItemActivatedEventSubscriber());
    }
    
    public void closePopup(){
        
    }
    
    public void onDoNothing(){
        
    }
    
    private class menuItem {
        public String id;
        public String name;

        public menuItem(String id, String name){
               this.id= id;
               this.name = name;
        }
    }
    
    private class MenuItemActivatedEventSubscriber 
        implements EventTopicSubscriber<MenuItemActivatedEvent> {
 
        @Override
        public void onEvent(final String id, final MenuItemActivatedEvent event) {
            menuItem item = (menuItem) event.getItem();
            
            if ("quit".equals(item.id)) {
                System.out.println("Quit Game");
            }
        }
  };
}
