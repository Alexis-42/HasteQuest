package com.jeu.hastequest.controller.games;

abstract public class Game{
    public String gameName;
    public int maxTime;
    public int id;

    public Game(int initialMaxTime, String gameName){
        this.maxTime = initialMaxTime;
        this.gameName = gameName;
    }

    abstract public int computeMaxTime(int difficulty);
}
