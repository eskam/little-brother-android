package com.example.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraModel {

    @SerializedName("guard")
    @Expose
    private String guard;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("ZoneModel")
    @Expose
    private ZoneModel zoneModel;

    public CameraModel(String guard, String target, ZoneModel zone){
        this.guard = guard;
        this.target = target;
        this.zoneModel = zone;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ZoneModel getZoneModel() {
        return zoneModel;
    }

    public void setZoneModel(ZoneModel zoneModel) {
        this.zoneModel = zoneModel;
    }

}