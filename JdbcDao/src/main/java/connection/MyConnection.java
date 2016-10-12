package connection;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class MyConnection {
    private String URL = "jdbc:postgresql://localhost:5432/Auto";
    private String name = "postgres";
    private String password = "jie1995xa";

    public MyConnection() {

    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.URL;
    }
}
