package com.jeu.hastequest;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.controller.Controller;


public class View extends AppCompatActivity {
    private View(){
        controller = Controller.getController(this);
    }
    Controller controller;
}