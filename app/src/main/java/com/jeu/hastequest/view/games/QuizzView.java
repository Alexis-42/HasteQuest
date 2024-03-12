package com.jeu.hastequest.view.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.jeu.hastequest.R;
import com.jeu.hastequest.databinding.FragmentFirstBinding;
import com.jeu.hastequest.view.View;

public class QuizzView extends View {
    public QuizzView(){
        super();
    }

    private FragmentFirstBinding binding;

    @Override
    public android.view.View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
