package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Position;

public class Fighter extends Unit{

	public Fighter(Player player) {
		this(new Position(5,5), player);
	}
	
	public Fighter(Position position, Player player) {
		this(position, 70, 60, 35, 4, player, false);
	}
	
	public Fighter(Position position, int cost, int HP, int dmg, int moveP, Player player, boolean selected) {
		super(position, cost, HP, dmg, moveP, player, selected);
	}

	public String getName () {
		return "Fighter";
	}
	
	public String toString() {
		return "F" + this.getOwner().getPlayerNumber();
	}
	
	public int getMaxMovePoints() {
		return 4;
	}
	
	public int getMaxHP() {
		return 60;
	}
}
