package com.example.littlebrotherandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraModel{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("littleBrother")
    @Expose
    private String littleBrother;
    @SerializedName("bigBrother")
    @Expose
    private String bigBrother;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("radius")
    @Expose
    private Double radius;




    /**
     * No args constructor for use in serialization
     *
     */
    public CameraModel() {
    }
    /**
     *
     * @param name
     * @param littleBrother
     * @param bigBrother
     * @param latitude
     * @param longitude
     * @param radius
     */
    public CameraModel(String name, String littleBrother, String bigBrother, Double latitude, Double longitude, Double radius) {
        this.name = name;
        this.littleBrother = littleBrother;
        this.bigBrother = bigBrother;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLittleBrother() {
        return littleBrother;
    }

    public void setLittleBrother(String littleBrother) {
        this.littleBrother = littleBrother;
    }

    public String getBigBrother() {
        return bigBrother;
    }

    public void setBigBrother(String bigBrother) {
        this.bigBrother = bigBrother;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
