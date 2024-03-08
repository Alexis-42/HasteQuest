package com.jeu.hastequest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jeu.hastequest.controller.Controller;


public class View extends Fragment {
    protected View(){
        controller = Controller.getController(this);
    }
    Controller controller;
}