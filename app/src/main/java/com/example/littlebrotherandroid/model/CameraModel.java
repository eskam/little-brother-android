package com.example.littlebrotherandroid.model;

public class CameraModel {
    private RestString name;
    private RestString bigBrother;
    private RestString littleBrother;
    //private Zone

    public CameraModel(RestString n, RestString bb, RestString lb) {
        name=n;
        bigBrother=bb;
        littleBrother=lb;
    }

    public RestString getBigBrother() {
        return bigBrother;
    }

    public void setBigBrother(RestString bigBrother) {
        this.bigBrother = bigBrother;
    }

    public RestString getLittleBrother() {
        return littleBrother;
    }

    public void setLittleBrother(RestString littleBrother) {
        this.littleBrother = littleBrother;
    }
}
