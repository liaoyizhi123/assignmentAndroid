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

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // do any init operation about any initialisation here
        }
    };

}
