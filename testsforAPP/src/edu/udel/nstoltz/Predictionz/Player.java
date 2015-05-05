package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Game;

public class Player {
	private int playerNumber;
	private int income;
	private int totalMoney;
	private boolean myTurn;
	private Base base;
	private int selectNum;
	private int deadUnits;
	private int liveUnits;
	private boolean amIAlive = true;
	private boolean queuedActions;

	public Player(int playerNumber) {
		this(playerNumber, 85, 0, 0, 0);
	}
	
	public Player(int player, int income, int totalMoney, int deadUnits, int liveUnits) {
		this.playerNumber = player;
		this.income = income;
		this.totalMoney = totalMoney;
		this.deadUnits = deadUnits;
		this.liveUnits = liveUnits;
		this.base = new Base(this);
	}
	
	public boolean amIAlive() {
		return amIAlive;
	}
	
	public int getLiveUnits() {
		return this.liveUnits;
	}
 	
	public void setLiveUnits(int liveUnits) {
		this.liveUnits = liveUnits;
	}
	
	public int getDeadUnits() {
		return this.deadUnits;
	}
	
	public void setDeadUnits(int deadUnits) {
		this.deadUnits = deadUnits;
	}
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public int getIncome() {
		return income;
	}

	public int getTotalMoney() {
		return totalMoney;
	}
	public Base getBase() {
		return base;
	}
	
	public void setBase(Base base) {
		this.base = base;
	}
	
	public boolean isMyTurn() {
		return myTurn;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public void addIncome(PredictionGame game) {
		this.setTotalMoney(this.getTotalMoney() + this.getIncome());
	}
	
	public int getSelectNum() {
		return this.selectNum;
	}
	
	public void SelectNum(int a) {
		this.selectNum = a;
	}
	
	public void killMeIfImDead(PredictionGame game) {
		if (!game.getUnitList().contains(this.base)) {
			this.amIAlive = false;
			System.out.println("IM DEAD NOWWWW");
		}
	}
	
	public String toString() {
		return "Player" +this.playerNumber;
	}
}
