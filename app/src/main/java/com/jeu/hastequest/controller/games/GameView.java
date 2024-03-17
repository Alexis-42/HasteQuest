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

public class GameView extends View {
    int vie, difficulty, score;
    boolean isSurvival;
    Bitmap background, ground, chara;
    Rect rectBackground, rectGround;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    float TEXT_SIZE = 120;
    int life = 1;
    static int dWidth, dHeight;
    Random random;
    float charX, charY;
    float oldX;
    float oldCharX;
    ArrayList<Spike> spikes;
    public int seconds = 20;
    ArrayList<Explosion>explosions;
    private Handler handlerChrono;
    private Runnable runnableChrono;



    public GameView(Context context, int vie, int difficulty, int score, boolean isSurvival) {
        super(context);
        this.isSurvival = isSurvival;
        this.vie = vie;
        this.difficulty = difficulty;
        this.score = score;
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        chara = BitmapFactory.decodeResource(getResources(), R.drawable.chara);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0,0,dWidth,dHeight);
        rectGround = new Rect(0,dHeight - ground.getHeight(),dWidth,dHeight);
        handler = new Handler();
        runnable = this::invalidate;
        textPaint.setColor(Color.rgb(255,165,0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        random = new Random();
        charX = dWidth / 2 - chara.getWidth() / 2;
        charY = dHeight - ground.getHeight() - chara.getHeight();
        spikes = new ArrayList<>();
        explosions = new ArrayList<>();
        for(int i=0;i<3;i++){
            Spike spike = new Spike(context);
            spikes.add(spike);
        }
        init(context);
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(background,null,rectBackground,null);
        canvas.drawBitmap(ground,null,rectGround,null);
        canvas.drawBitmap(chara,charX,charY,null);
        for(int i=0;i<spikes.size();i++){
            canvas.drawBitmap(spikes.get(i).getSpike(spikes.get(i).spikeFrame),spikes.get(i).spikeX,spikes.get(i).spikeY,null);
            spikes.get(i).spikeFrame ++;
            if(spikes.get(i).spikeFrame>2){
                spikes.get(i).spikeFrame = 0;
            }
            spikes.get(i).spikeY += spikes.get(i).spikeVelocity;
            if(spikes.get(i).spikeY + spikes.get(i).getSpikeHeight() >= dHeight - ground.getHeight()){
                Explosion explosion = new Explosion(context);
                explosion.explosionX = spikes.get(i).spikeX;
                explosion.explosionY = spikes.get(i).spikeY;
                explosions.add(explosion);
                spikes.get(i).resetPosition();
            }
        }
        for(int i=0; i<spikes.size();i++){
            if(spikes.get(i).spikeX + spikes.get(i).getSpikeWidth() >= charX
                    && spikes.get(i).spikeX <= charX + chara.getWidth()
                    && spikes.get(i).spikeY + spikes.get(i).getSpikeWidth() >= charY
                    &&spikes.get(i).spikeY + spikes.get(i).getSpikeWidth() <= charY + chara.getHeight()){
                life--;
                spikes.get(i).resetPosition();
                if(life == 0){
                    if(this.isSurvival){
                        Intent intent = new Intent(context, SurvivalMode.class);
                        Bundle extras = new Bundle();
                        extras.putInt("score", this.score);
                        extras.putInt("lives", this.vie-1);
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
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                }
            }
        }
        for(int i=0; i<explosions.size();i++){
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame),explosions.get(i).explosionX,explosions.get(i).explosionY,null);
            explosions.get(i).explosionFrame++;
            if(explosions.get(i).explosionFrame>3){
                explosions.remove(i);
            }
        }
        if(seconds<=0){
            if(this.isSurvival){
                Intent intent = new Intent(context, SurvivalMode.class);
                Bundle extras = new Bundle();
                extras.putInt("score", this.score);
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
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }
        canvas.drawText("" + seconds, 20, TEXT_SIZE, textPaint);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchY >= charY){
            int action = event.getAction();
            if(action==MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldCharX = charX;
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX - touchX;
                float newCharX = oldCharX - shift;
                if(newCharX <= 0){
                    charX = 0;
                }else if(newCharX >= dWidth - chara.getWidth()){
                    charX = dWidth - chara.getWidth();
                }else{
                    charX = newCharX;
                }
            }
        }
        return true;
    }

    private void init(Context context){
        handler.postDelayed(runnable,UPDATE_MILLIS);
        handlerChrono = new Handler();
        runnableChrono = new Runnable() {
            @Override
            public void run() {
                seconds--;
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

    // Vue personnalisée pour dessiner le chronomètre
    private class ChronometerView extends View {

        private Paint textPaint;

        public ChronometerView(Context context) {
            super(context);
            init();
        }

        public ChronometerView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public ChronometerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(TEXT_SIZE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawText("" + seconds, 20, TEXT_SIZE, textPaint);
        }
    }

}
