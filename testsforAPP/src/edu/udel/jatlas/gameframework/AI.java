package edu.udel.jatlas.gameframework;

import java.util.ArrayList;
import java.util.List;

public abstract class AI<G extends Game> implements GameListener<G>, Tickable {
    private G game;
    private String identifier;
    private long turnTimeLength;
    private Ticker ticker;
    
    /**
     * Default constructor for an AI. Will make the AI turn-based using
     * a 1000 millisecond timer for games that are not themselves Tickable.
     * For games that are Tickable, it makes the AI not turn based (i.e.
     * the AI will perform an Action once per each tick of the game).
     * 
     * @param game
     * @param identifier
     */
    public AI(G game, String identifier) {
        this(game, identifier, (game instanceof Tickable) ? 0 : 1000);
    }
    
    public AI(G game, String identifier, long turnTimeLength) {
        this.game = game;
        this.identifier = identifier;
        this.turnTimeLength = turnTimeLength;
    }
    
    protected G getGame() {
        return game;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public String toString() {
        return getIdentifier();
    }
    /**
     * Convenience method, a game is turn based if the AI
     * has been told to wait 0 milliseconds each turn.
     * 
     * Overriding this to return true allows for a turn
     * based game to complete "instantly".
     */
    protected boolean isTurnBased() {
        return turnTimeLength > 0;
    }
    
    /**
     * Ends the ticker if there is one.
     */
    public void onEndEvent(G game) {
        if (ticker != null) {
            ticker.end();
        }
    }
    
    /**
     * Subclass may override this, but AI does not need to
     * handle this event
     */
    public void onEvent(String event, G game) {
    }
    
    /**
     * In turn based games, the AI will do the following:
     * 1. Check if it is "my" turn
     * 2. If so, use the Ticker to wait turnTimeLength
     * 3. Perform the best action when the Ticker tells the AI to tick
     */
    protected void takeTurnIfMyTurn() {
        if (isTurnBased() && isMyTurn()) {
            if (ticker == null) {
                ticker = Ticker.start(this);
            }
            else {
                ticker.restart();
            }
        } 
    }
    /**
     * At the start of the game the AI will try to take a turn
     */
    public void onStartEvent(G game) {
        takeTurnIfMyTurn();
    }
    /**
     * Every time some action is performed on the game, the AI
     * will try to take a turn
     */
    public void onPerformActionEvent(Action<G> action, G game) {
        takeTurnIfMyTurn();
    }
    /**
     * Subclasses should override if they are multi-player games.
     */
    protected boolean isMyTurn() {
        // for one player games this is fine, it is always our turn
        return true;
    }
    /**
     * If a game is turn-based this is the time that the AI will
     * wait after an action to take its own turn. If a subclass
     * has a different time they should call the constructor that
     * takes the property. The only real use for overriding this
     * method would be if the amount of time should be variable
     * -- i.e. sometimes the AI waits shorter or longer.
     */
    public long getRealTimeTickLength() {
        return turnTimeLength;
    }
    public boolean tick() {
        performBestAction();
        return false;
    }
    public boolean isEnd() {
        return game.isEnd();
    }
    
    /**
     * In turn based games, does nothing.
     * In tick based games, the AI will try to find its best action
     * to perform this tick and perform that action on the game.
     */
    public void onTickEvent(G game) {
        if (!isTurnBased()) {
            performBestAction();
        }
    }
    
    protected void performBestAction() {
        Action<G> action = getBestAction(game);
        // in a turn based game the AI might have no best action (since
        //  no actions would be valid when it is not its turn)
        if (action != null) {
            game.perform(action);
        }
    }

    /**
     * You will complete this method as part of DL2.
     * 
     * @param game
     * @return
     */
    public Action<G> getBestAction(G game) {        
        List<Action<G>> validActions = getAllValidActions(game);
        List<Action<G>> possibleActions = new ArrayList<Action<G>>();
        
        double max_score = Double.NEGATIVE_INFINITY;
        for (Action<G> action : validActions) {
            double score = getHeuristicScore(action, game);
            if (score > max_score) {
                max_score = score;
                possibleActions.clear();
            }
            if (score >= max_score) {
                possibleActions.add(action);
            }
        }
        
        if (possibleActions.isEmpty()) {
            return null;
        }
        else {
            return possibleActions.get((int)(Math.random()*possibleActions.size()));
        }
    }
    
    /**
     * Get all possible valid actions for the game.
     * 
     * @param game
     * @return
     */
    public abstract List<Action<G>> getAllValidActions(G game);

    /**
     * AI subclasses need to implement a heuristic that gives a larger
     * number for "better" actions given the game state.
     */
    public abstract double getHeuristicScore(Action<G> action, G game);
}
