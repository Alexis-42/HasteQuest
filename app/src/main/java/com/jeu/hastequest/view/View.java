package com.jeu.hastequest.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jeu.hastequest.controller.Controller;


public abstract class View extends AppCompatActivity {

    protected View(){
        super();
        controller = Controller.getController(this);
    }
    Controller controller;

}