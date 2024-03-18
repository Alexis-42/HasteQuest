package com.jeu.hastequest.controller.games;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.gamemode.FreePlayMode;
import com.jeu.hastequest.controller.gamemode.SurvivalMode;
import com.jeu.hastequest.model.games.MemoryModel;

import java.util.Arrays;
import java.util.Collections;

public class Memory extends Game{
    public boolean finished = false;

    public ImageView card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12;
    public int image1,image2,image3,image4,image5,image6;

    public ImageView clickedFirst;
    public TextView chronometer;

    public boolean waitForReturn;

    public Integer[] cardArray = {1,2,3,4,5,6,1,2,3,4,5,6};

    public Memory() {
        super(new MemoryModel());
        setGameImage();
        this.waitForReturn = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        boolean isSurvival = extras.getBoolean("survival");
        int lives;
        int score;
        int difficulty;


        this.card1 = findViewById(R.id.carte1);
        this.card2 = findViewById(R.id.carte2);
        this.card3 = findViewById(R.id.carte3);
        this.card4 = findViewById(R.id.carte4);
        this.card5 = findViewById(R.id.carte5);
        this.card6 = findViewById(R.id.carte6);
        this.card7 = findViewById(R.id.carte7);
        this.card8 = findViewById(R.id.carte8);
        this.card9 = findViewById(R.id.carte9);
        this.card10 = findViewById(R.id.carte10);
        this.card11 = findViewById(R.id.carte11);
        this.card12 = findViewById(R.id.carte12);

        this.chronometer = findViewById(R.id.chrono);

        if(isSurvival){
            lives = extras.getInt("lives");
            score = extras.getInt("score");
            difficulty = extras.getInt("difficulty");
        } else {
            lives = 0;
            difficulty = 0;
            score = 0;
        }

        loadImages();
        Collections.shuffle(Arrays.asList(cardArray));

        this.card1.setOnClickListener(v -> flipCard(0,card1,isSurvival,score,difficulty,lives));
        this.card2.setOnClickListener(v -> flipCard(1,card2,isSurvival,score,difficulty,lives));
        this.card3.setOnClickListener(v -> flipCard(2,card3,isSurvival,score,difficulty,lives));
        this.card4.setOnClickListener(v -> flipCard(3,card4,isSurvival,score,difficulty,lives));
        this.card5.setOnClickListener(v -> flipCard(4,card5,isSurvival,score,difficulty,lives));
        this.card6.setOnClickListener(v -> flipCard(5,card6,isSurvival,score,difficulty,lives));
        this.card7.setOnClickListener(v -> flipCard(6,card7,isSurvival,score,difficulty,lives));
        this.card8.setOnClickListener(v -> flipCard(7,card8,isSurvival,score,difficulty,lives));
        this.card9.setOnClickListener(v -> flipCard(8,card9,isSurvival,score,difficulty,lives));
        this.card10.setOnClickListener(v -> flipCard(9,card10,isSurvival,score,difficulty,lives));
        this.card11.setOnClickListener(v -> flipCard(10,card11,isSurvival,score,difficulty,lives));
        this.card12.setOnClickListener(v -> flipCard(11,card12,isSurvival,score,difficulty,lives));

        getModel().init(this,isSurvival, score, lives, difficulty);
    }

    public void flipCard(int i, ImageView card, boolean isSurvival, int score, int difficulty, int lives){
        if(this.waitForReturn){
            return;
        }else {
            switch (cardArray[i]) {
                case 1:
                    card.setImageResource(image1);
                    break;
                case 2:
                    card.setImageResource(image2);
                    break;
                case 3:
                    card.setImageResource(image3);
                    break;
                case 4:
                    card.setImageResource(image4);
                    break;
                case 5:
                    card.setImageResource(image5);
                    break;
                case 6:
                    card.setImageResource(image6);
                    break;
            }

            if (getModel().cardNumber == 1) {
                this.clickedFirst = card;
                getModel().clickedFirst = cardArray[i];
                getModel().idFirst = i;
                getModel().cardNumber = 2;
            } else if (getModel().cardNumber == 2 && card != this.clickedFirst){
                getModel().clickedSecond = cardArray[i];
                getModel().idSecond = i;
                getModel().cardNumber = 1;

                Handler handler = new Handler();

                this.waitForReturn = true;

                if(getModel().calculate())
                    getModel().flippedCardNb += 2;
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Log.i("memorie", "memorie handler running");
                        if (getModel().calculate()) {
                            card.setVisibility(ImageView.INVISIBLE);
                            clickedFirst.setVisibility(ImageView.INVISIBLE);
                        } else {
                            card.setImageResource(R.drawable.intero_point);
                            clickedFirst.setImageResource(R.drawable.intero_point);
                        }
                        waitForReturn = false;
                    }
                }, 500);

                if (getModel().flippedCardNb >= 12) {
                    Bundle extras = new Bundle();
                    Intent intent;

                    if (isSurvival) {
                        score += 1;
                        difficulty += 1;
                        intent = new Intent(getApplicationContext(), SurvivalMode.class);
                        extras.putInt("score", score + 1);
                        extras.putInt("lives", lives);
                        extras.putInt("difficulty", difficulty + 1);
                    } else {
                        intent = new Intent(getApplicationContext(), FreePlayMode.class);
                    }
                    this.finished = true;
                    extras.putBoolean("survival", isSurvival);
                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        }
    }

    private void loadImages(){
        image1 = R.drawable.memory_card1;
        image2 = R.drawable.memory_card2;
        image3 = R.drawable.memory_card3;
        image4 = R.drawable.memory_card4;
        image5 = R.drawable.memory_card5;
        image6 = R.drawable.memory_card6;
    }

    public MemoryModel getModel(){
        return (MemoryModel) this.gameModel;
    }
    @Override
    void setGameImage() {
        this.gameImage = R.drawable.memory;
    }

    public void updateTime(int seconds) {
        this.chronometer.setText(String.valueOf(seconds));
    }

    public void checkWin(boolean isSurvival, int score, int lives, int difficulty){
        if(getModel().seconds<=0){
            if(isSurvival){
                this.finished = true;
                Intent intent = new Intent(getApplicationContext(), SurvivalMode.class);
                Bundle extras = new Bundle();
                extras.putInt("score", score);
                extras.putInt("lives", lives-1);
                extras.putInt("difficulty", difficulty);
                extras.putBoolean("survival", true);
                intent.putExtras(extras);
                startActivity(intent);
            }else {
                this.finished = true;
                Intent intent = new Intent(getApplicationContext(), FreePlayMode.class);
                Bundle extras = new Bundle();
                extras.putBoolean("survival", false);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}
