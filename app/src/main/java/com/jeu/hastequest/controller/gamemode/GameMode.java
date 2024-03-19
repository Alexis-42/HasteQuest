package com.jeu.hastequest.controller.gamemode;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.model.gamemode.GameModeModel;

abstract public class GameMode extends AppCompatActivity {
    public GameModeModel gameModeModel;
    public GameMode(){
        super();
    }

    abstract public void setGameModel();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }
}
