package com.jeu.hastequest.controller.games;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.model.games.GameModel;

abstract public class Game extends AppCompatActivity {
    public GameModel gameModel;

    public int gameImage;

    public Game(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    abstract void setGameImage();
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }
}
