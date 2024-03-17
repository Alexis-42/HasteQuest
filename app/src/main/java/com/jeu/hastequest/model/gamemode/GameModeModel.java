package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Dbh;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.controller.games.Memory;
import com.jeu.hastequest.controller.games.Quiz;
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
        this.games = new Game[]{new Quiz(),new Dbh(), new Memory()};
    }

    abstract public Score[] gameGetScore();
}
