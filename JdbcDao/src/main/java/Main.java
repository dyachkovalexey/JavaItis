import jdbc.CarsDaoJdbcImpl;
import models.Cars;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        CarsDaoJdbcImpl carsDaoJdbc = new CarsDaoJdbcImpl();
        //carsDaoJdbc.find(1);
        //carsDaoJdbc.getAll();
        //carsDaoJdbc.delete(1);
        /*Cars cars = new Cars(1, "Audi", 500);
        carsDaoJdbc.update(cars);*/
    }
}
