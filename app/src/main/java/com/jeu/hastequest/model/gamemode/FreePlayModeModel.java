package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;

public class FreePlayModeModel extends GameModeModel{
    public Game selectedGame;
    public int difficulty;
    public FreePlayModeModel() {
        super();
        this.selectedGame = games[0];
    }

    public Game selectNextGame(){
        return games[(selectedGame.gameModel.id+1)%(games.length)];
    }

    public Game selectPreviousGame(){
        if(selectedGame.gameModel.id <= 0)
            return games[games.length-1];
        return games[selectedGame.gameModel.id-1];
    }
}
