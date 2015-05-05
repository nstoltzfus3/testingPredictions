package edu.udel.jatlas.gameframework;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A basic implementation of a Ticker using Java's Timer.
 * The only way to use this class is to pass a Tickable
 * object directly to the Ticker.start static method.
 * 
 * Later we will have a different version that uses Android's
 * Handler class.
 * 
 * @author jatlas
 */
public class Ticker  {
    Tickable tickable;
    Timer timer;
    boolean ended;
    boolean queued;
    
    class Task extends TimerTask {
        public void run() {
            queued = false;
            boolean queueNext = tickable.tick();
            if (tickable.isEnd()) {
                end();
            }
            else if (queueNext) {
                nextTask();
            }
        }
    }
    
    private Ticker(Tickable tickable, Timer timer) {
        this.tickable = tickable;
        this.timer = timer;
    }
    
    public void end() {
        if (!ended) {
            timer.cancel();
        }
        ended = true;
    }
    
    private void nextTask() {
        if (!queued) {
            timer.schedule(new Task(), tickable.getRealTimeTickLength());
        }
    }
    
    public void restart() {
        if (!ended) {
            nextTask();
        }
    }
    
    /**
     * This method must be called to start the ticker.
     * This task ticker follows a similar event driven programming 
     * pattern to the way we will run our time based logic in Android. 
     * 
     * @param tickable
     */
    public static final synchronized Ticker start(Tickable tickable) {
        Ticker ticker = new Ticker(tickable, new Timer());
        ticker.nextTask();
        return ticker;
    }
}
