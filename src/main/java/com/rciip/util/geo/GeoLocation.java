package com.rciip.util.geo;

/**
 * Created by ZJM on 2018/4/5.
 */
public class GeoLocation {
    private final double longitude;
    private final double latitude;

    public GeoLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
