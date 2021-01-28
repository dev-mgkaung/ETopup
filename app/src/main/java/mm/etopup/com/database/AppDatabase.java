package mm.etopup.com.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import mm.etopup.com.database.daos.UserDao;
import mm.etopup.com.database.entity.UserEntity;

@Database(entities = {UserEntity.class},
        version = 1 , exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "ELoad.DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();
}