package com.example.govert.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighScoreRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    public interface Callback {
        void gotHighScores(ArrayList<HighScore> questions);
        void gotHighScoresError(String message);
    }

    public HighScoreRequest(Context context) {
        this.context = context;
    }

    void getHighScores(Callback activity) {
        // set activity
        this.activity = activity;

        // create RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSONObjectRequest
        queue.add(new JsonObjectRequest("https://ide50-ertzor.cs50.io:8080/highscores",
                null, this, this));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotHighScoresError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            // get JSONArray
            JSONArray array  = new JSONArray(response);

            // initialize ArrayList of highScores
            ArrayList<HighScore> highScores = new ArrayList<>();

            // iterate over questions, adding them to the ArrayList
            for (int i = 0; i < array.length(); i++) {
                // get object
                JSONObject object = array.getJSONObject(i);

                // get values
                String nickName = object.getString("nickname");
                Integer score = object.getInt("score");

                // add to highScores
                HighScore current = new HighScore(nickName, score);
                highScores.add(current);
            }

            // perform Callback to activity
            activity.gotHighScores(highScores);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
