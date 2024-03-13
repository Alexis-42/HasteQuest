package com.jeu.hastequest.controller.gamemode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.gamemode.FreePlayModeModel;

public class FreePlayMode extends GameMode{
    public Game selectedGame;
    public ImageButton homeButton;
    public ImageButton prevButton ;
    public ImageButton nextButton;
    public Button playButton;
    public ImageView gameImage;

    public FreePlayMode(){
        super();
        setGameModel();
        this.selectedGame = getGameModeModel().selectedGame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_play);

        this.homeButton = findViewById(R.id.boutonHome);
        this.prevButton = findViewById(R.id.boutonNextJeu);
        this.nextButton = findViewById(R.id.boutonPreviousJeu);
        this.playButton = findViewById(R.id.boutonJouer);
        this.gameImage = findViewById(R.id.imageJeu);

        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if(v.getId() == R.id.boutonNextJeu)
                nextGame();
            if(v.getId() == R.id.boutonPreviousJeu)
                previousGame();
            if(v.getId() == R.id.boutonJouer)
                startActivity(new Intent(getApplicationContext(), this.selectedGame.getClass()));

        };

        homeButton.setOnClickListener(listener);
        prevButton.setOnClickListener(listener);
        nextButton.setOnClickListener(listener);
        playButton.setOnClickListener(listener);

        actualizeGameImage();
    }

    public void actualizeGameImage(){
        gameImage.setImageResource(selectedGame.gameImage);
    }

    public void previousGame(){
        this.selectedGame = getGameModeModel().selectPreviousGame();
        actualizeGameImage();
    }

    public void nextGame(){
        this.selectedGame = getGameModeModel().selectNextGame();
        actualizeGameImage();
    }
    @Override
    public void setGameModel() {
        this.gameModeModel = new FreePlayModeModel();
    }

    public FreePlayModeModel getGameModeModel(){
        return (FreePlayModeModel) this.gameModeModel;
    }
}
