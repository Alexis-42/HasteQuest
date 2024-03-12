package com.jeu.hastequest.controller.gamemode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.jeu.hastequest.MainActivity;
import com.jeu.hastequest.R;

public class FreePlayMode extends GameMode{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_play);

        ImageButton homeButton = findViewById(R.id.boutonHome);

        android.view.View.OnClickListener listener = v -> {
            if(v.getId() == R.id.boutonHome)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
        };

        homeButton.setOnClickListener(listener);
    }
}
