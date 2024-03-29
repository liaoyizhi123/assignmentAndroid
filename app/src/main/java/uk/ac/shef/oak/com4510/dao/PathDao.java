package uk.ac.shef.oak.com4510.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Path;


@Dao
public interface PathDao {

    @Insert()
    long insert(Path path);

    @Delete
    void deleteImage(Path path);

    @Query("select * from path where stopTimestamp is not NULL")
    List<Path> findAll();

    @Query("select * from path where id= :id")
    LiveData<Path> findById(int id);

    @Query("select * from path where stopTimestamp is not NULL")
    LiveData<List<Path>> getAllPaths();

    @Query("update Path set location = :location, stopTimestamp=:stopTime where id=:id")
    void updatePath(int id, String location,String stopTime);

}
