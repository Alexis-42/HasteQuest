package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.ScoreModel;

public class GameModeModel {
    public ScoreModel[] scores;
    public Game[] games;
    public int difficulty;

    public GameModeModel(ScoreModel[] scores, Game[] games){
        this.scores = scores;
        this.games = games;
        this.difficulty = 0;
    }
}
