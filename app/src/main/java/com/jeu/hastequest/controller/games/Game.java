package com.jeu.hastequest.controller.games;

import com.jeu.hastequest.view.View;

abstract public class Game{
    public String gameName;
    public int maxTime;
    public View view;

    public Game(View view,int initialMaxTime, int difficulty, String gameName){
        this.view = view;
        this.maxTime = computeMaxTime(initialMaxTime, difficulty);
        this.gameName = gameName;
    }

    abstract public int computeMaxTime(int initialMaxTime, int difficulty);
}
