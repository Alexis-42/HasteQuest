package com.jeu.hastequest.controller.games;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.QuizModel;

public class Quiz extends Game{
    public Button anwsersButtonA;
    public Button anwsersButtonB;
    public Button anwsersButtonC;
    public Button anwsersButtonD;
    public TextView question;


    public Quiz(){
        super(new QuizModel());
        setGameImage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        this.question = findViewById(R.id.question);
        this.anwsersButtonA = findViewById(R.id.ansA);
        this.anwsersButtonB = findViewById(R.id.ansB);
        this.anwsersButtonC = findViewById(R.id.ansC);
        this.anwsersButtonD = findViewById(R.id.ansD);

        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.ansA);
            if(v.getId() == R.id.ansB);
            if(v.getId() == R.id.ansC);
            if(v.getId() == R.id.ansD);
        };

        this.anwsersButtonA.setOnClickListener(listener);
        this.anwsersButtonB.setOnClickListener(listener);
        this.anwsersButtonC.setOnClickListener(listener);
        this.anwsersButtonD.setOnClickListener(listener);

        loadQuestion();

    }

    @Override
    void setGameImage() {
        this.gameImage = R.drawable.quiz;
    }

    public void loadQuestion(){
        this.question.setText(QuizModel.question[getQuizModel().currentQuestionIndex]);
        this.anwsersButtonA.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][0]);
        this.anwsersButtonB.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][1]);
        this.anwsersButtonC.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][2]);
        this.anwsersButtonD.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][3]);

    }

    public QuizModel getQuizModel(){
        return (QuizModel) this.gameModel;
    }
}
