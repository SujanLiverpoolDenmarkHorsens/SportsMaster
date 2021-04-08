package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
        @Query("SELECT * FROM Question")
        List<Question> getAll();

        @Query("SELECT * FROM Question WHERE id = :id")
        List<Question> getQuestionById(int id);

        @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT :amount")
        List<Question> getRandomQuestions(int amount);

        @Insert
        void insertAll(Question... questions);
}
