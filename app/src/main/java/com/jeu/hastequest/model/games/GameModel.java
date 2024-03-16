package com.jeu.hastequest.model.games;

abstract public class GameModel{
    public String gameName;
    public String rules;
    public int maxTime;
    public final int id;

    public GameModel(int initialMaxTime, String gameName, String rules, int id){
        this.maxTime = initialMaxTime;
        this.gameName = gameName;
        this.rules = rules;
        this.id = id;
    }

    abstract public int computeMaxTime(int difficulty);
}
