package com.jeu.hastequest.controller.games;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jeu.hastequest.R;
import com.jeu.hastequest.controller.gamemode.FreePlayMode;
import com.jeu.hastequest.controller.gamemode.SurvivalMode;
import com.jeu.hastequest.model.games.QuizModel;

public class Quiz extends Game {
    public boolean finished = false;
    public Button anwsersButtonA;
    public Button anwsersButtonB;
    public Button anwsersButtonC;
    public Button anwsersButtonD;
    public TextView question;
    public TextView chronometer;

    public Quiz() {
        super(new QuizModel());
        setGameImage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        boolean isSurvival = extras.getBoolean("survival");
        int lives;
        int score;
        int difficulty;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        this.question = findViewById(R.id.question);
        this.anwsersButtonA = findViewById(R.id.ansA);
        this.anwsersButtonB = findViewById(R.id.ansB);
        this.anwsersButtonC = findViewById(R.id.ansC);
        this.anwsersButtonD = findViewById(R.id.ansD);
        this.chronometer = findViewById(R.id.chrono);

        if (isSurvival) {
            lives = extras.getInt("lives");
            score = extras.getInt("score");
            difficulty = extras.getInt("difficulty");
        } else {
            lives = 0;
            difficulty = 0;
            score = 0;
        }

        android.view.View.OnClickListener listener = v -> handleAnwser(((Button) v).getText().toString(), isSurvival, score, difficulty, lives);

        this.anwsersButtonA.setOnClickListener(listener);
        this.anwsersButtonB.setOnClickListener(listener);
        this.anwsersButtonC.setOnClickListener(listener);
        this.anwsersButtonD.setOnClickListener(listener);

        loadQuestion();
        getQuizModel().init(this, isSurvival, score, lives, difficulty);
    }

    public void handleAnwser(String stringPressed, boolean isSurvival, int score, int difficulty, int lives) {
        handleColorChange(this.anwsersButtonA);
        handleColorChange(this.anwsersButtonB);
        handleColorChange(this.anwsersButtonC);
        handleColorChange(this.anwsersButtonD);
        Log.i("handleanswerquiz", "quiz répondu");
        this.getQuizModel().handlerChrono.removeCallbacks(this.getQuizModel().runnableChrono);
        if (isSurvival) {
            if (getQuizModel().isCorrect(stringPressed)) {
                score += 1;
                difficulty += 1;
            } else {
                lives -= 1;
            }

            Handler handler = new Handler();
            int finalScore = score;
            int finalLives = lives;
            int finalDifficulty = difficulty;
            handler.postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), SurvivalMode.class);
                Bundle extras = new Bundle();
                extras.putInt("score", finalScore);
                extras.putInt("lives", finalLives);
                extras.putInt("difficulty", finalDifficulty);
                extras.putBoolean("survival", true);
                intent.putExtras(extras);
                startActivity(intent);
            }, 1500);

        } else {
            // TODO : faire qque chose si faux en mode freeplay
            Intent intent = new Intent(getApplicationContext(), FreePlayMode.class);
            Bundle extras = new Bundle();
            extras.putBoolean("survival", false);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    public void handleColorChange(Button button) {
        if (getQuizModel().isCorrect(button.getText().toString())) {
            button.setBackgroundColor(Color.parseColor("#00FF00"));
        } else {
            button.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        button.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    void setGameImage() {
        this.gameImage = R.drawable.quiz;
    }

    public void loadQuestion() {
        this.question.setText(QuizModel.question[getQuizModel().currentQuestionIndex]);
        this.anwsersButtonA.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][0]);
        this.anwsersButtonB.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][1]);
        this.anwsersButtonC.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][2]);
        this.anwsersButtonD.setText(QuizModel.choices[getQuizModel().currentQuestionIndex][3]);
    }

    public QuizModel getQuizModel() {
        return (QuizModel) this.gameModel;
    }

    public void updateTime(int seconds) {
        this.chronometer.setText(String.valueOf(seconds));
    }

    public void checkWin(boolean isSurvival, int score, int lives, int difficulty) {
        if (getQuizModel().seconds <= 0) {
            this.getQuizModel().handlerChrono.removeCallbacks(this.getQuizModel().runnableChrono);
            if (isSurvival) {
                this.finished = true;
                Intent intent = new Intent(getApplicationContext(), SurvivalMode.class);
                Bundle extras = new Bundle();
                extras.putInt("score", score);
                extras.putInt("lives", lives - 1);
                extras.putInt("difficulty", difficulty);
                extras.putBoolean("survival", true);
                intent.putExtras(extras);
                startActivity(intent);
            } else {
                this.finished = true;
                Intent intent = new Intent(getApplicationContext(), FreePlayMode.class);
                Bundle extras = new Bundle();
                extras.putBoolean("survival", false);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}