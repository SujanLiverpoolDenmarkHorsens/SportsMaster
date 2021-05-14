package com.example.sportsmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sportsmaster.vm.QuestionRepository;
import com.example.sportsmaster.vm.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

import Core.Question;


public class QuizActivity extends AppCompatActivity {
    private ArrayList<Core.Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private int score = 0;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        questionViewModel.getRandomQuestions(10).observe(this, new Observer<List<database.Question>>() {
            @Override
            public void onChanged(List<database.Question> questionDBEntities) {
                for (database.Question question : questionDBEntities) {
                    questions.add(new Question(question));
                }
                addTextToView();
            }
        });

    }

    public void answerClicked(View view) {
        Button buttonClicked = (Button) view;
        if(currentQuestion+1 == questions.size()) {
            Toast.makeText(getApplicationContext(), "You got all the points. "+ score + " to be exact", Toast.LENGTH_LONG).show();
            gameOver();
        }

        else if(buttonClicked.getText().toString().equals(questions.get(currentQuestion).getCorrectAnswer())) {
            currentQuestion++;
            score++;
            addTextToView();
        } else {
            Toast.makeText(getApplicationContext(), "You got "+ score + " / 10 points", Toast.LENGTH_LONG).show();
            gameOver();
        }
    }

    private void addTextToView() {
        TextView questionTextView = findViewById(R.id.questionId);
        questionTextView.setText(questions.get(currentQuestion).getQuestion());

        Button answer1 = findViewById(R.id.answer1Id);
        answer1.setText(questions.get(currentQuestion).getAnswers().get(0));

        Button answer2 = findViewById(R.id.answer2Id);
        answer2.setText(questions.get(currentQuestion).getAnswers().get(1));

        Button answer3 = findViewById(R.id.answer3Id);
        answer3.setText(questions.get(currentQuestion).getAnswers().get(2));

        Button answer4 = findViewById(R.id.answer4Id);
        answer4.setText(questions.get(currentQuestion).getAnswers().get(3));
    }

    private void gameOver()
    {
        Intent intent = new Intent(QuizActivity.this, ScoreEntry.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}