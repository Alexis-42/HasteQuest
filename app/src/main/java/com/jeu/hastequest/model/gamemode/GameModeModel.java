package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Dbh;
import com.jeu.hastequest.controller.games.FlappyPlane;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.controller.games.Memory;
import com.jeu.hastequest.controller.games.Quiz;


abstract public class GameModeModel {
    public Game[] games;
    public int currentScore;
    public int difficulty;

    public GameModeModel(){
        this.difficulty = 0;
        this.currentScore = 0;
        this.games = new Game[]{new Quiz(),new Dbh(), new Memory(), new FlappyPlane()};
    }


}
