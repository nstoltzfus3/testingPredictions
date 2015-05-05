package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Action;
//insert a queue for action button that will "refresh" the next unit you click on, save the game state(prior to refresh), 1 unit at a time
//then save each action made by that unit. Reload the Save State. This unit will be marked for queue and will be forced to carry out those actions
//AFTER the unit is clicked, also, the player will have 1 less select actions for the turn per marked Unit. After being clicked on, the player
//is stunned and cannot make any further actions, or button presses until all queued actions for a clicked unit have been completed.

//save state must be tested for accuracy prior to implementation
public class QueueAction implements Action<PredictionGame>{
	private Player player;
	private Action[] action = new Action[10];
	
	
	public QueueAction(Action action) {
		for(int i = 0 ; i < 10; i++) {
			if (this.action[i] == null) { //requires testing
				this.action[i] = action;
			}
		}
	}

	@Override
	public boolean isValid(PredictionGame game) {
		if (this.action.length >= 10) {
			return false;
		}
		return false;
	}

	@Override
	public void update(PredictionGame game) {
		int initialGoldDelivered = 10;
		int goldIncrement = 3;
		for (Action e : this.action) {
			if (e != null) {
				game.perform(e);
				game.getTurn().setIncome(game.getTurn().getIncome() + goldIncrement);
				game.getTurn().setTotalMoney(game.getTurn().getTotalMoney() + initialGoldDelivered);
			}
		}	
		
	}
	
	public String toString() {
		String action1 = action[0].toString();
		String action2 = "";
		if (this.action.length == 2) {
			action2 = action[1].toString();
		}

		return "Action(s) of\nQueue 1: " +action1+"\nQueue 2: " +action2+"\nhas/have been added to the queue";
	}
	

}
