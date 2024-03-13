package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Dbh;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.controller.games.Quiz;
import com.jeu.hastequest.model.Score;
import com.jeu.hastequest.model.games.GameModel;

public class FreePlayModeModel extends GameModeModel{
    public Game selectedGame;
    public FreePlayModeModel() {
        super();
        setGames();
        this.selectedGame = games[0];
    }

    public Game selectNextGame(){
        return selectGame((selectedGame.gameModel.id+1)%(GameModel.globalId+1));
    }

    public Game selectPreviousGame(){
        if(selectedGame.gameModel.id-1 <= 0)
            return selectGame(GameModel.globalId);
        return selectGame(selectedGame.gameModel.id-1);
    }

    public Game selectGame(int id){
        return games[id-1];
    }

    @Override
    public Score[] gameGetScore() {
        // TODO faire une methode qui récupère les saves
        return new Score[0];
    }

    @Override
    public void setGames() {
        // TODO faire une methode qui récupère les jeux
        this.games = new Game[]{new Dbh(),new Quiz()};
    }
}
