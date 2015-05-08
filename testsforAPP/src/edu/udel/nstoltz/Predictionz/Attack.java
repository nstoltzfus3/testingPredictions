package edu.udel.nstoltz.Predictionz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Position;

public class Attack implements Action<PredictionGame>{
	private char direction;
	private Unit unit;
	private ArrayList<Position> damageArea;
	private ArrayList<Unit> damagedUnits;
	
	/**
	 * Constructor for an Attack Action Object.
	 */
	public Attack(char direction, Unit unit) {
		this.damageArea = unit.getAttackArea(direction);
		this.direction = direction;
		this.unit = unit;
	}
	
	
	public char getDirection() {
		return direction;
	}



	public ArrayList<Unit> getDamagedUnits() {
		return damagedUnits;
	}


	public void setDamagedUnits(ArrayList<Unit> damagedUnits) {
		this.damagedUnits = damagedUnits;
	}


	public Unit getUnit() {
		return unit;
	}



	public ArrayList<Position> getDamageArea() {
		return damageArea;
	}



	public void setDirection(char direction) {
		this.direction = direction;
	}



	public void setUnit(Unit unit) {
		this.unit = unit;
	}



	public void setDamageArea(ArrayList<Position> damageArea) {
		this.damageArea = damageArea;
	}


	/**
	 * Determines whether an implementation of this action is valid or not.
	 * Overwrites the abstract class Action's implementation of isValid 
	 */
	@Override
	public boolean isValid(PredictionGame game) {
		if (unit.getAttack()) {
			System.out.println("already attacked");
			return false;
		}
		if (!unit.getSelected()) {
			System.out.println("not selected - cant attack");
			return false;
		}
		if (unit.getOwner() != game.getTurn()) {
			System.out.println("not my turn - cant attack");
			return false;
		}
		return true;

	}

	/**
	 * Carries out the actual damaging to the units within the damage Area. and clearing out of dead Units off the map.
	 * Still some minor problems with the AI I think but no worries.
	 */
	@Override
	public void update(PredictionGame game) {
		unit.setDirection(direction);
		LinkedList<Unit> units = game.getUnitList();
		for (Unit e : units) {
			for (Position pos : damageArea) {
				if (game.isWithinBounds(pos.getRow(), pos.getColumn())) {
					if (pos.getColumn() == e.getPosition().getColumn() && pos.getRow() == e.getPosition().getRow()) {
						e.setHP(e.getHP() - unit.getDmg());
					}
				}
			}
		}
		Iterator i = game.getUnitList().iterator();
		while (i.hasNext()) {
			Unit e =((Unit)i.next());
			if (e.checkIfImDead()) {
				e.die(game);
				i.remove();
			}
		}
		for (Player p: game.getPlayerList()) {
			p.killMeIfImDead(game);
		}
		unit.setAttack(true);
	}
	
	public String toString() {
		unit.setDirection(direction);
		return "Unit "+unit+" on "+unit.getPosition()+" has damaged " + direction;
	}
	
	
	
}
