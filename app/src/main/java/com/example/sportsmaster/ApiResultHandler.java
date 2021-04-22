package com.example.sportsmaster;

import android.app.Application;

import com.example.sportsmaster.vm.QuestionRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.Question;

public class ApiResultHandler {

    ArrayList<Question> questions = new ArrayList<>();
    QuestionRepository repo;

    public ApiResultHandler(Application application) {
        repo = new QuestionRepository(application);
    }

    public void parseData(String data)
    {
        JSONObject wholeJSON = null;
        try {
            wholeJSON = new JSONObject(data);
        } catch (JSONException e) {
        }
        try {
            JSONArray tempArray = wholeJSON.getJSONArray("results");

            for(int i = 0; i < tempArray.length(); i++)
            {
                Question temp = new Question();
                JSONObject jo = null;
                try {
                    jo = (JSONObject) tempArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    temp.question = (String) jo.getString("question");
                    temp.correct = jo.getString("correct_answer");
                    JSONArray incorrectAnswers = (JSONArray) jo.get("incorrect_answers");
                    temp.answer1 = (String) incorrectAnswers.get(0);
                    temp.answer2 = (String) incorrectAnswers.get(1);
                    temp.answer3 = (String) incorrectAnswers.get(2);
                    temp.answer4 = temp.correct;
                    questions.add(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
        }catch(NullPointerException e)
        {

        }

    }

    public void writeToDB() {
        repo.insertAll(questions.toArray(new Question[0]));
    }
}
