package com.example.sportsmaster.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import database.Question;

public class QuestionViewModel extends AndroidViewModel{
    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestions;
    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new QuestionRepository(application);
        //allQuestions = repository.getAllQuestions();
    }
    public void insert(Question question) {
        repository.insert(question);
    }

    public List<Question> getAllQuestions() {
        return repository.getAllQuestions();
    }

    public LiveData<List<Question>> getRandomQuestions(int amount) {
        return repository.getRandomQuestions(amount);
    }

    public int getQuestionCount() {
        return repository.getQuestionCount();
    }
}
