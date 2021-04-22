package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}
