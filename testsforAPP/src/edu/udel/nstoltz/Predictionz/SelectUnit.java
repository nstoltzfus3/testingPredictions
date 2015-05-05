package edu.udel.nstoltz.Predictionz;

import java.util.LinkedList;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Position;

public class SelectUnit implements Action<PredictionGame>{
	private Position pos;
	
	
	public SelectUnit(Position position) {
		this.pos = position;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}



	@Override
	public boolean isValid(PredictionGame game) {
		LinkedList<Unit> units = game.getUnitList();
		Unit e = game.getUnit(pos);
		if ((e!=null)?e.getOwner() == game.getTurn():false){
			if (e.getSelected() == false){
				return game.getTurn().getSelectNum() > 0;
			}
		}
		return false;
	}

	@Override
	public void update(PredictionGame game) {
		Unit thatUnit = game.getUnit(pos);
		thatUnit.setSelected(true);
		LinkedList<Unit> units = game.getUnitList();
		Unit e = game.getUnit(pos);
		e.getOwner().SelectNum(e.getOwner().getSelectNum() - 1);
	}

	public String toString() {
		return "A Unit was selected on " +pos;
	}
}
