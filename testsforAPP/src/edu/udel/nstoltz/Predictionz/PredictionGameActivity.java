//package edu.udel.nstoltz.Predictionz;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import edu.udel.jatlas.gameframework.Action;
//import edu.udel.jatlas.gameframework.Game;
//import edu.udel.jatlas.gameframework.GameListener;
//
//import edu.udel.nstoltz.Predictionz.PredictionGame;
//import	android.app.Activity;	
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import	android.os.Bundle;	
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import	android.widget.TextView;	
//
//public class PredictionGameActivity extends Activity implements GameListener<PredictionGame>, OnClickListener{
//	private TextView status;
//	private PredictionGame game;
//	private PredictionGameView2D gameView;
//	private Button endTurnButton;
//	private Button buildFighterButton;
//	private	static	final	int	GAMETYPE_HUMAN_AI	=	0;
//	private	static	final	int	GAMETYPE_HUMAN_HUMAN	=	1;	
//	private	static	final	int	GAMETYPE_AI_AI	=	2;
//	
//	private	int	gameType;
//	
//	private boolean buildFighter;
//
//	public boolean getBuildFighter() {
//		return buildFighter;
//	}
//	
//	@Override
//	public void onPerformActionEvent(Action<PredictionGame> action,
//			PredictionGame game) {
//		updateViews();
//		
//	}
//
//	@Override
//	public void onTickEvent(PredictionGame game) {
//		updateViews();
//		
//	}
//
//	@Override
//	public void onStartEvent(PredictionGame game) {
//		updateViews();
//		
//	}
//
//	@Override
//	public void onEndEvent(PredictionGame game) {
//		updateViews();
//		
//	}
//
//	@Override
//	public void onEvent(String event, PredictionGame game) {
//		updateViews();
//	}
//
//	public void updateViews() {
//		status.setText(game.getStatus());
//		gameView.invalidate();
//	}
//	
//
//	
//    class ActionButton extends Button {
//    	int color;
//    	String name;
//    	public ActionButton(int color, String name) {
//    		super(PredictionGameActivity.this);
//    		this.setBackgroundColor(color);
//    		this.setText(name);
//    		this.setOnClickListener(new ColorButtonListener());
//    	}
//    }
//    
//    class ColorButtonListener implements View.OnClickListener {
//    	@Override
//    	public void onClick(View v) {
//    		if (gameType == GAMETYPE_AI_AI) {
//    		}
//    		else if (gameType == GAMETYPE_HUMAN_AI && game.getTurn().getPlayerNumber() == 1) {
//        		game.perform(new ChangeTurnAction());
//    		}
//    		else if (gameType == GAMETYPE_HUMAN_HUMAN) {
//        		game.perform(new ChangeTurnAction());
//    		}
//    	}
//    	
//    }
//    
//    class BuildFighterButton extends Button {
//    	public BuildFighterButton(int color, String name) {
//    		super(PredictionGameActivity.this);
//    		this.setBackgroundColor(color);
//    		this.setText(name);
//    		this.setOnClickListener(new BuildFighterButtonListener());
//    	}
//    }
//    
//    class BuildFighterButtonListener implements View.OnClickListener {
//    	@Override
//    	public void onClick(View v) {
//    		PredictionGameActivity.this.buildFighter = !PredictionGameActivity.this.buildFighter;
//    		if (PredictionGameActivity.this.buildFighter == true) {
//    			buildFighterButton.setBackgroundColor(Color.rgb(34,139,34));
//    			buildFighterButton.setText("Click to Build Archer: 120 funds");
//    			buildFighterButton.setTextColor(Color.GREEN);
//    		}
//    		else {
//    			buildFighterButton.setBackgroundColor(Color.rgb(132, 31, 39));
//    			buildFighterButton.setText("Click to Build Fighter: 80 funds");
//    			buildFighterButton.setTextColor(Color.BLACK);
//    		}
//
//    		
//    	}
//    }
//	
//	protected	void	onCreate(Bundle	savedInstanceState)	{	
//		super.onCreate(savedInstanceState);	
//		status	=	new	TextView(this);	
//		status.setTypeface(Typeface.MONOSPACE);
//		gameView = new PredictionGameView2D(this);
//		
////        Log.i("ImageEditor", "about to load image");
////        try {
////            gameView.loadPicture("woodenBoard.png");
////        }
////        catch (IOException e) {
////            Log.e("ImageEditor", "Unable to load picture: 'woodenBoard.png'", e);
////        }
//		
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//
//		endTurnButton = new ActionButton(Color.RED, "End Turn");
//        endTurnButton.setTextColor(Color.BLACK);
//        layout.addView(endTurnButton);
//        
//        buildFighterButton = new BuildFighterButton(Color.rgb(34,139,34), "Click to Build Archer: 100 funds");
//        endTurnButton.setTextColor(Color.GREEN);
//        layout.addView(buildFighterButton);
//        this.buildFighter = true;
//        
//        TextView titleView = new TextView(this);
//        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        titleView.setLayoutParams(lparams);
//        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
//        
//        layout.addView(status);
//        layout.addView(gameView);
//
//		startGame();
//		setContentView(layout);	
//	}
//			
//
//	
//	private	void	startGame()	{	
//		game	=	PredictionGame.makeDefaultPredictionGame();	
//		game.addGameListener(this);	
//		List<Integer>	humanPlayers	=	new	ArrayList<Integer>();	
//
//		if	(gameType	==	GAMETYPE_HUMAN_HUMAN	||	gameType	==	GAMETYPE_HUMAN_AI)	{	
//						humanPlayers.add(1);									}	
//		else	{	
//		game.addGameListener(new	PredictionGameAI(game,	"Player1",	500));	
//		}	
//		if (gameType == GAMETYPE_AI_AI || gameType == GAMETYPE_HUMAN_AI)	{	
//			game.addGameListener(new PredictionGameAI(game, "Player2", 500));	
//		}	
//		else {	
//			humanPlayers.add(2);	
//		}	
//			
//		game.start();	
//		gameView.setOnTouchListener(new	PredictionGameHuman(this,		
//						new	HashSet<Integer>(humanPlayers)));	
//	}
//	
//	public	void	restartGame()	{	
//		if	(game	!=	null	&&	game.getLifecycle()	!=	Game.ENDED)	{	
//						game.end();	
//		}	
//		startGame();	
//	}
//	
//	public PredictionGame getCurrentGame() {
//		return game;
//	}
//	
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	menu.add("Human vs Artificial Intel");
//    	menu.add("Human vs Human");
//    	menu.add("Artificial vs Artificial");
//    	menu.add("Restart Game");
//    	menu.add("Quit");
//    	return true;
//    }
//    
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	if (item.getTitle().equals("Human vs Artificial Intel")) {
//    		gameType = GAMETYPE_HUMAN_AI; 
//    		this.restartGame();
//    	}
//    	else if (item.getTitle().equals("Human vs Human")) {
//    		gameType = GAMETYPE_HUMAN_HUMAN;
//    		this.restartGame();
//
//    	}
//    	else if (item.getTitle().equals("Artificial vs Artificial")) {
//    	    gameType = GAMETYPE_AI_AI;
//    		this.restartGame();
//
//    	}
//        else if (item.getTitle().equals("Restart Game")) {
//    		this.restartGame();
//
//        }
//        else if (item.getTitle().equals("Quit")){
//        	this.finish();
//        }
//    	gameView.invalidate();
//    	
//    	return true;
//    }
//    
//    public void onClick(View v) {
//        if (v == endTurnButton) {
//        	game.perform(new ChangeTurnAction());
//        }
//    }
//    
//    private void addUIListeners() {
//        endTurnButton.setOnClickListener(this);
//    }
//}
