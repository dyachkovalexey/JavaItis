package ru.itis.models;

import com.google.common.base.MoreObjects;

public class Autos {

    private int autoId;
    private String autoName;
    private String autoNumber;
    private int userId;

    public Autos(int autoId, String autoName, String autoNumber, int userId) {
        this.autoId = autoId;
        this.autoName = autoName;
        this.autoNumber = autoNumber;
        this.userId = userId;
    }

    public Autos(String autoName, String autoNumber, int userId) {
        this.autoName = autoName;
        this.autoNumber = autoNumber;
        this.userId = userId;
    }

    public int getAutoId() {
        return autoId;
    }

    public String getAutoName() {
        return autoName;
    }

    public String getAutoNumber() {
        return autoNumber;
    }

    public int getUserId() { return userId; }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ID", this.getAutoId())
                .add("Mark", this.getAutoName())
                .add("Number", this.getAutoNumber())
                .add("UserId", this.getUserId())
                .toString();
    }


}
