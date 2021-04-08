package com.example.sportsmaster;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;

import database.AppDatabase;
import database.QuestionDao;
import database.Question;


public class FlashCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "test").build();
        QuestionDao questionDao = db.questionDao();

        Question question = new Question();
        question.answer1 = "Answer 1";
        question.answer2 = "Answer 2";
        question.answer3 = "Answer 3";
        question.answer4 = "Answer 4";
        question.correct = "Correct";
        question.question = "question";
        question.id = 123;
        questionDao.insertAll(question);
        // Retrieve questions from Database
        // Database adapter
        // populate recycler views with questions
        // onClick listener must reveal answers

    }


}