package oak.shef.ac.uk.assignment.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import oak.shef.ac.uk.assignment.entities.Image;

@Dao
public interface ImageDao {

    @Insert
    void insert(Image image);

    @Query("select * from image where id= :id")
    LiveData<Image> findById(int id);

    @Query("select * from image where pathId= :pathId")
    LiveData<Image> findByPathId(int pathId);

    @Query("select * from image where id IN(:ids)")
    LiveData<List<Image>> findByIds(int ids);

    @Query("select * from image order by timestamp")
    LiveData<List<Image>> findAll();

    @Delete
    void deleteImage(Image image);
}
