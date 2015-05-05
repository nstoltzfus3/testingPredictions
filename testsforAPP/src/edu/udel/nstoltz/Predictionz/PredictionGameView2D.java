//package edu.udel.nstoltz.Predictionz;
//
//import java.io.IOException;
//
//
//import android.view.View;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.Paint.Style;
//import android.graphics.drawable.BitmapDrawable;
//import android.view.View;
//
//
//
//public class PredictionGameView2D extends View{
//    protected PredictionGameActivity activity;
//
//    private int width;
//    private int height;
//    
//    private float scale_x;
//    private float scale_y;
//    private Bitmap background;
//    private Bitmap fighter1;
//    private Bitmap fighter2;
//    private Bitmap archer1;
//    private Bitmap non_select_archer;
//    private Bitmap non_select_fighter;
//    private Bitmap castle;
//    private Bitmap archer2;
//    private Paint bitpaint;
//    private Rect setSizeRect;
//    
//    public PredictionGameView2D(PredictionGameActivity context) {
//        super(context);
//        activity = context;
//        setBackgroundColor(Color.BLACK);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        background = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020003);
//        archer1 = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020000);
//        archer2 = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020001);
//        fighter1 = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020008);
//        fighter2 = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020006);
//        non_select_fighter =  BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020007);
//        non_select_archer = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020002);
//        castle = BitmapFactory.decodeResource(activity.getResources(),
//        		0x7f020004);
//        BitmapDrawable bd = new BitmapDrawable(background);
//        this.setBackgroundDrawable(bd);
//    }
//    
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        width = w;
//        height = h;
//        
//        updateScaling();
//    }
//    
//    private void updateScaling() {
//        if (activity.getCurrentGame() != null) {
//            scale_x = (float)width / PredictionGame.HEIGHT_WIDTH;
//            scale_y = (float)height / PredictionGame.HEIGHT_WIDTH;
//        }
//    }
//    
//    protected void onDraw(Canvas canvas) {
//    	 super.onDraw(canvas);
//    	 
//         Paint Base1 = new Paint();
//         Paint Base2 = new Paint();
//
//         Paint Fighter1 = new Paint();
//         Paint Fighter2 = new Paint();
//                  
//         Base1.setStrokeWidth(10);
//         Base2.setStrokeWidth(11);
//         Fighter1.setStrokeWidth(5);
//         Fighter2.setStrokeWidth(3);
//         
//         Base1.setStyle(Style.FILL_AND_STROKE);
//         Base2.setStyle(Style.FILL_AND_STROKE);
//         Fighter1.setStyle(Style.FILL_AND_STROKE);
//         Fighter2.setStyle(Style.FILL_AND_STROKE);
//         
//         Base1.setColor(Color.GREEN);
//         Base2.setColor(Color.BLUE);
//         Fighter1.setColor(Color.GREEN);
//         Fighter2.setColor(Color.BLUE);
//         
//         PredictionGame game = activity.getCurrentGame();
//         
//         
//         
//         for (Unit e : game.getUnitList()) {
//        	 int x = e.getPosition().getColumn();
//        	 int y = e.getPosition().getRow();
//        	 setSizeRect = new Rect((int)(x*scale_x + 5), (int)(y*scale_y + 10), (int)(x*scale_x + scale_x - 5), (int)(y*scale_y + scale_y - 5));
//        	 if (e instanceof Base) {
//        		Paint thisPaint = new Paint();
//        		if (e.getOwner() == 1) {
//        			thisPaint = Base1;
//        		}
//        		else if (e.getOwner() == 2) {
//        			thisPaint = Base2;
//        		}
//        		canvas.drawBitmap(castle, null, setSizeRect, thisPaint);
//        	 }
//        	 if (e instanceof Unit) {
//        		 if (e.getOwner() == 1){
//        			 canvas.drawCircle(x*scale_x + 20, y*scale_y + 20,(float)((15)*e.getHP()/e.getMaxHP()), Fighter1);
//        		 }
//        		 else if (e.getOwner() == 2) {
//            		 canvas.drawCircle(x*scale_x + 20, y*scale_y + 20, (float)((15)*e.getHP()/e.getMaxHP()), Fighter2);
//        		 }
//
//            	 if (e instanceof Fighter) {
//	         		 if (e.getOwner() == 1){
//	            		 if (e.getSelected() == true) {
//	                		 canvas.drawBitmap(fighter1, null, setSizeRect, bitpaint);
//	            		 }
//	            		 else {
//	            			 canvas.drawBitmap(non_select_fighter, null, setSizeRect, bitpaint);
//	            		 }
//	        		 }
//	        		 else if (e.getOwner() == 2) {
//	            		 if (e.getSelected() == true) {
//	                		 canvas.drawBitmap(fighter2, null, setSizeRect, bitpaint);
//	            		 }
//	            		 else {
//	            			 canvas.drawBitmap(non_select_fighter, null, setSizeRect, bitpaint);
//	            			 //canvas.drawBitmap(bitmap, null, new Rect(), paint)
//	            		 }
//	        		 }
//            	 }
//            	 else if (e instanceof Archer) {
//	         		 if (e.getOwner() == 1){
//	            		 if (e.getSelected() == true) {
//	                		 canvas.drawBitmap(archer1, null, setSizeRect, bitpaint);
//	            		 }
//	            		 else {
//	            			 canvas.drawBitmap(non_select_archer, null, setSizeRect, bitpaint);
//	            		 }
//	        		 }
//	        		 else if (e.getOwner() == 2) {
//	            		 if (e.getSelected() == true) {
//	                		 canvas.drawBitmap(archer2, null, setSizeRect, bitpaint);
//	            		 }
//	            		 else {
//	            			 canvas.drawBitmap(non_select_archer, null, setSizeRect, bitpaint);
//	            		 }
//	        		 }
//            	 }
//        	 }
//        	 
//         }
//         drawGrid(canvas);
//    }
//    
////    public void loadPicture(String fileName) throws IOException {
////        Context context = getContext();
////        int resourceId = context.getResources().getIdentifier(
////            fileName, "drawable", getClass().getPackage().getName());
////        picture = BitmapFactory.decodeResource(context.getResources(), resourceId); 
////    }
////    
////    Paint paint = new Paint();
////    Rect bounds = new Rect();
//    protected void drawGrid(Canvas canvas) {
//        Paint gridPaint = new Paint();
//        gridPaint.setColor(Color.rgb(182, 155, 76));
//        gridPaint.setStrokeWidth(5); 
//        gridPaint.setStyle(Style.FILL_AND_STROKE);
//        
//        for (int i = 0; i <= PredictionGame.HEIGHT_WIDTH; i++) {
//            canvas.drawLine(0, i*scale_y, width, i*scale_y, gridPaint);
//        }
//        for (int i = 0; i <= PredictionGame.HEIGHT_WIDTH; i++) {
//            canvas.drawLine(i*scale_x, 0, i*scale_x, height, gridPaint);
//        }
//    }
//}
