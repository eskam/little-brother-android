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
     */
    public CameraModel(String name, String littleBrother, String bigBrother) {
        super();
        this.name = name;
        this.littleBrother = littleBrother;
        this.bigBrother = bigBrother;
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

}
