package com.jeu.hastequest.controller.games;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.model.games.GameModel;

abstract public class Game extends AppCompatActivity {
    public GameModel gameModel;
    public Bitmap gameImage;


    public Game(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    abstract void setGameImage();
}
