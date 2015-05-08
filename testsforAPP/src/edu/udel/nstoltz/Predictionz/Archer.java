package edu.udel.nstoltz.Predictionz;

import java.util.ArrayList;

import edu.udel.jatlas.gameframework.Position;

/**
 * Ranged Unit - Current stats:
 * Cost:	100
 * Health:	30
 * Damage:	25
 * Move:	3
 * AOE:		cardinal directions 2-3 spaces away in an area.
*/
public class Archer extends Unit{
	
	/**
	 * Different constructors for convenience. Arbitrary placement of 5/5 for random archer testing.
	 */
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
	
	/**
	 * Will return A1,A2,A3. Also the symbols used in the textual representation of the game.
	 */
	public String toString() {
		return "A" + this.getOwner().getPlayerNumber();
	}
	
	/**
	 * Used to reset the archers move points at the end of turn to maximum
	 * (Other implementations for icing or slowing effects can be added in by setting movePoints to Max/2 or Max/3 rounded.)
	 */
	public int getMaxMovePoints() {
		return 3;
	}
	
	
	/**
	 * Params:
	 * char direction (n,s,e,w)
	 * Returns the area of space that the archer hits at, 
	 * currently an area that is 2 and 3 spaces away from the archer in line with the direction.
	 * 
	 * Example: Archer shoots 'e', DIRECTION_RIGHT
	 * _______________
	 * A1..)))).......
	 */
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
//Used for testing purposes:
//		for (Position e : attackArea) {
//			System.out.println(e);
//		}
		return attackArea;
	}
	
	public int getMaxHP() {
		return 30;
	}
}
