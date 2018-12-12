package com.example.govert.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<HighScore> {
    private List<HighScore> highScores;

    public HighscoreAdapter(Context context, int resource, List<HighScore> items) {
        super(context, resource, items);
        highScores = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.highscore_row, parent,
                    false);
        }

        // get currentItem
        HighScore currentItem = highScores.get(position);

        // set ranking for item
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore o1, HighScore o2) {
                return o1.getScore() - o2.getScore();
            }
        });

        // set nickName for item
        TextView nickNameTextView = (TextView) listItem.findViewById(R.id.nickNameTextView);
        nickNameTextView.setText(currentItem.getNickName());

        // set score for item
        TextView scoreTextView = (TextView) listItem.findViewById(R.id.scoreTextView);
        scoreTextView.setText(currentItem.getScore());

        return listItem;
    }
}
