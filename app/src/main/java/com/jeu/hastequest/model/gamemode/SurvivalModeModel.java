package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Dbh;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.controller.games.Quiz;
import com.jeu.hastequest.model.Score;

public class SurvivalModeModel extends GameModeModel {
    public Game selectedGame;
    public SurvivalModeModel() {
        super();
        setGames();
        this.selectedGame = getRandomGame();
    }

    public Game getRandomGame(){
        return games[getRandomNumber(games.length)];
    }

    @Override
    public Score[] gameGetScore() {
        // TODO faire une methode qui récupère le fichier save
        return new Score[0];
    }

    @Override
    public void setGames() {
        // TODO faire une methode qui récupère les jeux
        this.games = new Game[]{new Quiz(), new Dbh()};
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }
}
