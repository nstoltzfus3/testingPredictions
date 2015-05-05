package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Position;

public class Base extends Unit{
	private double baseHealthBar = 100;
	
	public Base(Player player) {
		this(new Position(1,1), 0, 100, 0, 0, player, false);
	}
	
	public Base(Position position, int cost, int HP, int dmg, int moveP, Player owner, boolean selected) {
		super(position, cost, HP, dmg, moveP, owner, selected);
	}

	public String getName() {
		return "Base " + this.getOwner();
	}
	
	public String toString() {
		return "B" +this.getOwner().getPlayerNumber();
	}
	
	public int getMaxHP() {
		return 100;
	}
}
