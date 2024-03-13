package com.jeu.hastequest.controller.games;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.jeu.hastequest.R;
import com.jeu.hastequest.model.games.QuizModel;

public class Quiz extends Game{

    public Quiz(){
        super(new QuizModel());
        setGameImage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
    }

    @Override
    public void setGameImage() {
        this.gameImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.quiz);

    }
}
