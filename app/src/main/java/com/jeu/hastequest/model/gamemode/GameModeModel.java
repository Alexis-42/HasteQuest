package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

abstract public class GameModeModel {
    public Score[] scores;
    public Game[] games;
    public Score currentScore;
    public int difficulty;

    public GameModeModel(){
        this.scores = gameGetScore();
        this.difficulty = 0;
        this.currentScore = new Score(0,"");
    }

    abstract public Score[] gameGetScore();
    abstract public void setGames();
}
