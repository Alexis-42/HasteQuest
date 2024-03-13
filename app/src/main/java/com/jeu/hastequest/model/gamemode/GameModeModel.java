package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

abstract public class GameModeModel {
    public Score[] scores;
    public Game[] games;
    public int difficulty;

    public GameModeModel(){
        this.scores = gameGetScore();
        this.difficulty = 0;
    }

    abstract public Score[] gameGetScore();
    abstract public void setGames();
}
