package beliar;

import Units.ProductionBuilding;
import Units.BuildingController;
import Units.UnitController;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;


public class GameSimulation extends AbstractAppState{
	
	private SimpleApplication app;
	private float float_timerSoulProduction, float_timerGoodsProduction;
        private static final int SOUL_PRODUCTION = 0;
        private static final int GOODS_PRODUCTION = 1;
       
	public GameSimulation(AppStateManager stateManager, SimpleApplication app) {
            System.out.println("GameSimulation: Constructor");
            this.app = app;
            resetTimer(SOUL_PRODUCTION);
            resetTimer(GOODS_PRODUCTION);
	}
	
        @Override
        public void initialize(AppStateManager stateManager, Application app){
            System.out.println("GameSimulation: Initialize()");
            super.initialize(stateManager,app);
        }
        
        @Override
        public void stateAttached(AppStateManager stateManager) {
            System.out.println("GameSimulation: stateAttached()");
        }

        @Override
        public void stateDetached(AppStateManager stateManager){
            System.out.println("GameSimulation: stateDetached()");
        }

        @Override
        public void update(float tpf){      
            if(isEnabled()){
                updateDisplay();
                updateTimerSoulProduction(tpf);
                updateTimerGoodsProduction(tpf);
                checkForQuests();
            }else{
                
            }           
        }
 
        private void updateTimerSoulProduction(float tpf){
            float_timerSoulProduction += tpf;
            if(float_timerSoulProduction > GameContainer.UPDATE_PERIOD_SOUL_PRODUCTION){
                resetTimer(SOUL_PRODUCTION);
                createDoomed();                
                checkForSalvationOfSouls();
            }
        }
        
        private void updateTimerGoodsProduction(float tpf) {
            float_timerGoodsProduction += tpf;
            if(float_timerGoodsProduction > GameContainer.UPDATE_PERIOD_GOODS_PRODUCTION){
                resetTimer(GOODS_PRODUCTION);
                updateProductionBuildings();
            }
        }
        
        private void resetTimer(int whichTimer){
            switch(whichTimer){
                case SOUL_PRODUCTION:
                    float_timerSoulProduction = 0;
                    break;
                case GOODS_PRODUCTION:
                    float_timerGoodsProduction = 0;
                    break;
            }
        }
        
        private void updateDisplay(){
            if(this.app.getStateManager().getState(InGameInputs.class) == null){
                return;
            }
            this.app.getStateManager().getState(InGameInputs.class).ressourcesChanged();
        }
	
	public void calculateDarkness()
	{
		
	}
	
	public void checkGameStatus()
	{
            if (PlayerRessources.healthCentre<=0)
                    PlayerRessources.isLost=true;
	}
	
	public void doScience()
	{
		
	}
	
        public void createDoomed()
        {
            int countSoulAbyss= PlayerRessources.soulAbyssOfPlayer;
            
            PlayerRessources.soulsCount += countSoulAbyss*(PlayerRessources.darkness*(int)(Math.random()*10));
        }
        
    public void reduceRessources(int[]bill)
    {
        PlayerRessources.adam -= bill[0];
        PlayerRessources.kythos -= bill[1];
        PlayerRessources.mara -= bill[2];
    }  
    

    public boolean checkCostsBuilding(int whichBuilding, int size)
    {
        int [] cost = {0,0,0};
        switch(whichBuilding){
            case GameContainer.ADAM_BUILDING:
                if(size == GameContainer.ADAM_SMALL_SIZE){
                    cost = GameContainer.COSTADAMSMALL;
                }else if(size == GameContainer.ADAM_MIDDLE_SIZE){
                    cost = GameContainer.COSTADAMMIDDLE;
                }else{
                    cost = GameContainer.COSTADAMBIG;
                }
                break;
            case GameContainer.KYTHOS_BUILDING:
                if(size == GameContainer.KYTHOS_SMALL_SIZE){
                    cost = GameContainer.COSTKYTHOSSMALL;
                }else if(size == GameContainer.KYTHOS_MIDDLE_SIZE){
                    cost = GameContainer.COSTKYTHOSMIDDLE;
                }else{
                    cost = GameContainer.COSTKYTHOSBIG;
                }
                break;
            case GameContainer.MARA_BUILDING:
                if(size == GameContainer.MARA_SMALL_SIZE){
                    cost = GameContainer.COSTMARASMALL;
                }else if(size == GameContainer.MARA_MIDDLE_SIZE){
                    cost = GameContainer.COSTMARAMIDDLE;
                }else{
                    cost = GameContainer.COSTMARABIG;
                }
                break;
        }
        if((PlayerRessources.adam>=cost[0])  &&(PlayerRessources.kythos>=cost[1])&&(PlayerRessources.mara>=cost[2]))
            return true;
        else
            return false;
    }
    
    private void updateProductionBuildings() {
        for (ProductionBuilding myBuilding : BuildingController.getProductionBuildings()) {
            if (myBuilding.hasGoodies()) {
                int type = myBuilding.getType();

                switch (type) {
                    case GameContainer.ADAM_BUILDING:
                        PlayerRessources.adam += myBuilding.getGoods();
                        break;
                    case GameContainer.KYTHOS_BUILDING:
                        PlayerRessources.kythos += myBuilding.getGoods();
                        break;
                    case GameContainer.MARA_BUILDING:
                        PlayerRessources.mara += myBuilding.getGoods();
                        break;
                }
            }
        }
    }
    
    private void reduceSouls(int count)
    {
        PlayerRessources.soulsCount-=count;
    }
    
    private void checkForSalvationOfSouls()
      {
        //System.out.println("Freiheit der Seelen?");
        if(PlayerRessources.soulsCount> GameContainer.freeSouls)
        {  
            //System.out.println("Freiheit der Seelen? CHECK");
            PlayerRessources.chanceForSalvation =+ PlayerRessources.soulsCount/2;
        }
        
        if(PlayerRessources.chanceForSalvation >= GameContainer.soulsRate)
        {
            //System.out.println("Freiheit der Seelen? JA!");
            reduceSouls((int)PlayerRessources.chanceForSalvation);
            if(PlayerRessources.soulsCount <= 0){
                PlayerRessources.soulsCount = 0; 
            }  
            //System.out.println("SoulsCount"+PlayerRessources.soulsCount);
        }
      }

    private boolean checkForQuests() {
        if(isQuest1Completed() && isQuest2Completed() && isQuest3Completed()){
            return true;
        }
        return false;
    }

    protected boolean isQuest1Completed() {
        if(PlayerRessources.adam > 1000 && PlayerRessources.kythos > 600 && PlayerRessources.mara > 600){
            return true;
        }
        return false;
    }

    protected boolean isQuest2Completed() {
        if(UnitController.getUnits().size() > 20){
            return true;
        }
        return false;
    }

    protected boolean isQuest3Completed() {
        if(PlayerRessources.darkness > 50){
            return true;
        }
        return false;
    }
}