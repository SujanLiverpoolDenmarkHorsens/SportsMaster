package com.example.sportsmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sportsmaster.vm.QuestionRepository;
import com.example.sportsmaster.vm.QuestionViewModel;

import java.util.List;

import database.Question;

public class FlashcardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestionViewModel questionViewModel;
    QuestionRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionViewModel = new QuestionViewModel(this.getApplication());
        repo = new QuestionRepository(this.getApplication());
        setContentView(R.layout.activity_flashcard);

        recyclerView = findViewById(R.id.recyclerView);


        //question = new String[]{"Background young concurrent copying GC freed 19591(6407KB) AllocSpace objects, 0(0B) LOS objects, 79% free, 1569KB/7713KB, paused 12.355ms total 46.907ms", "W/ActivityThread: handleWindowVisibility: no activity for token android.os.BinderProxy@2d9321b"
        //                       , "W/ActivityThread: handleWindowVisibility: no activity for token android.os.BinderProxy@2d9321b", "W/ActivityThread: handleWindowVisibility: no activity for token android.os.BinderProxy@2d9321b"};
        //answer = new String[]{"1", "2", "3", "4"};

        //List<Question> questions =  questionViewModel.getAllQuestions().getValue();
        List<Question> questions = repo.getAllQuestions();
        String[] question = new String[questions.size()];
        String[] answer = new String[questions.size()];
        for(int i = 0; i< questions.size(); i++) {
            question[i] = questions.get(i).question;
            answer[i] = questions.get(i).correct;
        }

        QuestionAdapter questionAdapter = new QuestionAdapter(this, question, answer);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}