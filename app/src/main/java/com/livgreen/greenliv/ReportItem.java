package com.livgreen.greenliv;

public class ReportItem {

    String name;
    String seqestrationVal;

    public ReportItem(String name, String seqestrationVal) {
        this.name = name;
        this.seqestrationVal = seqestrationVal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeqestrationVal() {
        return seqestrationVal;
    }

    public void setSeqestrationVal(String seqestrationVal) {
        this.seqestrationVal = seqestrationVal;
    }
}
