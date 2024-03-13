package com.jeu.hastequest.controller.gamemode;

import android.os.Bundle;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.gamemode.SurvivalModeModel;

public class SurvivalMode extends GameMode{

    @Override
    public void setGameModel() {
        this.gameModeModel = new SurvivalModeModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
}
