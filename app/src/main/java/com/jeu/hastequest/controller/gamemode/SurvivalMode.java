package com.jeu.hastequest.controller.gamemode;

import com.jeu.hastequest.controller.Score;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.view.View;
import com.jeu.hastequest.view.gamemode.SurvivalModeView;

public class SurvivalMode extends GameMode{
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
    public SurvivalMode(View view, Score[] scores, Game[] games) {
        super(new SurvivalModeView(), scores, games);
    }


}
