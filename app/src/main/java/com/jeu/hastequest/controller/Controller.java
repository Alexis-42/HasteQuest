package com.jeu.hastequest.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.jeu.hastequest.model.Model;

public class Controller extends AppCompatActivity {
    private static Controller controller;
    public Model model;

    protected Controller(){
        this.model = Model.getModel(this);
    }

    public static Controller getController(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }
}
