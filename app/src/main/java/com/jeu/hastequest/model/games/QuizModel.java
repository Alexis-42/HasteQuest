package com.jeu.hastequest.model.games;

public class QuizModel extends GameModel {
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
        super(30,
                "Quizz",
                "Trouve la réponse correcte dans un temps impartie");
        currentQuestionIndex = getRandomNumber(question.length );
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

    public boolean isCorrect(String answer){
        return answer.equals(correctAnswer[currentQuestionIndex]);
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }
}
