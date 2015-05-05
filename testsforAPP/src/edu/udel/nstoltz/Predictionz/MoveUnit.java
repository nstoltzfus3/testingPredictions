package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Position;

public class MoveUnit implements Action<PredictionGame>{
	private char direction;
	private Unit unit;
	
	
	public MoveUnit(char direction, Unit unit) {
		this.direction = direction;
		this.unit = unit;
	}
	
	public char getDirection() {
		return direction;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setDirection(char direction) {
		this.direction = direction;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public boolean isValid(PredictionGame game) {
		unit.setDirection(direction);
		Position pos = unit.getNextPosition();
		if (!unit.getSelected()) {
			return false;
		}
		if (!game.isWithinBounds(pos.getRow(), pos.getColumn())){
			return false;
		}
		if (unit.getOwner() != game.getPlayerList().get(0)) {
			return false;
		}
		if (!game.isEmpty(pos)) {
			return false;
		}
		if (!(unit.getMoveP() > 0)) {
			return false;
		}
			return true;
	}

	@Override
	public void update(PredictionGame game) {
		unit.setMoveP(unit.getMoveP() - 1);
		unit.setDirection(direction);
		this.unit.setPosition(unit.getNextPosition());
	}
	
	public String toString() {
		return "Unit "+unit+" on "+unit.getPosition()+": has moved " +direction;
	}
}
