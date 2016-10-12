package models;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Cars {
    private int carId;
    private String autoName;
    private int mileage;

    public Cars(int carId, String autoName, int mileage) {
        this.carId = carId;
        this.autoName = autoName;
        this.mileage = mileage;
    }

    public int getCarId() {
        return carId;
    }

    public String getAutoName() {
        return autoName;
    }

    public int getMileage() {
        return mileage;
    }
}
