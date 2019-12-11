package uk.ac.shef.oak.com4510.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity()
public class Path {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String title;

    private String photoIds;

    private String location;

    private String startTimestamp;
    private String stopTimestamp;


    @Ignore
    public Path(){}


    public Path(String title, String startTimestamp){
        this.title=title;
        this.startTimestamp=startTimestamp;
    }
@Ignore
    public Path(String title, String photoIds, String location, String startTimestamp, String stopTimestamp) {
        this.title = title;
        this.photoIds = photoIds;
        this.location = location;
        this.startTimestamp = startTimestamp;
        this.stopTimestamp = stopTimestamp;
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

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public String getStopTimestamp() {
        return stopTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setStopTimestamp(String stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", photoIds='" + photoIds + '\'' +
                ", location='" + location + '\'' +
                ", startTimestamp='" + startTimestamp + '\'' +
                ", stopTimestamp='" + stopTimestamp + '\'' +
                '}';
    }
}
