package com.jeu.hastequest.controller;

import com.jeu.hastequest.view.View;
import com.jeu.hastequest.controller.games.Game;

abstract public class GameMode {
    public View view;
    public Score[] scores;
    public Game[] games;
    public int difficulty;

    /**
    public SurvivalMode(){
        scores = récupSaveScore();
        games = récupJeux()
        super(ViewFreePlay.getView(), scores, games);
    }**/
    public GameMode(View view, Score[] scores, Game[] games){
        this.view = view;
        this.scores = scores;
        this.games = games;
        this.difficulty = 0;
    }
}
