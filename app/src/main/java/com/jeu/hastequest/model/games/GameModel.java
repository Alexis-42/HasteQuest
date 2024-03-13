package com.jeu.hastequest.model.games;

abstract public class GameModel{
    public String gameName;
    public String rules;
    public int maxTime;
    static public int globalId = 0;
    public final int id;

    public GameModel(int initialMaxTime, String gameName, String rules){
        this.maxTime = initialMaxTime;
        this.gameName = gameName;
        this.rules = rules;
        this.id = globalId;
        ++globalId;
    }

    abstract public int computeMaxTime(int difficulty);
}
