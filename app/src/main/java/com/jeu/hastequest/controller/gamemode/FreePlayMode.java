package com.jeu.hastequest.controller.gamemode;

import com.jeu.hastequest.controller.Score;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.view.View;
import com.jeu.hastequest.view.gamemode.FreePlayModeView;

public class FreePlayMode extends GameMode{
    /**
     * public SurvivalMode(){
     * scores = récupSaveScore();
     * games = récupJeux()
     * super(ViewFreePlay.getView(), scores, games);
     * }
     *
     * @param view
     * @param scores
     * @param games
     **/
    public FreePlayMode(View view, Score[] scores, Game[] games) {
        super(new FreePlayModeView(), scores, games);
    }
}
