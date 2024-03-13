package com.jeu.hastequest.model.games;

public class DbhModel extends GameModel{
    public DbhModel() {
        super(30,
                "Don't be hurt",
                "Ne te fais pas toucher pendant le temps imparti");
    }

    @Override
    public int computeMaxTime(int difficulty) {
        return this.maxTime;
    }
}
