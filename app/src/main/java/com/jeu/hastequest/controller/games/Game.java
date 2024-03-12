package com.jeu.hastequest.controller.games;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.model.games.GameModel;

abstract public class Game extends AppCompatActivity {
    GameModel game;

    public Game(GameModel game){
        this.game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
}
