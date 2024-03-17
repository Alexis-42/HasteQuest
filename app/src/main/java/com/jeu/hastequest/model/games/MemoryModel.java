package com.jeu.hastequest.model.games;

import android.os.Handler;

import com.jeu.hastequest.controller.games.Memory;

public class MemoryModel extends GameModel{
    public int seconds = 20;
    private Handler handlerChrono;
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

    public void init(Memory memory, boolean isSurvival, int score, int lives, int difficulty){
        handlerChrono = new Handler();
        memory.updateTime(seconds);
        // Répète le runnable après un délai
        Runnable runnableChrono = new Runnable() {
            @Override
            public void run() {
                seconds--;
                memory.updateTime(seconds);
                memory.checkWin(isSurvival, score, lives, difficulty);
                if(!memory.finished){
                    handlerChrono.postDelayed(this, 1000); // Répète le runnable après un délai
                }
            }
        };
        handlerChrono.postDelayed(runnableChrono, 1000); // Démarrer le runnable avec un délai initial
    }
}
