package by.gsu.pms;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDriver {

        public static final String URL = "jdbc:mysql://localhost/mydb?serverTimezone=Europe/Minsk&useSSL=false";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "root";

        private ConnectionDriver() { }

        public static Connection openConnection() {
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
