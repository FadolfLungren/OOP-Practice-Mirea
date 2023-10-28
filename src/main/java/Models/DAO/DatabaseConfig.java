package Models.DAO;

public class DatabaseConfig {
    public static String host = "localhost:3306";
    public static String name = "marketplace";
    public static String username = "root";
    public static String password = "root";

    public static String getConnectionString() {
        return "jdbc:mysql://" + host + "/" + name;
    }
}
