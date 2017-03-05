package com.example.kappa.assignment;

/**
 * Created by Kappa on 3/5/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kappa on 3/5/2017.
 */

public class PlaySelf extends Activity {
    private List<Question> questions;
    private List<String> answers = new ArrayList<String>();
    private String correctanswer, option1, option2;
    private Question CurrentQuestion;
    private int mScore = 0, CHANCE, mStreak = 0, streakFinal=0;
    private int prevHighScore, prevStreak;


    private TextView ViewScore;
    private TextView ViewStreak;
    private TextView ViewChance;
    private TextView ViewQuestion;
    private Button opt1;
    private Button opt2;
    private Button opt3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playself);
        ViewStreak=(TextView) findViewById(R.id.streak);
        ViewChance = (TextView) findViewById(R.id.chance);
        ViewScore = (TextView) findViewById(R.id.score);
        ViewQuestion = (TextView) findViewById(R.id.question);
        opt1 = (Button) findViewById(R.id.choice1);
        opt2 = (Button) findViewById(R.id.choice2);
        opt3 = (Button) findViewById(R.id.choice3);
        final MediaPlayer COIN= MediaPlayer.create(this,R.raw.coins);

        DBHelper2 myDbHelper = new DBHelper2(getApplicationContext());
        myDbHelper = new DBHelper2(this);



        //open the database, get the questions and add them to the questions list
        questions = myDbHelper.getAllQuestions();



        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button answerbutton = ((Button) v);
                String ANS = answerbutton.getText().toString();
                if (ANS == correctanswer) {
                    mStreak = mStreak + 1;
                    calcScore();
                    updatescore(mScore);
                    updatestreak(mStreak);
                    compareStreak();
                    updatequestion();
                    COIN.start();
                    Toast.makeText(PlaySelf.this, (R.string.Correct), Toast.LENGTH_SHORT).show();
                } else {
                    mStreak = 0;
                    updatestreak(mStreak);
                    CHANCE--;
                    Toast.makeText(PlaySelf.this, (R.string.Incorrect), Toast.LENGTH_SHORT).show();
                    updatchance(CHANCE);
                    if (CHANCE <= 0) {
                        endGame();
                    }
                }
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button answerbutton = ((Button) v);
                String ANS = answerbutton.getText().toString();
                if (ANS == correctanswer) {
                    mStreak = mStreak + 1;
                    calcScore();
                    updatescore(mScore);
                    updatestreak(mStreak);
                    compareStreak();
                    updatequestion();
                    COIN.start();
                    Toast.makeText(PlaySelf.this, (R.string.Correct), Toast.LENGTH_SHORT).show();
                } else {
                    mStreak = 0;
                    updatestreak(mStreak);
                    CHANCE--;
                    Toast.makeText(PlaySelf.this, (R.string.Incorrect), Toast.LENGTH_SHORT).show();
                    updatchance(CHANCE);
                    if (CHANCE <= 0) {
                        endGame();
                    }
                }
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button answerbutton = ((Button) v);
                String ANS = answerbutton.getText().toString();
                if (ANS == correctanswer) {
                    mStreak = mStreak + 1;
                    calcScore();
                    updatescore(mScore);
                    updatestreak(mStreak);
                    compareStreak();
                    updatequestion();
                    COIN.start();
                    Toast.makeText(PlaySelf.this, (R.string.Correct), Toast.LENGTH_SHORT).show();
                } else {
                    mStreak = 0;
                    updatestreak(mStreak);
                    CHANCE--;
                    Toast.makeText(PlaySelf.this, (R.string.Incorrect), Toast.LENGTH_SHORT).show();
                    updatchance(CHANCE);
                    if (CHANCE <= 0) {
                        endGame();
                    }
                }
            }
        });

        LoadScore();
        LoadStreak();

        reset();


    }

    //reset score shuffle questions
    public void reset() {
        //reset score
        mScore = 0;
        ViewScore.setText(Integer.toString(mScore));

        //reset chance
        CHANCE = 5;
        ViewChance.setText(Integer.toString(CHANCE));

        //shuffle the question
        Collections.shuffle(questions);


        updatequestion();

    }

    private void updatequestion() {

        if (questions.size() > 0)
            CurrentQuestion = questions.remove(0);
        else {
            endGame();
            return;
        }


        ViewQuestion.setText(CurrentQuestion.getQuestion());


        // assign the results to values. These values will be used to check result
        correctanswer = CurrentQuestion.getAnswer();
        option1 = CurrentQuestion.getOption1();
        option2 = CurrentQuestion.getOption2();

        // clear list
        answers.clear();

        // add the answers to the list
        answers.add(correctanswer);
        answers.add(option1);
        answers.add(option2);

        // shuffle the answers
        Collections.shuffle(answers);

        // assign random answers to buttons
        opt1.setText(answers.remove(0));
        opt2.setText(answers.remove(0));
        opt3.setText(answers.remove(0));

    }

    private void calcScore() {
        if (mStreak < 3) {
            mScore = mScore + 100;
        } else if (mStreak == 3)
            mScore = mScore + 130;
        else if (mStreak == 4)
            mScore = mScore + 140;
        else if (mStreak == 5)
            mScore = mScore + 150;
        else if (mStreak == 6)
            mScore = mScore + 160;
        else if (mStreak == 7)
            mScore = mScore + 170;
        else if (mStreak == 8)
            mScore = mScore + 180;
        else if (mStreak == 9)
            mScore = mScore + 190;
        else
            mScore = mScore + 200;
    }

    private void updatescore(int points) {
        ViewScore.setText("" + mScore);
    }

    private void updatestreak(int astreak) {
        ViewStreak.setText("" + mStreak);
    }

    private void updatchance(int chances) {
        ViewChance.setText("" + CHANCE);
    }

    private void endGame() {
        //show end game alert dialog
        TextView dialogTitle = new TextView(this);
        dialogTitle.setText("Game Over!");
        dialogTitle.setGravity(Gravity.CENTER);
        dialogTitle.setTextSize(23);

        TextView dialogMessage = new TextView(this);
        dialogMessage.setText("Score: " + mScore + "\nLongest streak: " + mStreak);
        dialogMessage.setGravity(Gravity.CENTER);
        dialogMessage.setTextSize(19);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCustomTitle(dialogTitle);
        dialog.setView(dialogMessage);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            //restart activity
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        dialog.setNegativeButton("Return to Main Menu", new DialogInterface.OnClickListener() {
            //finish and return to main menu
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PlaySelf.this.finish();
            }
        });
        dialog.show();
    }

    //set highscore if activity is destroyed

    protected void onDestroy() {
        endGameScore();
        endGameStreak();
        super.onDestroy();
    }

    //save score using SharedPreferences
    public void SaveScore(String HighScore, int score2) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("HighScore", score2);
        editor.commit();
    }

    //load score using SharedPreferences
    public void LoadScore() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prevHighScore = prefs.getInt("HighScore", 0);
    }

    //save streak using SharedPreferences
    public void SaveStreak(String Streak, int streakFinal) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Streak", streakFinal);
        editor.commit();
    }

    //load streak using SharedPreferences
    public void LoadStreak() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prevStreak = prefs.getInt("Streak", 0);
    }

    //compare current streak and highest streak
    private void compareStreak() {
        if (mStreak > streakFinal) {
            streakFinal = mStreak;
        }
    }

    //compare session streak and previous longest streak
    private void endGameStreak() {
        if (streakFinal > prevStreak) {
            SaveStreak("Streak", streakFinal);
        }
    }

    //check for high score
    private void endGameScore() {
        if (mScore > prevHighScore) {
            SaveScore("HighScore", mScore);
        }
    }
}