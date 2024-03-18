package com.jeu.hastequest.model.games;

import android.os.Handler;
import android.util.Log;

import com.jeu.hastequest.controller.games.Quiz;

public class QuizModel extends GameModel {
    public int seconds;
    public Handler handlerChrono;
    public Runnable runnableChrono;
    public int currentQuestionIndex;
    public static String[] question ={
        "Léo le goat ?",
        "Combien de bonbons Carambar sont vendus chaque année en France ?",
        "Qui a fondé Microsoft ?",
    };

    public static String[][] choices ={
        {"non", "non", "oui", "non"},
        {"10 millions", "100 millions", "1 milliard", "2 milliards"},
        {"Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Jeff Bezos"},
    };
    public static String[] correctAnswer ={
        "oui",
        "1 milliard",
        "Bill Gates",
    };

    public QuizModel() {
        super(15,
                "Quizz",
                "Trouve la réponse correcte dans un temps impartie",
                0);
        currentQuestionIndex = getRandomNumber(question.length);
    }

    @Override
    public int computeMaxTime(int difficulty) {
        if(difficulty > 10){
            return 4;
        }else{
            return maxTime - difficulty;
        }
    }

    public boolean isCorrect(String answer){
        return answer.equals(correctAnswer[currentQuestionIndex]);
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }

    public void init(Quiz memory, boolean isSurvival, int score, int lives, int difficulty){
        this.seconds = computeMaxTime(difficulty);
        handlerChrono = new Handler();
        memory.updateTime(seconds);
        // Répète le runnable après un délai
        runnableChrono = new Runnable() {
            @Override
            public void run() {
                Log.i("Quiz", "quiz handler running");
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
