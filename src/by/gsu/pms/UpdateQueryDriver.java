package by.gsu.pms;


import java.sql.*;

public class UpdateQueryDriver {
    private static PreparedStatement preparedStatement;


    private UpdateQueryDriver() { }

    public static String executeUpdeteQuery(int taskNumber, String query, Connection connection) throws SQLException {
        String result = "";
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException sqlException) {
            System.out.println(">>> Update query execution failed.");
            sqlException.printStackTrace();
        } finally {
            preparedStatement.close();
        }
        return result;
    }
}