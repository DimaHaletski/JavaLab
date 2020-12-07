package by.gsu.pms;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDriver {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Minsk&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public ConnectionDriver() {
    }

    public Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection opened...");
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
        return connection;
    }

}
