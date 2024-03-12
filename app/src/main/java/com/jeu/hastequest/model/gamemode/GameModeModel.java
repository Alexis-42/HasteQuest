package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

public class GameModeModel {
    public Score[] scores;
    public Game[] games;
    public int difficulty;

    public GameModeModel(Score[] scores, Game[] games){
        this.scores = scores;
        this.games = games;
        this.difficulty = 0;
    }
}
