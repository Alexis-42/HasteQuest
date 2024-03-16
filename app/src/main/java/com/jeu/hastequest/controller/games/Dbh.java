package com.jeu.hastequest.controller.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.DbhModel;


public class Dbh extends Game{

    public Dbh(){
        super(new DbhModel());
        setGameImage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        boolean isSurvival = extras.getBoolean("survival");
        int lives;
        int score;
        int difficulty;

        if(isSurvival){
            lives = extras.getInt("lives");
            score = extras.getInt("score");
            difficulty = extras.getInt("difficulty");
        } else {
            lives = 0;
            difficulty = 0;
            score = 0;
        }

        GameView gameView = new GameView(this,lives,difficulty,score,isSurvival);
        setContentView(gameView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    void setGameImage(){
        this.gameImage = R.drawable.game_title;
    }


}

