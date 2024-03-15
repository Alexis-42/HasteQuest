package com.jeu.hastequest.controller.games;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.DbhModel;
import com.jeu.hastequest.controller.games.GameView;


public class Dbh extends Game{

    public Dbh(){
        super(new DbhModel());
        setGameImage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(this);
        setContentView(gameView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    void setGameImage(){
        this.gameImage = R.drawable.game_title;
    }


}

