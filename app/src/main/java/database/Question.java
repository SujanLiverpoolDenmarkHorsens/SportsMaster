package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "answer1")
    public String answer1;

    @ColumnInfo(name = "answer2")
    public String answer2;

    @ColumnInfo(name = "answer3")
    public String answer3;

    @ColumnInfo(name = "answer4")
    public String answer4;

    @ColumnInfo(name = "correct")
    public String correct;

}
