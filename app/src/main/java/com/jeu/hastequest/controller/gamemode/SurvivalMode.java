package com.jeu.hastequest.controller.gamemode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;
import com.jeu.hastequest.model.gamemode.SurvivalModeModel;

public class SurvivalMode extends GameMode{
    public ImageButton homeButton;
    public Button rulesButton;
    @Override
    public void setGameModel() {
        this.gameModeModel = new SurvivalModeModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survivalmenu);

        this.rulesButton = findViewById(R.id.boutonRegles);
        this.homeButton = findViewById(R.id.boutonHome);

        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            if(v.getId() == R.id.boutonRegles)
                displayRules();
        };

        rulesButton.setOnClickListener(listener);
        homeButton.setOnClickListener(listener);
    }

    public void displayRules(){
        setContentView(R.layout.rules_menu);
        @SuppressLint("CutPasteId") Button playBut = findViewById(R.id.boutonJouer);
        ImageButton closeRulesBut = findViewById(R.id.fermerRegles);
        playBut.setOnClickListener(jouer());
        closeRulesBut.setOnClickListener((android.view.View.OnClickListener)paramInutile -> startActivity(new Intent(getApplicationContext(), SurvivalMode.class)));
        TextView rules = (TextView)findViewById(R.id.regles);
        rules.setText("Le mode survie est un mode de jeu où vous devez répondre à un maximum de questions en un minimum de temps.\n\n Vous avez 3 vies, chaque mauvaise réponse vous enlève une vie. Le jeu se termine lorsque vous n'avez plus de vies.\n\n Votre score est le nombre de questions auxquelles vous avez répondu correctement. Bonne chance !");
    }

    public View.OnClickListener jouer(){

        return null;
    }
}
