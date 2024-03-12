package com.jeu.hastequest.controller.games;

import com.jeu.hastequest.view.View;

abstract public class Game{
    public String gameName;
    public int maxTime;
    public View view;
    public int id;

    public Game(View view,int initialMaxTime, String gameName){
        this.view = view;
        this.maxTime = initialMaxTime;
        this.gameName = gameName;
    }

    abstract public int computeMaxTime(int difficulty);
}
