package uk.ac.shef.oak.com4510.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import uk.ac.shef.oak.com4510.entities.Image;


@Dao
public interface ImageDao {

    @Insert
    void insert(Image image);

    @Query("select * from image where id= :id")
    LiveData<Image> findById(int id);

    @Query("select * from image where pathId= :pathId")
    LiveData<List<Image>> findByPathId(int pathId);

    @Query("select * from image where id IN(:ids)")
    LiveData<List<Image>> findByIds(int ids);

    @Query("select * from Image")
    List<Image> findAll();

    @Query("select * from Image")
    LiveData<List<Image>> getLiveImage();

    @Delete
    void deleteImage(Image image);
}
