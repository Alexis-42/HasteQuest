package com.jeu.hastequest.model.gamemode;

import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.Score;

public class FreePlayModeModel extends GameModeModel{
    public Game selectedGame;
    public FreePlayModeModel(Score[] scores, Game[] games) {

        super(scores, games);
    }

    public void selectGame(Game game){
    }
}
