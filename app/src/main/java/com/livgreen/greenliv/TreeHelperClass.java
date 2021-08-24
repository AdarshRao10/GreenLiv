package com.livgreen.greenliv;

public class TreeHelperClass {

    String name,Id;
    Double trunk,height,Sequestration,wa,wt,wd,wc,wco2;

    public TreeHelperClass(){

    }

    public TreeHelperClass(String name, String id, Double trunk, Double height, Double sequestration, Double wa, Double wt, Double wd, Double wc, Double wco2) {
        this.name = name;
        Id = id;
        this.trunk = trunk;
        this.height = height;
        Sequestration = sequestration;
        this.wa = wa;
        this.wt = wt;
        this.wd = wd;
        this.wc = wc;
        this.wco2 = wco2;
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
