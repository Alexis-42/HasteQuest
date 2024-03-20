package com.jeu.hastequest.controller.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.gamemode.FreePlayMode;
import com.jeu.hastequest.controller.gamemode.SurvivalMode;

import java.util.ArrayList;
import java.util.Random;

public class FlappyPlaneView extends View {
    int vie, difficulty, score;
    boolean isSurvival;
    Bitmap background;
    Bitmap toptube, bottomtube;
    Rect rect;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    float TEXT_SIZE = 120;
    int life = 1;
    static int dWidth, dHeight;
    Random random;

    public int seconds = 20;
    private Handler handlerChrono;
    private Runnable runnableChrono;
    Display display;
    public Point point;
    public Bitmap birds[];
    int birdFrame = 0;
    int velocity = 0, gravity = 3;
    int birdY, birdX;
    boolean gameState = false;
    int gap = 400;
    int minTubeOffset, maxTubeOffset;
    int numberOfTubes = 4;
    int distanceBetweenTubes;
    int[] tubeX = new int[numberOfTubes];
    int[] topTubeY = new int[numberOfTubes];
    int tubeVelocity = 8;
    public boolean isFinished = true;


    public FlappyPlaneView(Context context, int lives, int difficulty, int score, boolean isSurvival) {
        super(context);
        this.isSurvival = isSurvival;
        this.vie = lives;
        this.difficulty = difficulty;
        this.score = score;
        this.context = context;
        isFinished=true;

        tubeVelocity = 8 + 2*this.difficulty;

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundcity);
        toptube = BitmapFactory.decodeResource(getResources(), R.drawable.toptube);
        bottomtube = BitmapFactory.decodeResource(getResources(), R.drawable.bottomtube);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;

        textPaint.setColor(Color.rgb(255,165,0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);

        rect = new Rect(0, 0, dWidth, dHeight);
        birds = new Bitmap[2];
        birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.chara);//bird1
        birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.chara);//bird2
        birdX = dWidth / 2 - birds[0].getWidth() / 2;
        birdY = dHeight / 2 - birds[0].getHeight() / 2;
        distanceBetweenTubes = dWidth * 3 / 4;
        minTubeOffset = gap / 2;
        maxTubeOffset = dHeight - minTubeOffset - gap;
        random = new Random();
        for(int i = 0; i < numberOfTubes; i++){
            tubeX[i] = dWidth + i * distanceBetweenTubes;
            topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
        }
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rect, null);

        if(birdFrame == 0){
            birdFrame = 1;
        } else {
            birdFrame = 0;
        }

        if(gameState){
            if(birdY < dHeight - birds[0].getHeight() || velocity < 0){
                velocity += gravity;
                birdY += velocity;
            }
            for(int i = 0; i < numberOfTubes; i++){
                tubeX[i] -= tubeVelocity;
                if(tubeX[i] < -toptube.getWidth()){
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
                }
                canvas.drawBitmap(toptube, tubeX[i], topTubeY[i] - toptube.getHeight(), null);
                canvas.drawBitmap(bottomtube, tubeX[i], topTubeY[i] + gap, null);

                if (((birdX + birds[0].getWidth() > tubeX[i] && birdX < tubeX[i] + toptube.getWidth())
                        || (birdX > tubeX[i] && birdX + birds[0].getWidth() < tubeX[i] + toptube.getWidth()))){
                    if (birdY < topTubeY[i] || birdY + birds[0].getHeight() > topTubeY[i] + gap
                    && isFinished){
                        isFinished = false;
                        if(this.isSurvival){
                            Intent intent = new Intent(context, SurvivalMode.class);
                            Bundle extras = new Bundle();
                            //Log.i("newscoreL","score : "+(this.score));
                            extras.putInt("score", this.score);
                            extras.putInt("lives", this.vie-1);
                            extras.putInt("difficulty", this.difficulty);
                            extras.putBoolean("survival", true);
                            intent.putExtras(extras);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }else {
                            Intent intent = new Intent(context, FreePlayMode.class);
                            Bundle extras = new Bundle();
                            extras.putBoolean("survival", false);
                            intent.putExtras(extras);
                            ((Activity) context).finish();
                        }
                    }
                }
            }
        }
        if(seconds<=0 && isFinished){
            isFinished = false;
            if(this.isSurvival){
                Intent intent = new Intent(context, SurvivalMode.class);
                Bundle extras = new Bundle();
                //Log.i("score+",""+(1 + Math.floor((double)difficulty/5.0)));
                //Log.i("newscore","score : "+(this.score));
                extras.putInt("score", ((int)(this.score + 1 + Math.floor((double)difficulty/5.0))));
                extras.putInt("lives", this.vie);
                extras.putInt("difficulty", this.difficulty+1);
                extras.putBoolean("survival", true);
                intent.putExtras(extras);
                context.startActivity(intent);
                ((Activity) context).finish();
            }else {
                Intent intent = new Intent(context, FreePlayMode.class);
                Bundle extras = new Bundle();
                extras.putBoolean("survival", false);
                intent.putExtras(extras);
                ((Activity) context).finish();
            }
        }
        canvas.drawText("" + seconds, 20, TEXT_SIZE, textPaint);

        canvas.drawBitmap(birds[birdFrame], birdX, birdY, null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            velocity = -30;
            gameState = true;
        }
        return true;
    }

    private void init(Context context){
        handlerChrono = new Handler();
        runnableChrono = new Runnable() {
            @Override
            public void run() {
                if(gameState){
                    seconds--;
                }
                //Log.i("seconds", seconds+"s");
                handlerChrono.postDelayed(this, 1000); // Répète le runnable après un délai
            }
        };
        handlerChrono.postDelayed(runnableChrono, 1000); // Démarrer le runnable avec un délai initial
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable); // Arrêter le runnable lorsque la vue est détachée de la fenêtre
        handlerChrono.removeCallbacks(runnableChrono);
    }
}
