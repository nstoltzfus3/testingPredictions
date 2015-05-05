package edu.udel.jatlas.gameframework;

/**
 * Tickable objects respond to discrete tick events.
 * Each method will be called once per discrete event.
 * The general order will be:
 * 
 * 1. queueNext = tick()
 * 2. waitTime = getRealTimeTickLength()
 * 3. if isEnd() stop, otherwise if queueNext then
 *    wait for waitTime and repeat starting at step 1
 * (4. if not queueNext then the Ticker will need
 *     to be restarted manually)
 *    
 * Because different platforms may not support the
 * same threading pattern for execution, we place
 * the implementation of the "game loop" that calls
 * Tickable in its own class that will be platform
 * specific.  For example, see {@code Ticker}
 * for a basic version using Java's Timer.
 * We will use an Android specific version later. 
 */
public interface Tickable {
    /**
     * Called to allow the Tickable to perform update
     * logic on each discrete event tick
     */
    public boolean tick();
    
    /**
     * Gives the real amount of time that should elapse
     * between calls to tick.  Returning a value of 0
     * will essentially schedule the next call to tick
     * to be as soon as possible.
     */
    public long getRealTimeTickLength();
    
    /**
     * Return true when the Tickable object has reached
     * its end state. No call to tick will be made after
     * isEnd returns true.
     */
    public boolean isEnd();
}
