package com.jeu.hastequest.controller.gamemode;

import android.os.Bundle;

import com.jeu.hastequest.R;

public class SurvivalMode extends GameMode{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
}
