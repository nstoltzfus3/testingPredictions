package edu.udel.nstoltz.Predictionz;

import java.util.ArrayList;

import edu.udel.jatlas.gameframework.Position;



public class Unit {
	public static final char DIRECTION_UP = 'n';
	public static final char DIRECTION_RIGHT = 'e';
	public static final char DIRECTION_DOWN = 's';
	public static final char DIRECTION_LEFT = 'w';
	
	private int direction;
	private Position position;
	private int cost;
	private int HP;
	private int dmg;
	private int moveP;
	private Player owner;
	private Position[] attackArea;
	private boolean selected;
	private boolean attacked;
	private boolean markedForQueue;
	
	public Unit(Position position, int cost, int HP, int dmg, int moveP, Player owner, boolean selected) {
		this.position = position;
		this.cost = cost;
		this.dmg = dmg;
		this.HP = HP;
		this.moveP = moveP;
		this.owner = owner;
		this.selected = selected;
		this.attacked = false;
	}
	
	public boolean getMarkedForQueue() {
		return this.markedForQueue;
	}
	
	public void setMarkedForQueue(boolean marked) {
		this.markedForQueue = marked;
	}

	public int getDirection() {
		return direction;
	}


	public Position getPosition() {
		return position;
	}


	public int getCost() {
		return cost;
	}


	public int getHP() {
		return HP;
	}


	public int getDmg() {
		return dmg;
	}


	public int getMoveP() {
		return moveP;
	}
	
	public Player getOwner() {
		return owner;
	}


	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}


	public void setPosition(Position position) {
		this.position = position;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public void setHP(int HP) {
		this.HP = HP;
	}


	public void setDmg(int dmg) {
		this.dmg = dmg;
	}


	public void setMoveP(int moveP) {
		this.moveP = moveP;
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean a) {
		this.selected = a;
	}
	
	public Position getNextPosition() {
        Position currentPosition = this.getPosition();
        Position next = currentPosition;
        
        if (direction == DIRECTION_UP) {
            next = new Position(currentPosition.getColumn(), currentPosition.getRow() - 1);
        }
        else if (direction == DIRECTION_RIGHT) {
            next = new Position(currentPosition.getColumn() + 1, currentPosition.getRow());
        }
        else if (direction == DIRECTION_DOWN) {
            next = new Position(currentPosition.getColumn(), currentPosition.getRow() + 1);
            }
        else if (direction == DIRECTION_LEFT) {
            next = new Position(currentPosition.getColumn() - 1, currentPosition.getRow());
        }
        return next;
    }
	
	public String toString() {
		return "ER";
	}
	
	public ArrayList getAttackArea(char direction) {
		this.setDirection(direction);
		Position pos = this.position;
		int row = this.getPosition().getRow();
		int column = this.getPosition().getColumn();
		ArrayList<Position> attackArea = new ArrayList<Position>();
		if (direction == DIRECTION_UP) {
			attackArea.add(new Position(column, row - 1));
		}
		else if (direction == DIRECTION_DOWN){
			attackArea.add(new Position(column, row + 1));
		}
		else if (direction == DIRECTION_RIGHT) {
			attackArea.add(new Position(column + 1, row));
		}
		else {
			attackArea.add(new Position(column - 1, row));
		}
		return attackArea;
	}
	
	public boolean checkIfImDead() {
		if (this.getHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getName () {
		return "ER";
	}
	
	public void die(PredictionGame game) {
		this.getOwner().setDeadUnits(this.getOwner().getDeadUnits() + 1);
	}
	
	public int getMaxMovePoints() {
		return 0;
	}
	
	public void setAttack(boolean a) {
		this.attacked = a;
	}
	public boolean getAttack() {
		return this.attacked;
	}
	
	public int getMaxHP() {
		return 0;
	}
	
	public double getHealthPercent() {
		return this.getHP()/this.getMaxHP();
	}
	
}

