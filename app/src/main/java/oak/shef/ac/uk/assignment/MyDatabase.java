package oak.shef.ac.uk.assignment;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import oak.shef.ac.uk.assignment.dao.ImageDao;
import oak.shef.ac.uk.assignment.dao.PathDao;
import oak.shef.ac.uk.assignment.entities.Image;
import oak.shef.ac.uk.assignment.entities.Path;

@Database(entities = {Image.class, Path.class},version = 4,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();
    public abstract PathDao pathDao();

    private static volatile MyDatabase INSTANCE;

    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = android.arch.persistence.room.Room.databaseBuilder(context.getApplicationContext(),
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
