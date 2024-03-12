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


public abstract class View extends android.view.View {
    private final Context context;


    protected View(Context context){
        super(context);
        this.context = context;
        controller = Controller.getController(this);
    }
    Controller controller;

    public abstract android.view.View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    );

    protected void onDestroyView() {

    }
}