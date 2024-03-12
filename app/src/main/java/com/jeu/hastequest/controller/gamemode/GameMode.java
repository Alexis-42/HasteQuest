package com.jeu.hastequest.controller.gamemode;

import com.jeu.hastequest.controller.Score;
import com.jeu.hastequest.view.View;
import com.jeu.hastequest.controller.games.Game;

abstract public class GameMode {
    public View view;
    public Score[] scores;
    public Game[] games;
    public int difficulty;

    /**
    public SurvivalMode(){
        scores = récupSaveScore();
        games = récupJeux()
        super(ViewFreePlay.getView(), scores, games);
    }**/
    public GameMode(View view, Score[] scores, Game[] games){
        this.view = view;
        this.scores = scores;
        this.games = games;
        this.difficulty = 0;
    }

    public View selectGameView(int id){
        for(Game game : games){
            if(game.id == id){
                return game.view;
            }
        }
        // si jamais on trouve pas l'id du jeu
        return this.view;
    }
}
