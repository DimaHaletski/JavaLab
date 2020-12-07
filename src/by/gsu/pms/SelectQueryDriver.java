package by.gsu.pms;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectQueryDriver {
    private static Statement statement;
    private static ResultSet resultSet;

    private SelectQueryDriver() {
    }

    public static String executeSelectQuery(int exerciseNumber, String query, Connection connection) throws SQLException {
        String result = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (exerciseNumber == 3 || exerciseNumber == 4) {
                System.out.println(resultSet);
                result += ResultSetDriver.parseResultSetExtended(exerciseNumber, resultSet);
            }  else {
                result += ResultSetDriver.parseResultSet(exerciseNumber, resultSet);
            }
        } catch (SQLException sqlException) {
            System.out.println(">>> Select query execution failed.");
            sqlException.printStackTrace();
        } finally {
            statement.close();
            resultSet.close();
        }
        return result;
    }
}
