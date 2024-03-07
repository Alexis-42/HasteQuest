package com.jeu.hastequest.controller;

import com.jeu.hastequest.view.View;
import com.jeu.hastequest.model.Model;

public class Controller {
    private static Controller controller;
    public View view;
    public Model model;

    private Controller(View vue){
        this.view = vue;
        this.model = Model.getModel(this);
    }

    public static Controller getController(View vue){
        if(controller == null){
            controller = new Controller(vue);
        }
        return controller;
    }

    public Controller getController(){
        return controller;
    }
}
