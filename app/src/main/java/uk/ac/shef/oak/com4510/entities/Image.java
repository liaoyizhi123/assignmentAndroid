package uk.ac.shef.oak.com4510.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity()
public class Image {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String url;

    private String longitude;
    private String latitude;

    private String temp;
    private String pressure;

    private String timestamp;
    private int pathId;

    @Ignore
    public Image(){}

    public Image(String url, String longitude, String latitude, String temp, String pressure, String timestamp, int pathId) {
        this.url = url;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temp = temp;
        this.pressure = pressure;
        this.timestamp = timestamp;
        this.pathId = pathId;
    }


    @NonNull
    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", temp='" + temp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", pathId=" + pathId +
                '}';
    }
}
