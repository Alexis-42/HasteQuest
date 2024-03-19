package com.jeu.hastequest.model.games;

public class FlappyPlaneModel extends GameModel{
    public FlappyPlaneModel() {
        super(30,
                "Flappy Plane",
                "Evite les obstacles et reste en vie",
                3);
    }

    @Override
    public int computeMaxTime(int difficulty) {
        return this.maxTime;
    }
}
