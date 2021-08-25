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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public Double getSequestration() {
        return Sequestration;
    }

    public void setSequestration(Double sequestration) {
        Sequestration = sequestration;
    }

    public Double getWa() {
        return wa;
    }

    public void setWa(Double wa) {
        this.wa = wa;
    }

    public Double getWt() {
        return wt;
    }

    public void setWt(Double wt) {
        this.wt = wt;
    }

    public Double getWd() {
        return wd;
    }

    public void setWd(Double wd) {
        this.wd = wd;
    }

    public Double getWc() {
        return wc;
    }

    public void setWc(Double wc) {
        this.wc = wc;
    }

    public Double getWco2() {
        return wco2;
    }

    public void setWco2(Double wco2) {
        this.wco2 = wco2;
    }


}
