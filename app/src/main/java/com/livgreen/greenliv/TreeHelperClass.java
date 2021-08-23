package com.livgreen.greenliv;

public class TreeHelperClass {

    String latitude,longitude,name,Id;
    Double trunk,height,Sequestration;

    public TreeHelperClass(){

    }

    public TreeHelperClass(String latitude, String longitude, String name, String id, Double trunk, Double height, Double sequestration) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        Id = id;
        this.trunk = trunk;
        this.height = height;
        Sequestration = sequestration;
    }

    public Double getSequestration() {
        return Sequestration;
    }

    public void setSequestration(Double sequestration) {
        Sequestration = sequestration;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTrunk() {
        return trunk;
    }

    public void setTrunk(Double trunk) {
        this.trunk = trunk;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
