package com.example.littlebrotherandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraModel{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("littleBrotherId")
    @Expose
    private String littleBrotherId;
    @SerializedName("bigBrotherId")
    @Expose
    private String bigBrotherId;
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
    @Expose
    private String id;
    @SerializedName("accept")
    @Expose
    private Boolean accept;




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

     */
    public CameraModel(String name, String littleBrother, String bigBrother, Double latitude, Double longitude) {
        this.name = name;
        this.littleBrother = littleBrother;
        this.bigBrother = bigBrother;
        this.latitude = latitude;
        this.longitude = longitude;
        this.littleBrotherId = null;
        this.bigBrotherId = null;
        this.id = null;
        this.accept = false;
    }

    public CameraModel(String name, String littleBrother, String bigBrother, String littleBrotherId, String bigBrotherId, Double latitude, Double longitude, String id, Boolean accept) {
        this.name = name;
        this.littleBrother = littleBrother;
        this.bigBrother = bigBrother;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.accept = accept;
        this.littleBrotherId = littleBrotherId;
        this.bigBrotherId = bigBrotherId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public String getBigBrotherId() {
        return bigBrotherId;
    }

    public void setBigBrotherId(String bigBrotherId) {
        this.bigBrotherId = bigBrotherId;
    }

    public String getLittleBrotherId() {
        return littleBrotherId;
    }

    public void setLittleBrotherId(String littleBrotherId) {
        this.littleBrotherId = littleBrotherId;
    }
}
