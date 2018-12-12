package com.example.govert.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity implements HighScoreRequest.Callback {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // get listView
        listView = (ListView) findViewById(R.id.highscores);

        // get highScores
        HighScoreRequest x = new HighScoreRequest(this);
        x.getHighScores(this);
    }

    @Override
    public void gotHighScores(ArrayList<HighScore> highScores) {
        // set adapter
        listView.setAdapter(new HighscoreAdapter(this, 0, highScores));
    }

    @Override
    public void gotHighScoresError(String message) {
        Log.d("gotHighScoresError", message);
    }
}
