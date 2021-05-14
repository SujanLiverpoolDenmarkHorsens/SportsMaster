package com.example.sportsmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreEntry extends AppCompatActivity {
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_entry);
        setScoreTextView();
    }

    private void setScoreTextView()
    {
        TextView scoreTextView = (TextView) findViewById(R.id.scoreText);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            score = extras.getInt("score");
        }

        scoreTextView.setText("You have scored " + score + " points. Enter your name below to save the score!");
    }


    public void saveToFirebase(View view)
    {
        EditText nameTextView = findViewById(R.id.scoreEntryView);
        String name = nameTextView.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("scores");

        Score scoreObj = new Score(name, score);
        String id = myRef.push().getKey();
        myRef.child(id).setValue(scoreObj);
        Toast.makeText(this, "Score Added", Toast.LENGTH_LONG).show();

        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }

}