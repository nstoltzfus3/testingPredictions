package edu.udel.nstoltz.Predictionz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import edu.udel.jatlas.gameframework.ConsoleListener;
import edu.udel.jatlas.gameframework.Game;
import edu.udel.jatlas.gameframework.Position;
import edu.udel.jatlas.gameframework.Tickable;

public class PredictionGame extends Game{
	public static final int HEIGHT_WIDTH = 9;
	public static final int MAX_DEATHS = 5;
	public static final int MIN_BASE_HEALTH = 0;
	
	private int rows;
	private int cols;
	private LinkedList<Unit> units;
	private LinkedList<Player> players;
	private Player turn;//whoever is first in the player list.
    private int notTurn;
	
	public PredictionGame(LinkedList<Unit> units, LinkedList<Player> players, int rows, int cols, Player turn) {  
		this.units = units;
		this.rows = rows;
		this.cols = cols;
		this.players = players;
		this.turn = turn;
	}
	
	public LinkedList<Unit> getUnitList() {
		return units;
	}
	
	public void setUnitList(LinkedList<Unit> units) {
		this.units = units;
	}
	
	public LinkedList<Player> getPlayerList() {
		return players;
	}
	
	public void setPlayerList(LinkedList<Player> players) {
		this.players = players;
	}
	
	public int getPlayerDeadUnits(int playerNumber) {
		int playersDeadUnits = 10;
		for (Player e : players) {
			if (e.getPlayerNumber() == playerNumber) {
				playersDeadUnits = e.getDeadUnits();
			}
		}
		return playersDeadUnits;
	}
	
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	
	public Player getTurn() {
        return players.get(0);
    }
	
	public void setTurn(Player a) {
		this.turn = a;
	}

    public int getNotTurn() {
        return notTurn;
    }

    public void setNotTurn(int a) {
    	this.notTurn = a;
    }
    
    
    public void changeTurn() {
		int countUnits1 = 0;
		int countUnits2 = 0;
    	for (Unit e : this.getUnitList()) {
    		e.setMoveP(e.getMaxMovePoints());
    		e.setAttack(false);
    		e.setSelected(false);
    		
    	}
    	
    	for (Player e: this.players) {
    		e.setTotalMoney(e.getTotalMoney() + e.getIncome());
    		e.SelectNum((int)((e.getLiveUnits() - 4)/2 > 0?(e.getLiveUnits() - 4)/2:0) + 2);
    		System.out.println("Player: " + e.toString() + " is in position 1. ");
    	}
		
    	players.addLast(players.getFirst()); //cycles the turns
    	players.removeFirst();
    }

    public Player getPlayer(int playerNumber) {
    	for (Player e : players) {
    		if (playerNumber == e.getPlayerNumber()) {
    			return e;
    		}
    	}
		return null;
    }
    

	public Unit getUnit(Position pos) {
    	for (Unit e : units) {
    		if (pos.getColumn() == e.getPosition().getColumn() && pos.getRow() == e.getPosition().getRow()) {
    			return e;
    		}
    	}
		return null;
    }
	
	public String toString() {
		System.out.println("---------------------");
		String[][] grid = new String[rows][cols];
		
		for (String[] row : grid) {
            Arrays.fill(row, "  ");
        }
		
		//places a unit
		for (Unit unit : units ) {
			grid[unit.getPosition().getRow()][unit.getPosition().getColumn()] = unit.toString();
		}
		
		
		StringBuilder buffer = new StringBuilder();
		buffer.append(getStatus());
		for (String[] row : grid) {
			for (String s : row) {
		        buffer.append(s);
			}
            buffer.append('\n'); // new line
        }
        return buffer.toString();
		
	}
	
	public void setUnit(Position pos, Unit unit) {
		unit.setPosition(pos);
		units.add(unit);
	}
	
	@Override
	public boolean isEnd() {
		int numberOfLivingPlayers = 0;
		for (Player e : players) {
			if (e.amIAlive() == true) {
				numberOfLivingPlayers ++;
			}
		}
		if (numberOfLivingPlayers >= 2) {
			return false;
		}
		else {
			return true;
		}		
	}
	
	public boolean isWinner(Player player) {
		if (player.amIAlive() == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String getStatus() {
		String string = "";
		for (Player e : players) {
			System.out.println("Player "+e.getPlayerNumber()+" Base health: "+e.getBase().getHP()+"\tPlayer  Kills: "+e.getDeadUnits() + "\tPlayer 1 Funds: " +e.getTotalMoney());
		}
        if (isEnd()) {
            if (isWinner(this.getPlayerList().get(0))) {
                return string + "Player " + this.getTurn().getPlayerNumber() + " wins!\n";
            }
            else {
            	
                return string + "It is a draw.\n";
            }
        }
        else {
            return turn + "'s turn:\n";
        }
    }
	
	public String fullStatus() {
		String playerInfo = "";
		for (Player i : players) {
			playerInfo = playerInfo.concat("\nPlayer "+i.getPlayerNumber()+": Base Health = " + i.getBase().getHP()+ 
					"\n\tDead Units: "+i.getDeadUnits() + 
					"\n\tSelections Left: " +i.getSelectNum()+
					"\n\tHas already Built: "+i.getBase().getAttack()+
					"\n\tCurrent Funds: " + i.getTotalMoney() +
					"\n");
			for (Unit e : this.units) {
				if (e.getOwner() == i) {
					playerInfo= playerInfo.concat("Unit " +e.getName()+ " at " + e.getPosition().toString() + "\n" );
					playerInfo = playerInfo.concat("\tUnit stats:\n\tOwner: " +e.getOwner()+ 
														"\n\t\tHP: " +e.getHP()+
														"\n\t\tMovePoints: "+e.getMoveP() +
														"\n\t\tisSelected: " +e.getSelected() +
														"\n\t\tHas attacked yet: " +e.getAttack() +"\n");
				}
			
			}
		}
		return playerInfo;
	}
	
    public boolean isWithinBounds(int row, int column) {
        return row >= 0 && column >= 0 && 
                row < HEIGHT_WIDTH && 
                column < HEIGHT_WIDTH;
    }
	
	public static PredictionGame makeDefaultPredictionGame() {
		Player player1 = new Player(1, 48, 90, 0, 1);
		Player player2 = new Player(2, 63, 110, 0, 1);	
		Base base1 = new Base(player1);
		Base base2 = new Base(player2);
		base2.setPosition(new Position(7,7));
		player1.SelectNum(2);
		player2.SelectNum(2);
		player1.setBase(base1);
		player2.setBase(base2);
		LinkedList<Unit> unitlist = new LinkedList<Unit>(); 
		unitlist.add(base1);
		unitlist.add(base2);
		
		LinkedList<Player> players = new LinkedList<Player>(); 
		players.add(player1);
		players.add(player2);
		return new PredictionGame(unitlist, players, 9, 9, players.get(0));
	}
	
	
	public boolean isEmpty(Position pos) {
		for (Unit e : units) {
			if (e.getPosition().equals(pos)) {
				return false;
			}
		}
		return true;
	}
	
	public void selectAll() {
		for (Unit e :getUnitList()) {
			e.setSelected(true);
		}
	}
	
	public Player[] getEnemies(Player whoIam) {
		Player[] enemies = new Player[this.players.size() - 1]; 
		for (int i = 1; i < enemies.length + 1; i ++) {
			enemies[i - 1] = players.get(i);
		}
		return enemies;
	}
	
	public int getScore(Player player) {
		//generate a random number based on the number of enemies there are
		Player[] enemies = this.getEnemies(player);
		int randomNum = (int)(Math.random()*enemies.length);
		int score = 1000;
		for (Unit e : this.units) {
			if (e.getOwner() == player){
				score += (e.getSelected())? 1:0;
				int distance = e.getPosition().blockDistance(enemies[randomNum].getBase().getPosition());
				score += 19 - distance; //the closer they get to a random Enemy's base the better
			}
			else {
				score -= e.getHP()*2; //you get subtracted score for enemy health points, so, if you attack, it gives you score
				if (e instanceof Base) {
					score -= e.getHP()*2; //the base is worth double
				}
			}
		}
		return score;
	}
	
	public PredictionGame saveGameState() {
		
		LinkedList<Player> newPlayerList = new LinkedList<Player>();
		for (Player e : this.players) {
			Player newP = new Player(e.getPlayerNumber(), e.getIncome(), e.getTotalMoney(), e.getDeadUnits(), e.getLiveUnits());
			newP.setBase(e.getBase());
			
			newPlayerList.add(newP);
		}
		LinkedList<Unit> newUnitList = new LinkedList<Unit>();
		for (Unit e : this.units) {
			if (e instanceof Base) {
				Unit newU = new Base(e.getPosition(), e.getCost(), e.getHP(), e.getDmg(), e.getMoveP(), e.getOwner(), e.getSelected());
				newU.setMarkedForQueue(e.getMarkedForQueue());
				newUnitList.add(newU);
			}
			if (e instanceof Fighter) {
				Unit newU = new Fighter(e.getPosition(), e.getCost(), e.getHP(), e.getDmg(), e.getMoveP(), e.getOwner(), e.getSelected());
				newU.setMarkedForQueue(e.getMarkedForQueue());
				newUnitList.add(newU);
			}
			if (e instanceof Archer) {
				Unit newU = new Archer(e.getPosition(), e.getCost(), e.getHP(), e.getDmg(), e.getMoveP(), e.getOwner(), e.getSelected());
				newU.setMarkedForQueue(e.getMarkedForQueue());
				newUnitList.add(newU);
			}
		}
		PredictionGame savedGame = new PredictionGame(newUnitList, newPlayerList, this.getRows(), this.getCols(), newPlayerList.get(0));
		return savedGame;
	}
	
	public boolean isequals(PredictionGame game1) {
		//check if the player and unit lists are equal length before checking individual units.
		if (this.players.size() != game1.players.size()) {
			System.out.println("###Player Length Incorrect###");
			return false;
		}
		else if (this.units.size() != game1.units.size()) {
			System.out.println("###Unit Length Incorrect###");
			return false;
		}
		else {
			for (int i = 0; i < game1.players.size(); i++) {
				Player p1 = game1.players.get(i);
				Player p2 = this.players.get(i);
				if 	(	p1.amIAlive() != p2.amIAlive() ||
						p1.isMyTurn() != p2.isMyTurn() ||
						p1.getBase().checkIfImDead() 	!= p2.getBase().checkIfImDead() 	||
						p1.getBase().getAttack() 		!= p2.getBase().getAttack()			||
						p1.getBase().getHP()			!= p2.getBase().getHP()				||
						p1.getBase().getMaxHP()			!= p2.getBase().getMaxHP()			||
						p1.getBase().getPosition()		!= p2.getBase().getPosition()		||
						p1.getBase().getMaxMovePoints()	!= p2.getBase().getMaxMovePoints()	||
						p1.getBase().getSelected()		!= p2.getBase().getSelected()		||
						p1.getBase().getName()			!= p2.getBase().getName()
					) 
				{
					System.out.println("###Unit Similarity incorrect###");
					return false;
				}
				
			}
			for (int j = 0; j < game1.players.size(); j ++) {
				if (this.units.get(j) != game1.units.get(j)) {
					return false;
				}
			}
			
		}
				
		return true;
		
	}
	
	public static void main(String[] args) {
        PredictionGame game = makeDefaultPredictionGame();
        game.addGameListener(new ConsoleListener());
        game.addGameListener(new PredictionGameAI(game, "Player1", 100));
        game.addGameListener(new PredictionGameAI(game, "Player2", 100));
        game.start();
	}
	
}

