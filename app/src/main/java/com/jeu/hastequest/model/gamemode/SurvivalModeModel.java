package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

public class SurvivalModeModel extends GameModeModel {
    public int lives;
    public Game selectedGame;
    public SurvivalModeModel() {
        super();
        this.selectedGame = getRandomGame();
        this.lives = 3;
    }

    public Game getRandomGame(){
        return games[getRandomNumber(games.length)];
    }

    @Override
    public Score[] gameGetScore() {
        // TODO faire une methode qui récupère le fichier save
        return new Score[0];
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }
}
