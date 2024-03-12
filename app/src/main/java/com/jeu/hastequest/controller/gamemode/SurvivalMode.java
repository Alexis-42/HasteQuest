package com.jeu.hastequest.controller.gamemode;

import android.content.Context;

import com.jeu.hastequest.controller.Score;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.view.View;
import com.jeu.hastequest.view.gamemode.SurvivalModeView;

public class SurvivalMode extends GameMode{

    public SurvivalMode(Context context, Score[] scores, Game[] games) {
        super(new SurvivalModeView(context), scores, games);
    }


}
