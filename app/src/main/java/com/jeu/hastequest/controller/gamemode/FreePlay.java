package com.jeu.hastequest.controller.gamemode;

import com.jeu.hastequest.controller.Score;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.view.View;
import com.jeu.hastequest.view.gamemode.FreePlayView;

public class FreePlay extends GameMode{
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
    public FreePlay(View view, Score[] scores, Game[] games) {
        super(new FreePlayView(), scores, games);
    }
}
