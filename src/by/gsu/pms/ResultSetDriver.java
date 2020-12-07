package by.gsu.pms;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetDriver {
    private ResultSetDriver(){}

    public static String parseResultSet(int taskNumber, ResultSet resultSet) throws SQLException {
        StringBuilder taskResult = new StringBuilder("<p>Task number: " + taskNumber + "</p>");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String weekday = resultSet.getString(3);
            String classroom = resultSet.getString(4);
            taskResult.append(String.format("id: %-5d | name: %-10s | weekday: %-10s | classroom %-10s", id, name, weekday, classroom));
        }
        return taskResult.toString();
    }

    public static String parseResultSetExtended(int taskNumber, ResultSet resultSet) throws SQLException {
        StringBuilder taskResult = new StringBuilder("<p>Task number: " + taskNumber + "</p>");
        while (resultSet.next()) {
            String weekday = resultSet.getString(1);
            System.out.println(weekday);
            taskResult.append(String.format("weekday: %-10s ", weekday));
        }
        System.out.println(taskResult.toString());
        return taskResult.toString();
    }
}