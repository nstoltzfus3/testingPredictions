package edu.udel.nstoltz.Predictionz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.jatlas.gameframework.AI;
import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Position;

public class PredictionGameAI extends AI<PredictionGame>{

	public PredictionGameAI(PredictionGame game, String identifier, int turnTimeLength) {
		super(game, identifier, turnTimeLength);
		// TODO Auto-generated constructor stub
	}

	
	
	protected boolean isMyTurn() {
		String string = "Player" + getGame().getTurn().getPlayerNumber();
		return string.equals(getIdentifier());
	}
	
	@Override
	public List<Action<PredictionGame>> getAllValidActions(PredictionGame game) {
		List<Action<PredictionGame>> validActions = new ArrayList<Action<PredictionGame>>();
		validActions.add(new ChangeTurnAction());
		for (Unit e :game.getUnitList()) {
			
			validActions.add(new SelectUnit(e.getPosition()));
			
			if(!(e instanceof Base)) {
				validActions.add(new MoveUnit('n', e));
				validActions.add(new MoveUnit('s', e));
				validActions.add(new MoveUnit('e', e));
				validActions.add(new MoveUnit('w', e));
				
				validActions.add(new Attack('n', e));
				validActions.add(new Attack('s', e));
				validActions.add(new Attack('e', e));
				validActions.add(new Attack('w', e));
			}
		}
		
		Player currentPlayer = game.getPlayerList().get(0);
		validActions.add(new BuildNewUnit(currentPlayer,'s', new Fighter(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'e', new Fighter(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'w', new Fighter(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'n', new Fighter(currentPlayer)));
		
		validActions.add(new BuildNewUnit(currentPlayer,'s', new Archer(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'w', new Archer(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'e', new Archer(currentPlayer)));
		validActions.add(new BuildNewUnit(currentPlayer,'n', new Archer(currentPlayer)));
		
		Iterator<Action<PredictionGame>> i = validActions.iterator();
		while (i.hasNext()) {
			Action<PredictionGame> action = i.next();
			if (!action.isValid(game) || action == null) {
				i.remove();
			}
		}
		System.out.println(game.fullStatus());
		return validActions;
	}

	@Override
	public double getHeuristicScore(Action<PredictionGame> action, PredictionGame game) {
		
//		PredictionGame savedGame = PredictionGame.makeDefaultPredictionGame();
//		for (Unit e : game.getUnitList()) {
//			savedGame.getUnitList().add(e);
//		}
//		savedGame.setPlayer1(game.getPlayer1());
//		savedGame.setPlayer2(game.getPlayer2());
//		savedGame.setNotTurn(game.getNotTurn());
//		savedGame.setTurn(game.getTurn());
		
		double score = 0;
		if (action instanceof Attack) {
			int dmg = ((Attack)action).getUnit().getDmg();
			for (Position pos :((Attack)action).getDamageArea()) {
				if (game.getUnit(pos) != null) {
					Unit damagedUnit = game.getUnit(pos);
					if (damagedUnit.getHP() <= dmg) {
						action.update(game);
						score += game.getScore(game.getTurn());
						game.getUnitList().add(damagedUnit);
						damagedUnit.setHP(damagedUnit.getHP() + dmg);
						
						damagedUnit.getOwner().setDeadUnits(damagedUnit.getOwner().getDeadUnits() - 1);
					}
					else {
						action.update(game);
						score += game.getScore(game.getTurn());
						damagedUnit.setHP(damagedUnit.getHP() + dmg);
					}
				}
			}
			((Attack)action).getUnit().setAttack(false);
		}
		else if (action instanceof SelectUnit) {
			Position pos = ((SelectUnit)action).getPos();
			Unit selectedUnit = game.getUnit(pos);
			action.update(game);
			
			score += game.getScore(game.getTurn());
			selectedUnit.setSelected(false);
			game.getTurn().SelectNum(game.getTurn().getSelectNum() + 1);
		}
		else if (action instanceof MoveUnit) {
			Position pos = ((MoveUnit)action).getUnit().getPosition();
			action.update(game);
			score += game.getScore(game.getTurn());
			((MoveUnit)action).getUnit().setPosition(pos);
			((MoveUnit)action).getUnit().setMoveP(((MoveUnit)action).getUnit().getMoveP() + 1);
		}
		else if (action instanceof BuildNewUnit) {
			LinkedList<Unit> oldUnitList = new LinkedList<Unit>();
			oldUnitList.addAll(game.getUnitList());
			action.update(game);
			score += game.getScore(game.getTurn());
			game.setUnitList(oldUnitList);
			((BuildNewUnit)action).getPlayer().getBase().setAttack(false);
			((BuildNewUnit)action).getPlayer().setTotalMoney(((BuildNewUnit)action).getPlayer().getTotalMoney() 
						+ ((BuildNewUnit)action).getUnit().getCost());
		}
		else if (action instanceof ChangeTurnAction){
			score = Double.NEGATIVE_INFINITY;
		}
		return score;
	}
}
