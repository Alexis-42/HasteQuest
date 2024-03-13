package com.jeu.hastequest.controller.games;

import android.os.Bundle;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.QuizModel;

public class Quiz extends Game{

    public Quiz(){
        super(new QuizModel());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
    }
}
