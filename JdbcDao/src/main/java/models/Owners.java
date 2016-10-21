package models;

import com.google.common.base.MoreObjects;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Owners {
    private int ownerId;
    private String FIO;
    private int ownerAge;
    private String ownerCity;



    public Owners(int ownerId, String FIO, int ownerAge, String ownerCity) {
        this.ownerId = ownerId;
        this.FIO = FIO;
        this.ownerAge = ownerAge;
        this.ownerCity = ownerCity;
    }

    public Owners(String FIO, int ownerAge, String ownerCity) {
        this.FIO = FIO;
        this.ownerAge = ownerAge;
        this.ownerCity = ownerCity;
    }

    public int getOwnerId() { return ownerId; }

    public String getFIO() {
        return FIO;
    }

    public int getOwnerAge() {
        return ownerAge;
    }

    public String getOwnerCity() {
        return ownerCity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ID", this.getOwnerId())
                .add("FIO", this.getFIO())
                .add("Age", this.getOwnerAge())
                .add("City", this.getOwnerCity())
                .toString();
    }
}
