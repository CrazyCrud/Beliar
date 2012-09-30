package beliar;

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
                updateTimer(tpf);
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
                updateRessources();
                updateDisplay();
            }
        }

	public void updateRessources()
	{
		//Adam
		PlayerRessources.adam+= (PlayerRessources.adamCreatorBIG*GameContainer.ADAMBIG);
		PlayerRessources.adam+= (PlayerRessources.adamCreatorMIDDLE*GameContainer.ADAMMIDDLE);
		PlayerRessources.adam+= (PlayerRessources.adamCreatorSMALL*GameContainer.ADAMSMALL);
		
		//Kythos
		PlayerRessources.kythos+= (PlayerRessources.kythosCreatorBIG*GameContainer.KYTHOSBIG);
		PlayerRessources.kythos+= (PlayerRessources.kythosCreatorMIDDLE*GameContainer.KYTHOSMIDDLE);
		PlayerRessources.kythos+= (PlayerRessources.kythosCreatorSMALL*GameContainer.KYTHOSSMALL);
		
		//Mara
		PlayerRessources.mara+= (PlayerRessources.maraCreatorBIG*GameContainer.MARABIG);
		PlayerRessources.mara+= (PlayerRessources.maraCreatorMIDDLE*GameContainer.MARAMIDDLE);
		PlayerRessources.mara+= (PlayerRessources.maraCreatorSMALL*GameContainer.MARASMALL);
		
		System.out.println("ADAM:"+PlayerRessources.adam+"\n KYTHOS:"+PlayerRessources.kythos+"\n MARA:"+PlayerRessources.mara);
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
	

}




