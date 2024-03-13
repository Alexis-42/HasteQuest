package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

public class SurvivalModeModel extends GameModeModel {
    public SurvivalModeModel() {
        super();
        setGames();
    }

    @Override
    public Score[] gameGetScore() {
        // TODO faire une methode qui récupère le fichier save
        return new Score[0];
    }

    @Override
    public void setGames() {
        // TODO faire une methode qui récupère les jeux
        this.games = new Game[0];
    }
}
