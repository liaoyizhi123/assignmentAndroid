package uk.ac.shef.oak.com4510.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Path;


@Dao
public interface PathDao {

    @Insert()
    long insert(Path path);

    @Delete
    void deleteImage(Path path);

    @Query("select * from path")
    List<Path> findAll();

    @Query("select * from path where id=:id")
    List<Path> findById(int id);



}
