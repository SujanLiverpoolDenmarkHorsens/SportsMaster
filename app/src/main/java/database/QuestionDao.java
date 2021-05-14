package database;

import androidx.lifecycle.LiveData;
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
        LiveData<List<Question>> getQuestionById(int id);

        @Query("SELECT * FROM Question LIMIT :amount")
        LiveData<List<Question>> getRandomQuestions(int amount);

        @Insert
        void insertAll(Question... questions);

        @Query("SELECT COUNT(*) FROM Question")
        int getQuestionCount();
}
