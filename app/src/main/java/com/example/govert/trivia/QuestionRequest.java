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
import java.util.List;

public class QuestionRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public QuestionRequest(Context context) {
        this.context = context;
    }

    void getQuestions(Callback activity, String difficulty) {
        // set activity
        this.activity = activity;

        // create RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSONObjectRequest
        String url = "https://opentdb.com/api.php?amount=10&difficulty=" + difficulty;
        queue.add(new JsonObjectRequest(url, null, this, this));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // get JSONArray
        try {
            JSONArray array = response.getJSONArray("results");

            // initialize ArrayList of questions
            ArrayList<Question> questions = new ArrayList<>();

            // iterate over questions, adding them to the ArrayList
            for (int i = 0; i < array.length(); i++) {
                // get object
                JSONObject object = array.getJSONObject(i);

                // get values
                String category = object.getString("category");
                String type = object.getString("type");
                String difficulty = object.getString("difficulty");
                String question = object.getString("question");
                String correct = object.getString("correct_answer");
                JSONArray jsonArray = (JSONArray) object.get("incorrect_answers");

                // make list of incorrect answers
                List<String> incorrect = new ArrayList<>();

                for (int j = 0; j < jsonArray.length(); j++) {
                    incorrect.add(jsonArray.getString(j));
                }

                // add to questions
                Question current = new Question(category, type, difficulty, question, correct, incorrect);
                questions.add(current);
            }

            // perform Callback to activity
            activity.gotQuestions(questions);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}