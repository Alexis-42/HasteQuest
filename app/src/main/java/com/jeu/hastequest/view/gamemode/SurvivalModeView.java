package com.jeu.hastequest.view.gamemode;

import android.os.Bundle;

import com.jeu.hastequest.R;
import com.jeu.hastequest.view.View;

public class SurvivalModeView extends View {

    public SurvivalModeView(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
}
