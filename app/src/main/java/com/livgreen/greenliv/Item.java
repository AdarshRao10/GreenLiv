package com.livgreen.greenliv;

public class Item {
String no;
String common_name;
String botanical_name;
String family;

    public Item(String no, String common_name, String botanical_name, String family) {
        this.no = no;
        this.common_name = common_name;
        this.botanical_name = botanical_name;
        this.family = family;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getBotanical_name() {
        return botanical_name;
    }

    public void setBotanical_name(String botanical_name) {
        this.botanical_name = botanical_name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}

