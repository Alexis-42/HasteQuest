package com.jeu.hastequest.model.gamemode;

import android.content.Context;
import android.content.SharedPreferences;

import com.jeu.hastequest.controller.gamemode.SurvivalMode;
import com.jeu.hastequest.controller.games.Game;

import java.util.Arrays;
import java.util.Collections;

public class SurvivalModeModel extends GameModeModel {
    public static final String GAME_PREFS = "SaveFile";
    public Integer[] scores = {};
    public int lives;
    public Game selectedGame;
    public SurvivalModeModel() {
        super();
        this.selectedGame = getRandomGame();
        this.lives = 3;
    }

    public Game getRandomGame(){
        return games[getRandomNumber(games.length)];
    }


    public void gameLoadScore(SurvivalMode gm){
        SharedPreferences scorePrefs = gm.getApplicationContext().getSharedPreferences(GAME_PREFS, Context.MODE_PRIVATE);
        if(scorePrefs.contains("scores")){
            String[] savedScores = scorePrefs.getString("scores", "").split("\n");
            for (String score : savedScores) {
                if(isNumeric(score)){
                    if (scores.length == 0) {
                        scores = Arrays.copyOf(scores, 1);
                        scores = new Integer[]{Integer.parseInt(score)};
                    } else {
                        scores = Arrays.copyOf(scores, scores.length + 1);
                        scores[scores.length - 1] = Integer.parseInt(score);
                    }
                }
            }
        }
        Arrays.sort(scores, Collections.reverseOrder());
        gm.updateScoreText();
    }

    public void gameSaveScore(SurvivalMode gm, int score) {
        SharedPreferences scorePrefs = gm.getSharedPreferences(GAME_PREFS, 0);
        SharedPreferences.Editor scoreEdit = scorePrefs.edit();
        String scoresString = score+"\n";
        if(scorePrefs.contains("scores")){
            scoresString = scoresString + scorePrefs.getString("scores", "");
        }
        scoreEdit.putString("scores", scoresString);
        scoreEdit.apply();
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
