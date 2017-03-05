package com.example.kappa.assignment;

/**
 * Created by Kappa on 3/4/2017.
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;



/**
 * Created by Kappa on 2/18/2017.
 */

public class Highscore extends Activity{

    private TextView scoreView, streakView;
    private int prevHighScore, prevStreak;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.highscore);

        scoreView = (TextView) findViewById(R.id.highest);
        streakView = (TextView) findViewById(R.id.longeststreak);
        LoadScore();
        LoadStreak();

        scoreView.setText(Integer.toString(prevHighScore));
        streakView.setText(Integer.toString(prevStreak));
    }
    //sharedpreferences for managing highscore and longest streak
    public void SaveScore(String HighScore, int score2){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("HighScore", score2);
        editor.commit();
    }

    public void LoadScore(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prevHighScore = prefs.getInt("HighScore", 0);
    }

    public void SaveStreak(String Streak, int streakFinal){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Streak", streakFinal);
        editor.commit();
    }

    public void LoadStreak(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prevStreak = prefs.getInt("Streak", 0);
    }
}