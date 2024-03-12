package com.jeu.hastequest.model.games;

public class QuizModel extends GameModel {
    public QuizModel() {
        super(30,
                "Quizz",
                "Trouve la réponse correcte dans un temps impartie");
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
