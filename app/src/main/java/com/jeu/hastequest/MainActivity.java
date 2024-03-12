package com.jeu.hastequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.view.gamemode.FreePlayModeView;
import com.jeu.hastequest.view.gamemode.SurvivalModeView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button fpButton = (Button) findViewById(R.id.boutonJeuLibre);
        Button sButton = (Button) findViewById(R.id.boutonSurvie);
        View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonJeuLibre)
                redirectFreePlay();
            if(v.getId() == R.id.boutonSurvie)
                redirectSurvival();
        };
        fpButton.setOnClickListener(listener);
        sButton.setOnClickListener(listener);
    }

    public void redirectSurvival(){
        Intent intent = new Intent(getApplicationContext(), SurvivalModeView.class);
        startActivity(intent);
    }

    public void redirectFreePlay(){
        Intent intent = new Intent(getApplicationContext(), FreePlayModeView.class);
        startActivity(intent);
    }

}
