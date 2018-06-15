package coolscan.dvsmart.com.dmdlocation.kostr;

import org.jetbrains.annotations.NotNull;

public class Marker {


    private double lat;
    private String description;
    private int id;
    private double lng;
    private String title;

    public Marker(double lat, String description, int id, double lng, String title) {
        this.lat = lat;
        this.description = description;
        this.id = id;
        this.lng = lng;
        this.title = title;
    }

    public Marker() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
