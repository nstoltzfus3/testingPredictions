//package edu.udel.nstoltz.Predictionz;
//
//import junit.framework.TestCase;
//import edu.udel.jatlas.gameframework.Action;
//import edu.udel.jatlas.gameframework.Position;
//import edu.udel.nstoltz.Predictionz.PredictionGame;
//
//public class PredictionGameTests extends TestCase {
//	
//	public static PredictionGame createStartGame() {
//		return PredictionGame.makeDefaultPredictionGame();
//	}
//	
//	public static PredictionGame createMidGame() {
//		PredictionGame game = createStartGame();
//		Fighter fighter1 = new Fighter(new Position(3,3), 1);
//		Fighter fighter2 = new Fighter(new Position(5,3), 2);
//		game.getUnitList().add(fighter1);
//		game.getUnitList().add(fighter2);
//		game.setDead1Units(2);
//		game.setDead2Units(3);
//		return game;
//	}
//	
//	public static PredictionGame createEndGame() {
//		PredictionGame game = createStartGame();
//		Fighter fighter1 = new Fighter(new Position(0, 1), 2);
//		Fighter fighter2 = new Fighter(new Position(1, 0), 2);
//		game.getUnitList().add(fighter1);
//		game.getUnitList().add(fighter2);
//		game.setDead1Units(5); //loses by dead units
//		return game;
//	}
//	
//	public static PredictionGame createEndGame1() {
//		PredictionGame game = createStartGame();
//		Fighter fighter1 = new Fighter(new Position(0, 1), 2);
//		Fighter fighter2 = new Fighter(new Position(1, 0), 2);
//		game.getUnitList().add(fighter1);
//		game.getUnitList().add(fighter2);
//		game.getPlayer1().getBase().setHP(0);//loses by dead base
//		return game;
//	}
//	
//	public static Attack createAttackAction () {
//		return new Attack('n', new Fighter(new Position(5,5), 2));
//	}
//	
//	public static BuildNewUnit createNewBuildAction() {
//		return new BuildNewUnit(new Player(1), 's', new Fighter(1));
//	}
//	
//	public static MoveUnit createMoveUnitAction() {
//		return new MoveUnit('s', new Fighter(1));
//	}
//	
//	public static QueueAction createQueueAction() {
//		return new QueueAction(createMoveUnitAction());
//	}
//	
//	public void test_isEmpty() {
//		PredictionGame game = createStartGame();
//		for (int i =0; i < 10 ; i ++) {
//			for (int j = 0; j < 10 ; j ++) {
//				if ((i == 1 && j == 1) || (i == 7 && j == 7)) {
//					assertFalse(game.isEmpty(new Position(i,j)));
//				}
//				else {
//				assertTrue(game.isEmpty(new Position(i, j)));
//				}
//			}
//		}
//
//
//	}
//	
//	/**
//	 * Because many of the functions in this game are based off of previous functions (i.e. select status)
//	 * I decided to test a run through of a game before I started testing specific instances of methods.
//	 * The full status of a game is printed at the end of each turn.
//	 */
//	public void test_FullGameTest() {
//		PredictionGame game = createStartGame();
//		//turn 1
//		Action<PredictionGame> selectBase1 = new SelectUnit(new Position(1,1));
//		assertTrue(selectBase1.isValid(game));
//		game.perform(selectBase1);
//		assertTrue(game.getPlayer1().getBase().getSelected());
//		
//		Action<PredictionGame> selectBase2 = new SelectUnit(new Position(7,7));//not Player 2's turn
//		assertFalse(selectBase2.isValid(game));
//		
//		Action<PredictionGame> Buildn1 = new BuildNewUnit(game.getPlayer1(), 's', new Fighter(1));
//		assertTrue(Buildn1.isValid(game));
//		game.perform(Buildn1);
//		
//
//		Action<PredictionGame> selectSouthFighter = new SelectUnit(new Position(1,2));
//		game.perform(selectSouthFighter);
//
//		Action<PredictionGame> moveSouthFighterS = new MoveUnit('s', game.getUnit(new Position(1,2)));
//		assertTrue(moveSouthFighterS.isValid(game));
//		game.perform(moveSouthFighterS);
//		assertEquals("Fighter", game.getUnit(new Position(1,3)).getName());
//		
//		Action<PredictionGame> moveSouthFighterS2 = new MoveUnit('s', game.getUnit(new Position(1,3)));
//		assertTrue(moveSouthFighterS.isValid(game));
//		game.perform(moveSouthFighterS2);
//		assertEquals("Fighter", game.getUnit(new Position(1,4)).getName());
//
//		Action<PredictionGame> moveSouthFighterE = new MoveUnit('e', game.getUnit(new Position(1,4)));
//		assertTrue(moveSouthFighterS.isValid(game));
//		game.perform(moveSouthFighterE);
//		assertEquals("Fighter", game.getUnit(new Position(2,4)).getName());
//
//		Action<PredictionGame> moveSouthFighterE2 = new MoveUnit('e', game.getUnit(new Position(2,4)));
//		assertTrue(moveSouthFighterS.isValid(game));
//		game.perform(moveSouthFighterE2);
//		assertEquals("Fighter", game.getUnit(new Position(3,4)).getName());
//		
//		System.out.println(game.fullStatus());
//
//		game.changeTurn();
//		//turn 2
//		assertTrue(selectBase2.isValid(game));
//		assertFalse(selectBase1.isValid(game));
//		game.perform(selectBase2);
//		
//				
//		Action<PredictionGame> Buildn2 = new BuildNewUnit(game.getPlayer2(), 'n', new Fighter(2));
//		assertTrue(Buildn2.isValid(game));
//		game.perform(Buildn2);
//		
//		Action<PredictionGame> Buildw2 = new BuildNewUnit(game.getPlayer2(), 'w', new Fighter(2));
//		assertFalse(Buildw2.isValid(game));
//		
//		Action<PredictionGame> selectP2Fighter = new SelectUnit(new Position(7,6));
//		assertTrue(selectP2Fighter.isValid(game));
//		game.perform(selectP2Fighter);
//		
//		Action<PredictionGame> moveNorthFighterN = new MoveUnit('n', game.getUnit(new Position(7,6)));
//		assertTrue(moveNorthFighterN.isValid(game));
//		game.perform(moveNorthFighterN);
//		assertEquals("Fighter", game.getUnit(new Position(7,5)).getName());
//		
//		Action<PredictionGame> moveNorthFighterW = new MoveUnit('w', game.getUnit(new Position(7,5)));
//		assertTrue(moveNorthFighterW.isValid(game));
//		game.perform(moveNorthFighterW);
//		assertEquals("Fighter", game.getUnit(new Position(6,5)).getName());
//
//		Action<PredictionGame> moveNorthFighterN2 = new MoveUnit('n', game.getUnit(new Position(6,5)));
//		assertTrue(moveNorthFighterN2.isValid(game));
//		game.perform(moveNorthFighterN2);
//		assertEquals("Fighter", game.getUnit(new Position(6,4)).getName());
//		
//		Action<PredictionGame> moveNorthFighterW2 = new MoveUnit('w', game.getUnit(new Position(6,4)));
//		assertTrue(moveNorthFighterW2.isValid(game));
//		game.perform(moveNorthFighterW2);
//		assertEquals("Fighter", game.getUnit(new Position(5,4)).getName());
//		
//		System.out.println(game.fullStatus());
//
//		game.changeTurn();
//		//turn 3
//		Action<PredictionGame> selectP1Fighter = new SelectUnit(new Position(3,4));
//		assertTrue(selectP1Fighter.isValid(game));
//		game.perform(selectP1Fighter);
//		
//		Action<PredictionGame> moveSouthFighterE3 = new MoveUnit('e', game.getUnit(new Position(3,4)));
//		assertTrue(moveSouthFighterE3.isValid(game));
//		game.perform(moveSouthFighterE3);
//		assertEquals("Fighter", game.getUnit(new Position(4,4)).getName());
//
//		Action<PredictionGame> moveSouthFighterE4 = new MoveUnit('e', game.getUnit(new Position(4,4)));
//		assertFalse(moveSouthFighterE4.isValid(game));
//		game.perform(moveSouthFighterE4);
//
//		Action<PredictionGame> attack1E = new Attack('e', game.getUnit(new Position(4,4)));
//		assertTrue(attack1E.isValid(game));
//		game.perform(attack1E);
//
//		assertFalse(attack1E.isValid(game));
//
//		Action<PredictionGame> moveSouthFighterS3 = new MoveUnit('s', game.getUnit(new Position(4,4)));
//		assertTrue(moveSouthFighterS3.isValid(game));
//		game.perform(moveSouthFighterS3);
//		
//		Action<PredictionGame> moveSouthFighterS4 = new MoveUnit('s', game.getUnit(new Position(4,5)));
//		assertTrue(moveSouthFighterS4.isValid(game));
//		game.perform(moveSouthFighterS4);
//		
//		Action<PredictionGame> moveSouthFighterS5 = new MoveUnit('s', game.getUnit(new Position(4,6)));
//		assertTrue(moveSouthFighterS5.isValid(game));
//		game.perform(moveSouthFighterS5);
//				
//		game.perform(selectBase1);
//
//		Action<PredictionGame> Builds1 = new BuildNewUnit(game.getPlayer1(), 's', new Fighter(1));
//		assertTrue(Builds1.isValid(game));
//		game.perform(Builds1);
//		
//		System.out.println(game.fullStatus());
//
//		game.changeTurn();
//		//turn 4
//		game.perform(selectBase2);
//		game.perform(Buildw2);
//		
//		Action<PredictionGame> selectW2 = new SelectUnit(new Position(6,7));
//		game.perform(selectW2);
//
//		Action<PredictionGame> moveWestFightW = new MoveUnit('w', game.getUnit(new Position(6,7)));
//		game.perform(moveWestFightW);
//		
//		Action<PredictionGame> AttackWestFightW = new Attack('w', game.getUnit(new Position(5,7)));
//		game.perform(AttackWestFightW);
//		
//		Action<PredictionGame> moveWestFightN = new MoveUnit('n', game.getUnit(new Position(5,7)));
//		game.perform(moveWestFightN);
//		
//		Action<PredictionGame> moveWestFightN2 = new MoveUnit('n', game.getUnit(new Position(5,6)));
//		game.perform(moveWestFightN2);
//		
//		Action<PredictionGame> moveWestFightW2 = new MoveUnit('w', game.getUnit(new Position(5,5)));
//		game.perform(moveWestFightW2);
//		
//		System.out.println(game.fullStatus());
//		game.changeTurn();
//		//turn5
//		Action<PredictionGame> selectSouthFight = new SelectUnit(new Position(4,7));
//		game.perform(selectSouthFight);
//		
//		Action<PredictionGame> moveUpAndKillThoseScrubsWithSuperPowers = new MoveUnit('n', game.getUnit(new Position(4,7)));
//		game.perform(moveUpAndKillThoseScrubsWithSuperPowers);
//		
//		Action<PredictionGame> moveUpAndKillThoseScrubsWithSuperPowers1 = new MoveUnit('e', game.getUnit(new Position(4,6)));
//		game.perform(moveUpAndKillThoseScrubsWithSuperPowers1);
//		
//		Action<PredictionGame> moveUpAndKillThoseScrubsWithSuperPowers2 = new MoveUnit('n', game.getUnit(new Position(5,6)));
//		game.perform(moveUpAndKillThoseScrubsWithSuperPowers2);
//		
//		Action<PredictionGame> attackUp = new Attack('n', game.getUnit(new Position(5,5)));
//		game.perform(attackUp);
//		
//		game.setDead1Units(5); //Player 2 is a cheater
//		
//		System.out.println(game.fullStatus());
//	}
//	
//	public void test_SelectUnit() {
//		PredictionGame game = createMidGame();
//		for (Unit e : game.getUnitList()) {
//			assertFalse(e.getSelected());
//		}
//		game.selectAll();
//		for (Unit e : game.getUnitList()) {
//			assertTrue(e.getSelected());
//		}
//	}
//	
//	public void test_MoveUnit() {
//		PredictionGame game = createMidGame();
//		game.selectAll();
//		
//		Action<PredictionGame> move33n = new MoveUnit('n', game.getUnit(new Position(3,3)));
//		Action<PredictionGame> move33w = new MoveUnit('w', game.getUnit(new Position(3,3)));
//		Action<PredictionGame> move33s = new MoveUnit('s', game.getUnit(new Position(3,3)));
//		Action<PredictionGame> move33e = new MoveUnit('e', game.getUnit(new Position(3,3)));
//		
//		assertTrue(move33n.isValid(game));
//		assertTrue(move33w.isValid(game));
//		assertTrue(move33s.isValid(game));
//		assertTrue(move33e.isValid(game));
//		
//		game.getUnit(new Position(3,3)).setPosition(new Position(0,1));
//		//collision with the sides of the map and Player 1's base.
//		Action<PredictionGame> move01w = new MoveUnit('w', game.getUnit(new Position(0,1)));
//		Action<PredictionGame> move01e = new MoveUnit('e', game.getUnit(new Position(0,1)));
//		assertFalse(move01w.isValid(game));
//		assertFalse(move01e.isValid(game));
//	}
//	
//	public void test_BuildNewUnit() {
//		PredictionGame game = createMidGame();
//		game.selectAll();
//		
//		Action<PredictionGame> Buildn1 = new BuildNewUnit(game.getPlayer1(), 'n', new Fighter(1));
//		Action<PredictionGame> Buildw1 = new BuildNewUnit(game.getPlayer1(), 'w', new Fighter(1));
//		Action<PredictionGame> Builde1 = new BuildNewUnit(game.getPlayer1(), 'e', new Fighter(1));
//		Action<PredictionGame> Builds1 = new BuildNewUnit(game.getPlayer1(), 's', new Fighter(1));
//		
//		Action<PredictionGame> Buildn2 = new BuildNewUnit(game.getPlayer2(), 'n', new Fighter(2));
//		Action<PredictionGame> Buildw2 = new BuildNewUnit(game.getPlayer2(), 'w', new Fighter(2));
//		Action<PredictionGame> Builde2 = new BuildNewUnit(game.getPlayer2(), 'e', new Fighter(2));
//		Action<PredictionGame> Builds2 = new BuildNewUnit(game.getPlayer2(), 's', new Fighter(2));
//		
//		assertTrue(Buildn1.isValid(game));
//		assertTrue(Builde1.isValid(game));
//		assertTrue(Builds1.isValid(game));
//		assertTrue(Buildw1.isValid(game));
//		
//		assertFalse(Buildn2.isValid(game));
//		assertFalse(Builde2.isValid(game));
//		assertFalse(Builds2.isValid(game));
//		assertFalse(Buildw2.isValid(game));
//		
//		game.changeTurn();
//		game.selectAll();
//		
//		assertTrue(Buildn2.isValid(game));
//		assertTrue(Builde2.isValid(game));
//		assertTrue(Builds2.isValid(game));
//		assertTrue(Buildw2.isValid(game));
//		
//		game.perform(Builde2);
//		assertFalse(Builds2.isValid(game)); //one unit was already built this turn.
//		game.changeTurn();
//		game.changeTurn();
//		game.selectAll();
//		assertFalse(Builde2.isValid(game)); //there is a unit in the way
//	}
//	
//
//	
//	public void test_Attack() {
//		PredictionGame game = createMidGame();
//		game.selectAll();
//		game.getUnit(new Position(3,3)).setPosition(new Position(0,0));//player 1's fighter, can attack this turn.
//		game.getUnit(new Position(5,3)).setPosition(new Position(1,0));//player 2's fighter, can't attack this turn.
//		Action<PredictionGame> attackE = new Attack('e', game.getUnit(new Position(0,0)));
//		assertTrue(attackE.isValid(game));
//		game.perform(attackE);
//		assertFalse(attackE.isValid(game));//1 attack per selected unit per turn
//
//		assertEquals(25, game.getUnit(new Position(1,0)).getHP());
//		game.changeTurn();
//		game.selectAll();
//		Action<PredictionGame> attackS = new Attack('s', game.getUnit(new Position(1,0)));
//		game.perform(attackS);
//		assertEquals(65, game.getPlayer1().getBase().getHP());
//		
//		game.changeTurn();
//		game.changeTurn();
//		game.selectAll();
//		Action<PredictionGame> attackN = new Attack('n', game.getUnit(new Position(1,0)));
//		assertTrue(attackN.isValid(game));//its fine to attack out of bounds, go ahead, see what's out there...
//	}
//	
//	public void test_QueueAction() {
//		
//	}
//	
//	public void test_getScore() {
//		PredictionGame game = createMidGame();
//		game.selectAll();
//		int lower = game.getScore(1);
//		game.getUnit(new Position(3,3)).setPosition(new Position(6,7));
//		int higher = game.getScore(1);
//		
//		Action<PredictionGame> attackE = new Attack('e', game.getUnit(new Position(6,7)));
//		Action<PredictionGame> pass = new ChangeTurnAction();
//		
//		assertTrue(higher > lower);
//		System.out.println(game.fullStatus());
//		
//		game.changeTurn();
//		game.changeTurn();
//		
//		Action<PredictionGame> buildTest = new SelectUnit(new Position(1,1));
//		Action<PredictionGame> buildTest1 = new BuildNewUnit(game.getPlayer1(), 'n', new Fighter(1));
//		game.perform(buildTest);
//		
//		assertTrue(buildTest1.isValid(game));
//		assertTrue(buildTest1.isValid(game));
//		PredictionGameAI player11 = new PredictionGameAI(game, "Player1", 0);
//		PredictionGameAI player22 = new PredictionGameAI(game, "Player2", 0);
//
//        game.addGameListener(player11);
//        for (Action<PredictionGame>  action : player11.getAllValidActions(game)) {
//        	System.out.println(action);
//        }
//        
//        System.out.println(player11.getBestAction(game));
//        game.perform(player11.getBestAction(game));
//        for (Action<PredictionGame>  action : player11.getAllValidActions(game)) {
//        	System.out.println(action);
//        }
//        //game.perform(player11.getBestAction(game));
//        System.out.println(game.fullStatus());
//        
//        Unit bill = game.getUnit(new Position(6,7));
//        while (true) {
//        	System.out.println(game);
//        	System.out.println(bill.getMoveP());
//        	if (game.getTurn() == 1) {
//        		game.perform(player11.getBestAction(game));
//        	}
//        	else {
//        		game.perform(player22.getBestAction(game));
//        	}
//        	if (game.isEnd()) {
//        		System.out.println(game.fullStatus());
//        		break;
//        	}
//        }
//        
//
//
//	}
//	
//	public void test_AIfighting() {
//		PredictionGame game = createMidGame();
//		PredictionGameAI player11 = new PredictionGameAI(game, "Player1", 0);
//		PredictionGameAI player22 = new PredictionGameAI(game, "Player2", 0);
//		game.changeTurn();
//		game.selectAll();
//		game.getUnit(new Position(3,3)).setPosition(new Position(6,7));
//		game.getUnit(new Position(5,3)).setPosition(new Position(5,7));
//		Action<PredictionGame> asdfasdf = new Attack('e', game.getUnit(new Position(5,7)));
//		game.perform(asdfasdf);
//
//		System.out.println(game.fullStatus());
//		game.changeTurn();
//		game.changeTurn();
//		game.selectAll();
//		player11.getHeuristicScore(asdfasdf, game);
//		game.perform(asdfasdf);
//		System.out.println(game.fullStatus());
//		
//		player11.getHeuristicScore(asdfasdf, game);
//		
////		Action<PredictionGame> attackE = new Attack('e', game.getUnit(new Position(6,7)));
////		for (Position pos :		((Attack)attackE).getDamageArea()) {
////			System.out.println(pos);
////		}
////		Action<PredictionGame> moves = new MoveUnit('w', game.getUnit(new Position(6,7)));
////		
////		System.out.println(attackE.isValid(game));
////		game.perform(attackE);
////	
//		
////		Action<PredictionGame> select1 = new SelectUnit(new Position(1,1));
////		Action<PredictionGame> select = new SelectUnit(new Position(7,7));
////		assertTrue(select1.isValid(game));
////		assertTrue(select.isValid(game));
////		game.perform(select);
////		System.out.println(game.fullStatus());
////		Action<PredictionGame> build1 = new BuildNewUnit(game.getPlayer2(), 'w', new Fighter(2));
////		for (Unit e : game.getUnitList()) {
////			System.out.println(e.toString() + ": I am selected -- " + e.getSelected());
////		}
////		System.out.println(game.getPlayer2().getBase().getSelected());
////		assertTrue(build1.isValid(game));
////		
//
//		
//		
//		
////        while (true) {
////        	System.out.println(game);
////        	if (game.getTurn() == 1) {
////        		game.perform(player11.getBestAction(game));
////        	}
////        	else {
////        		game.perform(player22.getBestAction(game));
////        	}
////        	if (game.isEnd()) {
////        		System.out.println(game.fullStatus());
////        		break;
////        	}
////        }
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(createStartGame());
//		System.out.println(createMidGame());
//		System.out.println(createEndGame());
//		System.out.println(createEndGame1());
//		System.out.println(createAttackAction());
//		System.out.println(createNewBuildAction());
//		System.out.println(createMoveUnitAction());
//		System.out.println(createQueueAction());
//	}
//}
