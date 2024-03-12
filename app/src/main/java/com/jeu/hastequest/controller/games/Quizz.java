package com.jeu.hastequest.controller.games;

import android.content.Context;

import com.jeu.hastequest.view.games.QuizzView;

public class Quizz extends Game{
    public Quizz(Context context) {
        super(new QuizzView(context), 30, "Quizz");
    }

    @Override
    public int computeMaxTime(int difficulty) {
        if(difficulty > 25){
            // TODO trouver une fonction log de difficultée
            return 3;
        }else{
            return maxTime - difficulty;
        }
    }
}
