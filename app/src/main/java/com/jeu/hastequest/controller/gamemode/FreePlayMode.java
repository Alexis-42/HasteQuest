package com.jeu.hastequest.controller.gamemode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.gamemode.FreePlayModeModel;

public class FreePlayMode extends GameMode{
    public ImageButton homeButton;
    public ImageButton prevButton ;
    public ImageButton nextButton;
    public Button playButton;
    public Button rulesButton;
    public ImageView gameImage;

    public FreePlayMode(){
        super();
        setGameModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_play);

        this.homeButton = findViewById(R.id.boutonHome);
        this.nextButton = findViewById(R.id.boutonNextJeu);
        this.prevButton = findViewById(R.id.boutonPreviousJeu);
        this.playButton = findViewById(R.id.boutonJouer);
        this.rulesButton = findViewById(R.id.boutonRegles);
        this.gameImage = findViewById(R.id.imageJeu);



        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if(v.getId() == R.id.boutonNextJeu)
                nextGame();
            if(v.getId() == R.id.boutonPreviousJeu)
                previousGame();
            if(v.getId() == R.id.boutonJouer) {
                Intent intent = new Intent(this,  getGameModeModel().selectedGame.getClass());
                Bundle extras = new Bundle();
                extras.putBoolean("survival", false);
                intent.putExtras(extras);
                startActivity(intent);
            }
            if(v.getId() == R.id.boutonRegles) {
                displayRules();
            }


        };

        homeButton.setOnClickListener(listener);
        prevButton.setOnClickListener(listener);
        nextButton.setOnClickListener(listener);
        playButton.setOnClickListener(listener);
        rulesButton.setOnClickListener(listener);

        actualizeGameImage();
    }

    public void displayRules(){
        Game jeu = getGameModeModel().selectedGame;
        setContentView(R.layout.rules_menu);
        @SuppressLint("CutPasteId") Button playBut = findViewById(R.id.boutonJouer);
        ImageButton closeRulesBut = findViewById(R.id.fermerRegles);
        playBut.setOnClickListener(paramInutile -> startActivity(new Intent(getApplicationContext(), jeu.getClass())));
        closeRulesBut.setOnClickListener(paramInutile -> startActivity(new Intent(getApplicationContext(), FreePlayMode.class)));
        TextView rules = findViewById(R.id.regles);
        rules.setText(getGameModeModel().selectedGame.gameModel.rules);
    }

    public void actualizeGameImage(){
        gameImage.setImageResource(getGameModeModel().selectedGame.gameImage);
    }

    public void previousGame(){
        this.getGameModeModel().selectedGame = getGameModeModel().selectPreviousGame();
        actualizeGameImage();
    }

    public void nextGame(){
        this.getGameModeModel().selectedGame = getGameModeModel().selectNextGame();
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
