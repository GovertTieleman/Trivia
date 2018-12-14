package com.example.govert.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        // sort list by score rank
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore o1, HighScore o2) {
                return o2.getScore() - o1.getScore() ;
            }
        });
        
        // set adapter
        listView.setAdapter(new HighscoreAdapter(this, 0, highScores));
    }

    @Override
    public void gotHighScoresError(String message) {
        Log.d("gotHighScoresError", message);
    }
}
