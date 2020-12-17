package by.gsu.pms;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class ResultSetDriver {
        private ResultSetDriver() { }

        public static String parseResultSet(int taskNumber, ResultSet resultSet) throws SQLException {
            StringBuilder taskResult = new StringBuilder("<h3>Task number: " + taskNumber + "</h3>");
            while (resultSet.next()) {

               // taskResult.append(String.format());
            }
            return taskResult.toString();
        }

        public static String parseResultSetExtended(int taskNumber, ResultSet resultSet) throws SQLException {
            StringBuilder taskResult = new StringBuilder("<h3>Task number: " + taskNumber + "</h3>");
            while (resultSet.next()) {

                //taskResult.append(String.format());
            }
            return taskResult.toString();
        }
    }

