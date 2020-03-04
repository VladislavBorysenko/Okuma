package com.androidschool.denis.okuma;

public class McdItem {

    private String mcdName;
    private String mcdDescription;

    public McdItem() {
    }

    public McdItem(String mcdName, String mcdDescription) {
        this.mcdName = mcdName;
        this.mcdDescription = mcdDescription;
    }

    public String getMcdName() {
        return mcdName;
    }

    public void setMcdName(String mcdName) {
        this.mcdName = mcdName;
    }

    public String getMcdDescription() {
        return mcdDescription;
    }

    public void setMcdDescription(String mcdDescription) {
        this.mcdDescription = mcdDescription;
    }

    @Override
    public String toString() {
        return "McdItem{" +
                "mcdName='" + mcdName + '\'' +
                ", mcdDescription='" + mcdDescription + '\'' +
                '}';
    }
}
