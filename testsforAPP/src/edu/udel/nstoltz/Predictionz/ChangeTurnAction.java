package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Action;

public class ChangeTurnAction implements Action<PredictionGame> {

	@Override
	public boolean isValid(PredictionGame game) {
		if (game.getTurn() == game.getPlayerList().get(0)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void update(PredictionGame game) {
		game.changeTurn();
	}

	public String toString() {
		return "The turn has been changed";
	}
}
