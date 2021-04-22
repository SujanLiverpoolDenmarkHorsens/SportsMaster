package com.example.sportsmaster;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.Button;

import com.example.sportsmaster.vm.QuestionRepository;
import com.example.sportsmaster.vm.QuestionViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Core.Question;

public class MainActivity extends AppCompatActivity
{
    private QuestionRepository questionRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionRepository = new QuestionRepository(this.getApplication());
        int count = questionRepository.getQuestionCount();

        if(count == 0) {
            FetchData fetchData = new FetchData(this.getApplication());
            fetchData.execute();
        }
    }

    public void startQuiz (View view)
    {
        Intent intent = new Intent (this, QuizActivity.class);
        startActivity (intent);
    }

    public void startFlashCards(View view)
    {
        Intent intent = new Intent (this, FlashcardActivity.class);
        startActivity (intent);
    }

    private class FetchData extends AsyncTask<Void, Void, Void> {
        String data;
        ApiResultHandler apiResultHandler;

        public FetchData(Application application) {
            apiResultHandler = new ApiResultHandler(application);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            URL url = null;
            try {
                url = new URL("https://opentdb.com/api.php?amount=20&difficulty=easy&type=multiple");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder output = new StringBuilder();
            if(inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = null;
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(line != null)
                {
                    output.append(line);
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            data = output.toString();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            apiResultHandler.parseData(data);
            apiResultHandler.writeToDB();
        }
    }


}
