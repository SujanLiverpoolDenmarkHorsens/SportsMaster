package com.example.sportsmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{

    String[] q, a;
    Context context;

    public QuestionAdapter(Context ct, String[] question, String[] answer) {
        this.q = question;
        this.a = answer;
        this.context = ct;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.question_row, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(q[position]);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, a[position], Toast.LENGTH_LONG).show();

                if(holder.isAnswerShowing()) {
                    holder.myText1.setText(q[position]);
                } else {
                    holder.myText1.setText(a[position]);
                }
                holder.switchAnswerState();
            }
        };
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return q.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        boolean answerShowing = false;
        TextView myText1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.fcText);
        }

        public void switchAnswerState() {
            answerShowing = !answerShowing;
        }

        public boolean isAnswerShowing() {
            return answerShowing;
        }
    }
}
