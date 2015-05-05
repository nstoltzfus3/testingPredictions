//package edu.udel.nstoltz.Predictionz;
//
//import java.util.Set;
//
//import edu.udel.jatlas.gameframework.Action;
//import edu.udel.jatlas.gameframework.Position;
//
//import android.graphics.Point;
//import android.view.MotionEvent;
//import android.view.View;
//
//public class PredictionGameHuman implements View.OnTouchListener {
//    private PredictionGameActivity activity;
//    
//
//    private Position touchDown;
//    
//
//    private Position touchUp;
//    
//    Point touchDownP = null;
//    Point touchUpP = null;
//
//
//    private Set<Integer> humanPlayers;
//    
//    public PredictionGameHuman(PredictionGameActivity activity, 
//                             Set<Integer> humanPlayers) {
//        this.activity = activity;
//        this.humanPlayers = humanPlayers;
//    }
//
//    public boolean onTouch(View v, MotionEvent event) {
//        int action = event.getAction();
//        PredictionGame game = activity.getCurrentGame();
//        if (game != null) {
//            if (game.isEnd()) {
//                activity.restartGame();
//            }
//            else if (humanPlayers.contains(game.getTurn())) {
//
//                
//                if (action == MotionEvent.ACTION_DOWN) {
//                	touchDownP = new Point((int)((event.getX() / v.getWidth()) * game.HEIGHT_WIDTH),
//                							(int)((event.getY() / v.getHeight()) *  game.HEIGHT_WIDTH));
//                	return true;
//                }
//                else if (action == MotionEvent.ACTION_UP) {
//                	touchUpP = new Point((int)((event.getX() / v.getWidth()) * game.HEIGHT_WIDTH),
//                						(int)((event.getY() / v.getHeight()) * game.HEIGHT_WIDTH));
//                	System.out.println(touchDownP);
//                	System.out.println(touchUpP);
//                	System.out.println("Touch down unit: " + game.getUnit(getPosFromPoint(touchDownP)));
//                	System.out.println("Touch up unit: " +game.getUnit(getPosFromPoint(touchUpP)));
//                	
//                    if (game.getUnit(getPosFromPoint(touchDownP)) != null) {
//                    	Unit downUnit = game.getUnit(getPosFromPoint(touchDownP));
//	                	if (touchUpP.equals(touchDownP)) {
//	                		game.perform(new SelectUnit(getPosFromPoint(touchDownP)));
//	                		System.out.println("A unit was selected");
//	                	}
//	                	else if (game.getUnit(getPosFromPoint(touchDownP)) instanceof Base ) {
//	                		System.out.println("were checking for a base");
//	                		Character direction = null;
//	                		if (touchDownP.x < touchUpP.x && touchDownP.y == touchUpP.y) {
//	                			direction = 'e';
//	                		} 
//	                		else if (touchDownP.x > touchUpP.x && touchDownP.y == touchUpP.y) {
//	                			direction = 'w';
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y > touchUpP.y) {
//	                			direction = 'n';
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y < touchUpP.y) {
//	                			direction = 's';
//	                		}
//	                		
//	                		if (activity.getBuildFighter()) {
//	                			game.perform(new BuildNewUnit(game.getTurn(), direction, new Fighter(game.getTurn())));
//	                		}
//	                		else {
//	                			game.perform(new BuildNewUnit(game.getTurn(), direction, new Archer(game.getTurn())));
//	                		}
//	                	}
//	                	else if (!(downUnit instanceof Base) && game.getUnit(getPosFromPoint(touchUpP)) == null){
//	                		System.out.println("were trying to move");
//	                		if (touchDownP.x < touchUpP.x && touchDownP.y == touchUpP.y) {
//	                    		game.perform(new MoveUnit('e',downUnit));
//	                		}
//	                		else if (touchDownP.x > touchUpP.x && touchDownP.y == touchUpP.y) {
//	                    		game.perform(new MoveUnit('w',downUnit));
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y > touchUpP.y) {
//	                    		game.perform(new MoveUnit('n',downUnit));
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y < touchUpP.y) {
//	                    		game.perform(new MoveUnit('s',downUnit));
//	                		}
//	                	}
//	                	else if (!(downUnit instanceof Base) &&
//	                			game.getUnit(getPosFromPoint(touchUpP)) != null) {
//	                		if (touchDownP.x < touchUpP.x && touchDownP.y == touchUpP.y) {
//	                    		game.perform(new Attack('e',game.getUnit(getPosFromPoint(touchDownP))));
//	                		}
//	                		else if (touchDownP.x > touchUpP.x && touchDownP.y == touchUpP.y) {
//	                    		game.perform(new Attack('w',game.getUnit(getPosFromPoint(touchDownP))));
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y > touchUpP.y) {
//	                    		game.perform(new Attack('n',game.getUnit(getPosFromPoint(touchDownP))));
//	                		}
//	                		else if (touchDownP.x == touchUpP.x && touchDownP.y < touchUpP.y) {
//	                    		game.perform(new Attack('s',game.getUnit(getPosFromPoint(touchDownP))));
//	                		}
//	                	}
//                    }
//                	return true;
//                }
//                }
//            }
//                
//            
//        return false;
//    }
//      public static Position getPosFromPoint(Point point) {
//    	  return new Position(point.x, point.y);
//      }
//    
//}
