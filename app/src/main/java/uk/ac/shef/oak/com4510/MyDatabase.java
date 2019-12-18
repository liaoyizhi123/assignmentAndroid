package uk.ac.shef.oak.com4510;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import uk.ac.shef.oak.com4510.dao.ImageDao;
import uk.ac.shef.oak.com4510.dao.PathDao;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;

@Database(entities = {Image.class, Path.class},version = 9,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();
    public abstract PathDao pathDao();

    private static volatile MyDatabase INSTANCE;

    //static method that return the instance of DataBase
    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "MyDatabase")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // do any init operation about any initialisation here
        }
    };

}
