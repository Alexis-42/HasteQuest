package com.jeu.hastequest.model.games;

import android.os.Handler;
import android.util.Log;

import com.jeu.hastequest.controller.games.Memory;

public class MemoryModel extends GameModel{
    public int seconds;
    private Handler handlerChrono;
    public int clickedFirst, clickedSecond;
    public int idFirst, idSecond;
    public int cardNumber;
    public int flippedCardNb;
    public MemoryModel() {
        super(
                40,
                "Memory",
                "Retrouve les paires de cartes dans un temps imparti",
                2);

        this.flippedCardNb = 0;
        this.cardNumber = 1;
    }

    @Override
    public int computeMaxTime(int difficulty) {
        if(difficulty > 25){
            return 15;
        }else{
            return maxTime - difficulty;
        }
    }

    public boolean calculate() {
        return clickedSecond == clickedFirst;
    }

    public void init(Memory memory, boolean isSurvival, int score, int lives, int difficulty){
        this.seconds = computeMaxTime(difficulty);
        handlerChrono = new Handler();
        memory.updateTime(seconds);
        // Répète le runnable après un délai
        Runnable runnableChrono = new Runnable() {
            @Override
            public void run() {
                seconds--;
                Log.i("memoriesec", "memorie handler chrono "+seconds);
                memory.updateTime(seconds);
                memory.checkWin(isSurvival, score, lives, difficulty);
                if(!memory.finished){
                    handlerChrono.postDelayed(this, 1000); // Répète le runnable après un délai
                }else{
                    handlerChrono.removeCallbacks(this);
                }
            }
        };
        handlerChrono.postDelayed(runnableChrono, 1000); // Démarrer le runnable avec un délai initial
    }
}
