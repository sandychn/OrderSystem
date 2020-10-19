package com.example.pojo;

import java.io.Serializable;

public class Kind implements Serializable {
    private static final long serialVersionUID = 1L;

    private int kindID; //餐品总类ID
    private String kindName; //餐品种类名称

    public int getKindID() {
        return kindID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}