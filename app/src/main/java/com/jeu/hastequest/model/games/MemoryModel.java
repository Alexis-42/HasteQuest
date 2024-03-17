package com.jeu.hastequest.model.games;

public class MemoryModel extends GameModel{
    public int clickedFirst, clickedSecond;
    public int idFirst, idSecond;
    public int cardNumber;
    public int flippedCardNb;
    public MemoryModel() {
        super(
                30,
                "Memory",
                "Retrouve les paires de cartes dans un temps imparti",
                2);

        this.flippedCardNb = 0;
        this.cardNumber = 1;
    }

    @Override
    public int computeMaxTime(int difficulty) {
        return 0;
    }

    public boolean calculate() {
        return clickedSecond == clickedFirst;
    }
}
