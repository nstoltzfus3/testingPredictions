package edu.udel.nstoltz.Predictionz;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Position;

public class BuildNewUnit implements Action<PredictionGame>{
    private Player player;
    private Unit unit;
    char direction;
    
	public BuildNewUnit(Player player, char direction, Unit unit) {
		this.player = player;
		this.unit = unit;
		this.direction = direction;
	}

	public Player getPlayer() {
		return player;
	}

	public Unit getUnit() {
		return unit;
	}

	public char getDirection() {
		return direction;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	@Override
	public boolean isValid(PredictionGame game) {
		this.player.getBase().setDirection(direction);
		Position pos = this.player.getBase().getNextPosition();

		if (game.getPlayerList().get(0).getBase().getSelected() == false || this.unit.getCost() >= game.getPlayerList().get(0).getTotalMoney()){
			System.out.println("not selected " + player.toString() + game.getPlayerList().get(0));
			return false;
		}

		if (this.player != game.getPlayerList().get(0)) {
			System.out.println("not your turn" + player.toString());
			return false;
		}
		if (this.player.getBase().getAttack()) {
			System.out.println("already placed");
			return false;
		}
		if (!game.isEmpty(pos)){
			System.out.println("its full");
			return false;
		}
		return true;
	}

	@Override
	public void update(PredictionGame game) {
		game.getUnitList().add(unit);
		this.player.getBase().setDirection(direction);
		unit.setPosition(this.player.getBase().getNextPosition());
		unit.setOwner(player);
		this.player.getBase().setAttack(true);
		this.player.setTotalMoney(this.player.getTotalMoney() - this.unit.getCost());
	}
	
    public String toString() {
		this.player.getBase().setDirection(direction);
        return "Player "+player.getPlayerNumber()+" placed a "+unit.getName()+" at "+this.player.getBase().getNextPosition();
    }
}
