package com.jeu.hastequest.model.games;

abstract public class GameModel{
    public String gameName;
    public String rules;
    public int maxTime;
    static int globalId = 0;
    final int id;

    public GameModel(int initialMaxTime, String gameName, String rules){
        this.maxTime = initialMaxTime;
        this.gameName = gameName;
        this.rules = rules;
        this.id = globalId;
        ++globalId;
    }


    abstract public int computeMaxTime(int difficulty);
}
