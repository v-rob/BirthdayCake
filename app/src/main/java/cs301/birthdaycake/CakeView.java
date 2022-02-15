package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

public class CakeView extends SurfaceView {
    private CakeModel cakeModel;

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint square1 = new Paint();
    Paint square2 = new Paint();
    Paint textPaint = new Paint();
    Paint balloonPaint = new Paint();
    Paint stringPaint = new Paint();
    Paint[] allPaint = new Paint[]{cakePaint, frostingPaint, candlePaint, outerFlamePaint, innerFlamePaint, wickPaint, balloonPaint, stringPaint, textPaint};

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 120.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public static final float balloonWidth = 100f;
    public static final float balloonHeight = 140f;
    public static final float stringLength = 200f;
    public static final float stringWidth = 10f;
    public static final float rainbowSpeed = 10;

    //hehehehhehehehehehehfehfhauGBOESB VSDnjg bEOWJNFOI N
    Random r = new Random();

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        cakeModel = new CakeModel();

        //Setup our palette
        cakePaint.setColor(0xFF61F1EA);  //violet-red (Changed to Sky-blue)
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        balloonPaint.setColor(0xFFFF0000);
        balloonPaint.setStyle(Paint.Style.FILL);
        stringPaint.setColor(0xFFAAAAAA);
        stringPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(0xFF900000);
        textPaint.setTextSize(40f);

        square1.setColor(Color.GREEN);
        square1.setStyle(Paint.Style.FILL);
        square2.setColor(Color.RED);
        square2.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default
    }

    public CakeModel getCakeModel() {
        return this.cakeModel;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if (!this.cakeModel.hasCandles)
            return;

        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        if (this.cakeModel.candlesLit) {
            //draw the outer flame
            float flameCenterX = left + candleWidth / 2;
            float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

            //draw the inner flame
            flameCenterY += outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
        }

        //draw the wick
        float wickLeft = left + candleWidth / 2 - wickWidth / 2;
        float wickTop = bottom - wickHeight - candleHeight;
        canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
    }

    public void drawBalloon(Canvas canvas, int x, int y) {
        canvas.drawOval(x-balloonWidth/2, y-balloonHeight/2, x+balloonWidth/2,
                y+balloonHeight/2, balloonPaint);
        canvas.drawRect(x-stringWidth/2, y+balloonHeight/2, x+stringWidth/2,
                y+balloonHeight/2+stringLength, stringPaint);
    }
    public void stepRainbow(Paint paint) {
        float[] hsv = new float[3];
        Color.colorToHSV(paint.getColor(), hsv);
        float hue = hsv[0]+rainbowSpeed;
        if (hue > 360){
            hue = 0;
        }
        paint.setColor(Color.HSVToColor(new float[]{hue,hsv[1],hsv[2]}));
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        for (Paint each : allPaint) {
            stepRainbow(each);
        }
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Finally, draw the candles
        for (int i = 1; i <= this.cakeModel.numCandles; i++) {
            drawCandle(canvas, cakeLeft + cakeWidth * i / (this.cakeModel.numCandles + 1) -
                    candleWidth / 2, cakeTop);

            if (cakeModel.touchX != -1 && cakeModel.touchY != -1) {
                canvas.drawText("X-Coordinate: " + cakeModel.touchX + " " + "Y-Coordinate: " + cakeModel.touchY, 400f, 900f, textPaint);
            }
        }

        //stepRainbow(balloonPaint);
        drawBalloon(canvas, cakeModel.balloonX, cakeModel.balloonY);
        drawChecker(canvas);
        this.invalidate();
    }//onDraw

    public void drawChecker(Canvas canvas){
        //draws the checkerboard on the position where the person touched the view
        // - 1 to keep it hidden until touched
        if(cakeModel.squareX > -1 && cakeModel.squareY > -1){
            //top left
            canvas.drawRect(cakeModel.squareX - 50, cakeModel.squareY - 50, cakeModel.squareX, cakeModel.squareY,square1);
            //top right
            canvas.drawRect(cakeModel.squareX,cakeModel.squareY - 50, cakeModel.squareX + 50,cakeModel.squareY,square2);
            //bottom left
            canvas.drawRect(cakeModel.squareX - 50, cakeModel.squareY, cakeModel.squareX,cakeModel.squareY + 50, square2);
            //bottom right
            canvas.drawRect(cakeModel.squareX, cakeModel.squareY, cakeModel.squareX + 50,cakeModel.squareY + 50, square1);
        }
    }

}//class CakeView

