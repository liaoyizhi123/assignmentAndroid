package oak.shef.ac.uk.assignment.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity()
public class Path {

    @PrimaryKey(autoGenerate = true)
    @android.support.annotation.NonNull
    private int id;

    private String title;

    private String photoIds;

    private String location;

    public Path(String title, String photoIds, String location) {
        this.title = title;
        this.photoIds = photoIds;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPhotoIds() {
        return photoIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhotoIds(String photoIds) {
        this.photoIds = photoIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
