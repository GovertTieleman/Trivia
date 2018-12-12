package com.example.govert.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String difficulty;
    private TextView nickNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get input field
        nickNameInput = (EditText) findViewById(R.id.nicknameEditText);

        // create difficulty options
        String[] difficulties = new String[]{"easy", "medium", "hard"};

        // create spinner
        Spinner spinner = (Spinner) findViewById(R.id.chooseDifficulty);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        // set listener for spinner
        spinner.setOnItemSelectedListener(this);
    }

    /** Open QuestionsActivity with intent */
    public void playClicked(View view) {
        // get nickName
        String nickName = nickNameInput.getText().toString();

        if (nickName.equals("")) {
            Toast.makeText(this, "Please enter your nickname", Toast.LENGTH_LONG).show();
        }
        else {
            // make intent
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putExtra("nickName", nickName);
            intent.putExtra("difficulty", difficulty);

            // start GetWords with intent
            startActivity(intent);
        }
    }

    /** Open HighScoreActivity */
    public void scoreClicked(View view) {
        startActivity(new Intent(MainActivity.this, HighScoreActivity.class));
    }

    /** Set difficulty based on user selection */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 0:
                difficulty = "easy";
                break;
            case 1:
                difficulty = "medium";
                break;
            case 2:
                difficulty = "hard";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    // auto generated method stub
    }
}
