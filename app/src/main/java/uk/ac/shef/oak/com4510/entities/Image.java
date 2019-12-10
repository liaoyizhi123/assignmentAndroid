package uk.ac.shef.oak.com4510.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Image {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String url;

    private String longitude;
    private String latitude;

    private Double temp;
    private Double pressure;

    private String timestamp;
    private int pathId;

    public Image(){};

    public Image(String url, String longitude, String latitude, Double temp, Double pressure, String timestamp, int pathId) {
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

    public Double getTemp() {
        return temp;
    }

    public Double getPressure() {
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

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setPressure(Double pressure) {
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
}
