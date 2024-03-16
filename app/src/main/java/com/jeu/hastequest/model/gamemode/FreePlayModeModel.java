package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

public class FreePlayModeModel extends GameModeModel{
    public Game selectedGame;
    public FreePlayModeModel() {
        super();
        this.selectedGame = games[0];
    }

    public Game selectNextGame(){
        if(selectedGame.gameModel.id >= games.length)
            return games[0];
        return games[selectedGame.gameModel.id+1];
    }

    public Game selectPreviousGame(){
        if(selectedGame.gameModel.id <= 1)
            return games[games.length-1];
        return games[selectedGame.gameModel.id-1];
    }

    @Override
    public Score[] gameGetScore() {
        // TODO faire une methode qui récupère les saves
        return new Score[0];
    }
}
