package Core;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;

    public Question(String question, ArrayList<String> answers, String correctAnswer) {
        Collections.shuffle(answers);
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public Question (database.Question ques){
        this.question = ques.question;
        this.answers = new ArrayList<String>();
        this.answers.add(ques.answer1);
        this.answers.add(ques.answer2);
        this.answers.add(ques.answer3);
        this.answers.add(ques.answer4);

        this.correctAnswer = ques.correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
