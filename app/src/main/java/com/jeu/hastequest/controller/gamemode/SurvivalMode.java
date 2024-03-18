package com.jeu.hastequest.controller.gamemode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;
import com.jeu.hastequest.model.gamemode.SurvivalModeModel;

public class SurvivalMode extends GameMode{
    public ImageButton homeButton;
    public Button playButton;
    public Button rulesButton;
    @Override
    public void setGameModel() {
        this.gameModeModel = new SurvivalModeModel();
    }

    public SurvivalMode(){
        super();
        setGameModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if(v.getId() == R.id.boutonRegles)
                displayRules();
            if(v.getId() == R.id.boutonJouer)
                handlePlayButton();
        };

        if(extras != null){
            getGameModeModel().currentScore.score = extras.getInt("score");
            getGameModeModel().lives = extras.getInt("lives");
            getGameModeModel().difficulty = extras.getInt("difficulty");
            handlePlayButton();
        }else{
            setContentView(R.layout.survivalmenu);

            this.rulesButton = findViewById(R.id.boutonRegles);
            this.homeButton = findViewById(R.id.boutonHome);
            this.playButton = findViewById(R.id.boutonJouer);

            rulesButton.setOnClickListener(listener);
            homeButton.setOnClickListener(listener);
            playButton.setOnClickListener(listener);
        }
    }

    public void handlePlayButton(){
        setContentView(R.layout.start_menu);
        Button startButton = findViewById(R.id.boutonJouer);
        ImageButton homeButton = findViewById(R.id.boutonHome);
        ImageView gameImage = findViewById(R.id.imageJeu);
        TextView score = findViewById(R.id.score);
        ImageView heartImage1 = findViewById(R.id.heart1);
        ImageView heartImage2 = findViewById(R.id.heart2);
        ImageView heartImage3 = findViewById(R.id.heart3);

        // On passe au jeu avec les paramètres du mode de jeu qui seront à repasser
        Intent intent = new Intent(this,  this.getGamemodeModel().selectedGame.getClass());
        Bundle extras = new Bundle();
        extras.putInt("score", getGameModeModel().currentScore.score);
        extras.putInt("lives", getGameModeModel().lives);
        extras.putInt("difficulty", getGameModeModel().difficulty);
        extras.putBoolean("survival", true);
        intent.putExtras(extras);
        startButton.setOnClickListener(paramInutile -> startActivity(intent));

        gameImage.setImageResource(this.getGamemodeModel().selectedGame.gameImage);
        if(getGameModeModel().lives<3) {
            heartImage3.setVisibility(View.INVISIBLE);
            if(getGameModeModel().lives<2) {
                heartImage2.setVisibility(View.INVISIBLE);
                if(getGameModeModel().lives < 1) {
                    heartImage1.setVisibility(View.INVISIBLE);
                    if(getGameModeModel().lives <= 0)
                        // TODO FAIRE UN POP UP POUR DIRE QUE C'EST FINI
                        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        setContentView(R.layout.scoremenu);
                        //TextView scoreText = findViewById(R.id.score);
                        //bouton home
                        ImageButton homeScoreButton = findViewById(R.id.boutonHome);
                        homeScoreButton.setOnClickListener(paramInutile -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
                        //bouton réessayer
                        Intent intentRetry =  new Intent(getApplicationContext(), SurvivalMode.class);
                        Button startScoreButton = findViewById(R.id.boutonJouer);
                        startScoreButton.setOnClickListener(paramInutile -> startActivity(intentRetry));
                }
            }
        }
        score.setText(String.format("Score actuel : " + this.getGamemodeModel().currentScore.score));
        homeButton.setOnClickListener(paramInutile -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }

    public void displayRules(){
        setContentView(R.layout.rules_menu);
        @SuppressLint("CutPasteId") Button playBut = findViewById(R.id.boutonJouer);
        ImageButton closeRulesBut = findViewById(R.id.fermerRegles);
        playBut.setOnClickListener(jouer());
        closeRulesBut.setOnClickListener(paramInutile -> startActivity(new Intent(getApplicationContext(), SurvivalMode.class)));
        TextView rules = findViewById(R.id.regles);
        rules.setText("Le mode survie est un mode de jeu où vous devez répondre à un maximum de questions en un minimum de temps.\n\n Vous avez 3 vies, chaque mauvaise réponse vous enlève une vie. Le jeu se termine lorsque vous n'avez plus de vies.\n\n Votre score est le nombre de questions auxquelles vous avez répondu correctement. Bonne chance !");
    }

    public SurvivalModeModel getGamemodeModel(){
        return (SurvivalModeModel) this.gameModeModel;
    }

    public View.OnClickListener jouer(){

        return null;
    }

    public SurvivalModeModel getGameModeModel(){
        return (SurvivalModeModel) this.gameModeModel;
    }
}
