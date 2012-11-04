package beliar;

import Units.ProductionBuilding;
import Units.Unit;
import beliar.GameContainer;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;


public class GameSimulation extends AbstractAppState{
	
	private SimpleApplication app;
        private AppStateManager stateManager;
	private float timer;
        
	public GameSimulation(AppStateManager stateManager, SimpleApplication app) {
            System.out.println("GameSimulation");
            this.app = app;
            this.stateManager = stateManager;
            resetTimer();
	}
	
        @Override
        public void initialize(AppStateManager stateManager, Application app){
            System.out.println("GameSimulation: Initialize()");
            super.initialize(stateManager,app);
        }
        
        @Override
        public void stateAttached(AppStateManager stateManager) {
            
        }

        @Override
        public void stateDetached(AppStateManager stateManager){
            System.out.println("GameSimulation: stateDetached()");
        }

        @Override
        public void update(float tpf){      
            if(isEnabled()){
                updateDisplay();
                updateTimer(tpf);
                updateProductionBuildings();
            }else{
                
            }
            
        }
        
        private void resetTimer(){
            timer = 0;
        }
        
        private void updateTimer(float tpf){
            timer += tpf;
            if(timer > GameContainer.UPDATE_PERIOD){
                resetTimer();
                
                createDoomed();
                //produceStuff();
                
                checkForSalvationOfSouls();
            }
        }
        
        private void updateDisplay(){
            //this.app.getStateManager().getState(InGameInputs.class).ressourcesChanged();
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
            
            System.out.println("Verdammte:"+PlayerRessources.soulsCount);
        }
        
        /*
        //DEPRECEATED
        public void produceStuff()
        {
            //Space for Souls
            int adamPlace =     PlayerRessources.adamCreatorBIG*GameContainer.ADAMBIG+
                                PlayerRessources.adamCreatorMIDDLE*GameContainer.ADAMMIDDLE+
                                PlayerRessources.adamCreatorSMALL*GameContainer.ADAMSMALL;
            
            int kythosPlace =   PlayerRessources.kythosCreatorBIG*GameContainer.KYTHOSBIG+
                                PlayerRessources.kythosCreatorMIDDLE*GameContainer.KYTHOSMIDDLE+
                                PlayerRessources.kythosCreatorSMALL*GameContainer.KYTHOSSMALL;
            
            int maraPlace =     PlayerRessources.maraCreatorBIG*GameContainer.MARABIG+
                                PlayerRessources.maraCreatorMIDDLE*GameContainer.MARAMIDDLE+
                                PlayerRessources.maraCreatorSMALL*GameContainer.MARASMALL;
            
            
            int soulsForAdam = (int)(PlayerRessources.soulsCount/100)*PlayerRessources.percentAdam;
            int soulsForKythos= (int)(PlayerRessources.soulsCount/100)*PlayerRessources.percentKythos;
           // int soulsForMara = (int)(PlayerRessources.soulsCount/100)*PlayerRessources.percent;
            
            //int Newsouls = 
            System.out.println("ADAM: "+adamPlace);
            System.out.println("KYTHOS: "+kythosPlace);
            System.out.println("MARA: "+maraPlace);
            
        }
        */
        
    public void reduceRessources(int[]bill)
    {
        PlayerRessources.adam -= bill[0];
        PlayerRessources.kythos -= bill[1];
        PlayerRessources.mara -= bill[2];
    }  
    

    public boolean checkCostsBuilding(int[] bill)
    {
        if((PlayerRessources.adam>=bill[0])  &&(PlayerRessources.kythos>=bill[1])&&(PlayerRessources.mara>=bill[2]))
            return true;
        else
            return false;
    }
    
    private void updateProductionBuildings() {
        //System.out.println("updateProductionBuildings " + PlayerRessources.buildings.size());
        for (ProductionBuilding myBuilding : PlayerRessources.buildings) {
            //System.out.println("MyBuildingsName" + myBuilding.toString());
            myBuilding.update();

            if (myBuilding.hasGoodies()) {
                char type = myBuilding.getType();

                switch (type) {
                    case 'a':
                        PlayerRessources.adam += myBuilding.getGoods();
                        break;
                    case 'k':
                        PlayerRessources.kythos += myBuilding.getGoods();
                        break;
                    case 'm':
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
            PlayerRessources.chanceForSalvation=+PlayerRessources.soulsCount/2;
        }
        
        if(PlayerRessources.chanceForSalvation>=GameContainer.soulsRate)
        {
            
          //System.out.println("Freiheit der Seelen? JA!");
            reduceSouls((int)PlayerRessources.chanceForSalvation);
            if(PlayerRessources.soulsCount<=0){
                PlayerRessources.soulsCount=0; 
            }  
            //System.out.println("SoulsCount"+PlayerRessources.soulsCount);
        }
      }
}




