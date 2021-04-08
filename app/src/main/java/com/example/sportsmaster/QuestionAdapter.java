package com.example.sportsmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import database.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private ArrayList<Question> questions;

    public QuestionAdapter(ArrayList<Question> questions)
    {
        this.questions = questions;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.question.setText(questions.get(i));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.questionText);
        }
    }
}

