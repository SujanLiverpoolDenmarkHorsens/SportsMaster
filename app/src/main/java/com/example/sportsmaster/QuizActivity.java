package com.example.sportsmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import Core.Question;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ArrayList<String> answers = new ArrayList<>();
        answers.add("Aayush");
        answers.add("Sujan");
        answers.add("Suman");
        answers.add("Niyog");
        Question question = new Question("Who is the best football player of all time ?", answers, "Sujan");
        questions.add(question);
        addTextToView();
    }

    public void answerClicked(View view) {
    }

    private void addTextToView() {
        TextView questionTextView = findViewById(R.id.questionId);
        questionTextView.setText(questions.get(0).getQuestion());

        Button answer1 = findViewById(R.id.answer1Id);
        answer1.setText(questions.get(0).getAnswers().get(0));

        Button answer2 = findViewById(R.id.answer2Id);
        answer1.setText(questions.get(0).getAnswers().get(1));

        Button answer3 = findViewById(R.id.answer3Id);
        answer1.setText(questions.get(0).getAnswers().get(2));

        Button answer4 = findViewById(R.id.answer4Id);
        answer1.setText(questions.get(0).getAnswers().get(3));
    }
}