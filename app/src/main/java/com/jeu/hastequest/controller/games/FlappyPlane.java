package com.jeu.hastequest.controller.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.DbhModel;
import com.jeu.hastequest.model.games.FlappyPlaneModel;
import com.jeu.hastequest.model.games.GameModel;
import com.jeu.hastequest.model.games.MemoryModel;

public class FlappyPlane  extends Game{

    public FlappyPlane() {
        super(new FlappyPlaneModel());
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
        int difficulty = extras.getInt("difficulty");

        if(isSurvival){
            lives = extras.getInt("lives");
            score = extras.getInt("score");
        } else {
            lives = 0;
            score = 0;
        }

        // TODO: Implement FlappyPlaneView
        FlappyPlaneView flappyPlaneView = new FlappyPlaneView(this,lives,difficulty,score,isSurvival);
        setContentView(flappyPlaneView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    void setGameImage() {
        this.gameImage = R.drawable.game_title;
    }
}
