package com.jeu.hastequest.controller.games;

import com.jeu.hastequest.view.games.QuizzView;

public class Quizz extends Game{
    public Quizz() {
        super(new QuizzView(), 30, "Quizz");
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
