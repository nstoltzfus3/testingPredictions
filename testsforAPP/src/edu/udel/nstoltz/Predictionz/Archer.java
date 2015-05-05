package edu.udel.nstoltz.Predictionz;

import java.util.ArrayList;

import edu.udel.jatlas.gameframework.Position;

public class Archer extends Unit{
	
	
	public Archer(Player player) {
		this(new Position(5,5), player);
	}
	
	public Archer(Position position, Player player) {
		this(position, 100, 30, 25, 3, player, false);
	}
	
	public Archer(Position position, int cost, int HP, int dmg, int moveP, Player player, boolean selected) {
		super(position, cost, HP, dmg, moveP, player, selected);
	}

	public String getName () {
		return "Archer";
	}
	
	public String toString() {
		return "A" + this.getOwner().getPlayerNumber();
	}
	
	public int getMaxMovePoints() {
		return 3;
	}
	
	public ArrayList<Position> getAttackArea(char direction) {
		this.setDirection(direction);
		int y = this.getPosition().getRow();
		int x = this.getPosition().getColumn();
		ArrayList<Position> attackArea = new ArrayList<Position>();
		if (direction == DIRECTION_UP) {
			for (int i = y - 2; i >= y - 3; i--) {
				attackArea.add(new Position(x, i));
			}
		}
		else if (direction == DIRECTION_DOWN){
			for (int i = y + 2; i <= y + 3; i++) {
				attackArea.add(new Position(x, i));
			}
		}
		else if (direction == DIRECTION_RIGHT) {
			for (int i = x + 2; i <= x + 3; i++) {
				attackArea.add(new Position(i, y));
			}
		}
		else if (direction == DIRECTION_LEFT){
			for (int i = x - 2; i >= x - 3; i--) {
				attackArea.add(new Position(i, y));
			}
		}
//		for (Position e : attackArea) {
//			System.out.println(e);
//		}
		return attackArea;
	}
	
	public int getMaxHP() {
		return 30;
	}
}
