package edu.udel.nstoltz.Predictionz;

import junit.framework.TestCase;

public class SaveStateTests extends TestCase{
	
	public static boolean playerConst(PredictionGame game1) {
		PredictionGame game2 = game1.saveGameState();
		return game1 == game2;
	}

	/**
	 * Tests the state of a game to determine if it matches it's previous level
	 */
	public static void main(String[] args) {
		PredictionGame game1 = PredictionGame.makeDefaultPredictionGame();
		assertTrue(playerConst(game1));
		assertEquals(game1, game1);
		//naggors
	}

}
