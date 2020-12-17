package by.gsu.pms;

import java.sql.*;
import java.util.ArrayList;

public class SelectQuaryDriver {
    public static final String PRODUCTS_RECORDS_QUERY = "SELECT * FROM products";
    public static final String MATERIALS_QUERY = "SELECT * FROM materials";
    public static final String MATERIAL_QUERY = "SELECT material_name, material_cost FROM materials WHERE material_id = ?";
    public static final String WORKERS_QUERY = "SELECT * FROM workers";
    public static final String WORKER_QUERY = "SELECT worker_name, worker_salary FROM worker WHERE worker_id = ?";

    private SelectQuaryDriver() {
    }

    public static ArrayList<ProductsRecord> selectProductsRecords(Connection connection) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ProductsRecord> productsRecords = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(PRODUCTS_RECORDS_QUERY);
            int product_id = resultSet.getInt("product_id");
            int material_id = resultSet.getInt("material_id");
            int worker_id = resultSet.getInt("worker_id");
            int making_time = resultSet.getInt("making_time");

            productsRecords.add(new ProductsRecord(product_id, material_id, worker_id, making_time));
        } catch (SQLException sqlException) {
            System.out.println(">>> Select query execution failed.");
            sqlException.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return productsRecords;
    }

    public static ArrayList<Materials> selectMaterials(Connection connection) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Materials> materials = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(MATERIALS_QUERY);

            int material_id = resultSet.getInt("material_id");
            String material_name = resultSet.getString("material_name");
            int material_cost = resultSet.getInt("material_cost");

            materials.add(new Materials(material_id, material_name, material_cost));
        } catch (SQLException sqlException) {
            System.out.println(">>> Select query execution failed.");
            sqlException.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return materials;
    }

    public static Materials selectMaterialById(int material_id, Connection connection) throws SQLException {
        Materials materials;

        try (PreparedStatement preparedStatement = connection.prepareStatement(MATERIAL_QUERY)) {
            preparedStatement.setInt(1, material_id);
            ResultSet resultSet = null;
            materials = null;

            try {
                resultSet = preparedStatement.executeQuery();

                String material_name = resultSet.getString("material_name");
                int material_cost = resultSet.getInt("material_cost");

                materials = new Materials(material_id, material_name, material_cost);
            } catch (SQLException sqlException) {
                System.out.println(">>> Select query execution failed.");
                sqlException.printStackTrace();
            } finally {
                assert resultSet != null;
                resultSet.close();
            }
        }
        return materials;
    }

    public static ArrayList<Workers> selectWorkers(Connection connection) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Workers> workers = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(WORKERS_QUERY);

            int worker_id = resultSet.getInt("worker_id");
            String worker_name = resultSet.getString("worker_name");
            int worker_salary = resultSet.getInt("worker_salary");

            workers.add(new Workers(worker_id, worker_name, worker_salary));
        } catch (SQLException sqlException) {
            System.out.println(">>> Select query execution failed.");
            sqlException.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return workers;
    }

    public static Workers selectWorkerById(int worker_id, Connection connection) throws SQLException {
        Workers workers;

        try (PreparedStatement preparedStatement = connection.prepareStatement(WORKER_QUERY)) {
            preparedStatement.setInt(1, worker_id);
            ResultSet resultSet = null;
            workers = null;

            try {
                resultSet = preparedStatement.executeQuery();

                String worker_name = resultSet.getString("worker_name");
                int worker_salary = resultSet.getInt("worker_salary");

                workers = new Workers(worker_id, worker_name, worker_salary);
            } catch (SQLException sqlException) {
                System.out.println(">>> Select query execution failed.");
                sqlException.printStackTrace();
            } finally {
                assert resultSet != null;
                resultSet.close();
            }
        }
        return workers;
    }
}

