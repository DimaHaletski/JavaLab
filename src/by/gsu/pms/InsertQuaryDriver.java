package by.gsu.pms;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class InsertQuaryDriver {
    public static final String PRODUCTS_RECORD_QUERY = "INSERT INTO products (material_id, worker_id, making_time) VALUES (?, ?, ?);";
    public static final String MATERIALS_QUERY = "INSERT INTO materilas (material_name, material_cost) VALUES (?, ?);";
    public static final String WORKER_QUERY = "INSERT INTO workers (worker_name, worker_salary) VALUES (?, ?);";

    private InsertQuaryDriver(){}

    public static void insertProductsRecord(ArrayList<Object> queryParameters, Connection connection){
        try (PreparedStatement preparedStatement = connection.prepareStatement(PRODUCTS_RECORD_QUERY)) {
            preparedStatement.setInt(1, (Integer) queryParameters.get(0));
            preparedStatement.setInt(2, (Integer) queryParameters.get(1));
            preparedStatement.setInt(3,  (Integer) queryParameters.get(2));

            preparedStatement.execute();
            System.out.println(">>> Insert query executed...");
        } catch (SQLException sqlEx) {
            System.out.println(">>> Insert query execution failed.");
            sqlEx.printStackTrace();
        }
    }
    public static void insertMaterial(ArrayList<Object> queryParameters, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(MATERIALS_QUERY)) {
            preparedStatement.setString(1, (String) queryParameters.get(0));
            preparedStatement.setInt(2, (Integer) queryParameters.get(1));
            preparedStatement.execute();
            System.out.println(">>> Insert query executed...");
        } catch (SQLException sqlEx) {
            System.out.println(">>> Insert query execution failed.");
            sqlEx.printStackTrace();
        }
    }
    public static void insertWorker(ArrayList<Object> queryParameters, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(WORKER_QUERY)) {
            preparedStatement.setString(1, (String) queryParameters.get(0));
            preparedStatement.setInt(2, (Integer) queryParameters.get(1));
            preparedStatement.execute();
            System.out.println(">>> Insert query executed...");
        } catch (SQLException sqlEx) {
            System.out.println(">>> Insert query execution failed.");
            sqlEx.printStackTrace();
        }
    }
}
