package com.jeu.hastequest.controller.gamemode;

import static android.view.animation.AnimationUtils.loadAnimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.games.Game;
import com.jeu.hastequest.model.gamemode.FreePlayModeModel;

public class FreePlayMode extends GameMode{
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    public TextView gameModeDifficulty;
    public ImageButton homeButton;
    public Button playButton;
    public Button rulesButton;
    public ImageButton diffUpButton;
    public ImageButton diffDownButton;
    public ImageView gameImage;
    public ImageView prevGameImage;
    public ImageView nextGameImage;

    public FreePlayMode(){
        super();
        setGameModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_play);

        this.homeButton = findViewById(R.id.boutonHome);
        this.playButton = findViewById(R.id.boutonJouer);
        this.rulesButton = findViewById(R.id.boutonRegles);
        this.gameImage = findViewById(R.id.imageJeu);
        this.diffDownButton = findViewById(R.id.boutonDiffDown);
        this.diffUpButton = findViewById(R.id.boutonDiffUp);
        this.gameModeDifficulty = findViewById(R.id.gameModeDifficulty);
        this.nextGameImage = findViewById(R.id.imageJeuNext);
        this.prevGameImage = findViewById(R.id.imageJeuPrev);



        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if(v.getId() == R.id.boutonJouer) {
                Intent intent = new Intent(this,  getGameModeModel().selectedGame.getClass());
                Bundle extras = new Bundle();
                extras.putInt("difficulty", getGameModeModel().difficulty);
                extras.putBoolean("survival", false);
                intent.putExtras(extras);
                startActivity(intent);
            }
            if(v.getId() == R.id.boutonRegles) {
                displayRules();
            }
            if(v.getId() == R.id.boutonDiffDown) {
                if(getGameModeModel().difficulty > 1) {
                    getGameModeModel().difficulty--;
                    updateDifficulty();
                }
            }
            if(v.getId() == R.id.boutonDiffUp) {
                if(getGameModeModel().difficulty < 99) {
                    getGameModeModel().difficulty++;
                    updateDifficulty();
                }
            }


        };

        homeButton.setOnClickListener(listener);
        playButton.setOnClickListener(listener);
        rulesButton.setOnClickListener(listener);
        diffDownButton.setOnClickListener(listener);
        diffUpButton.setOnClickListener(listener);

        actualizeGameImage();
        updateDifficulty();
    }

    private void updateDifficulty() {
        this.gameModeDifficulty.setText("Difficulté : " + getGameModeModel().difficulty);
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
        nextGameImage.setImageResource(getGameModeModel().selectNextGame().gameImage);
        prevGameImage.setImageResource(getGameModeModel().selectPreviousGame().gameImage);
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

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        gameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_right));
                        nextGameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_right));
                        prevGameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_right));
                        previousGame();
                    }

                    // Right to left swipe action
                    else
                    {
                        gameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_left));
                        nextGameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_left));
                        prevGameImage.startAnimation(loadAnimation(getApplicationContext(),R.anim.slide_left));
                        nextGame();
                    }

                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
