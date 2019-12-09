package oak.shef.ac.uk.assignment.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import oak.shef.ac.uk.assignment.entities.Path;

@Dao
public interface PathDao {

    @Insert
    void insert(Path path);

    @Delete
    void deleteImage(Path path);

    @Query("select * from path")
    LiveData<List<Path>> findAll();

    @Query("select * from path where id=:id")
    LiveData<List<Path>> findById(int id);



}
