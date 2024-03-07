package com.jeu.hastequest.model;

import com.jeu.hastequest.controller.Controller;

public class Model {
    private static Model model;
    public Controller controller;

    private Model(Controller controll){
        controller = controll;
    }

    public static Model getModel(Controller controller){
        if(model == null){
            model = new Model(controller);
        }
        return model;
    }
}
