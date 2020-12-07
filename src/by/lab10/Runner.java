package by.lab10;

import by.gsu.pms.ConnectionDriver;
import by.gsu.pms.SelectQueryDriver;
import by.gsu.pms.UpdateQueryDriver;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        boolean test = true;

        Connection connection = new ConnectionDriver().getConnect();
        ScriptRunner scriptRunner = new ScriptRunner(connection);
//        try {
//            Reader readerCreate = new BufferedReader(new FileReader("./resources/CreateScript.sql"));
//            scriptRunner.runScript(readerCreate);
//
//            Reader readerInsert = new BufferedReader(new FileReader("./resources/InsertScript.sql"));
//            scriptRunner.runScript(readerInsert);
//            System.out.println("Tables created and inserted");
//        }catch (Exception ex ){
//            System.out.println("Tables alredy exist");
//        }
        if (connection != null) {
            showMenu();
            System.out.println("\n Please choose: ");
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    String result = "";
                    String exercise1Select = "SELECT teachers.id,teachers.name,subjects.weekday,subjects.classroom\n" +
                            "FROM mydb.teachers\n" +
                            "inner join subjects on teachers.subjId = subjects.id\n" +
                            "WHERE teachers.subjId in (select subjects.id from subjects where subjects.weekday = 'mon' and subjects.classroom = '1-10' )\n" +
                            "group by teachers.id , teachers.name;";
                    result += SelectQueryDriver.executeSelectQuery(1, exercise1Select, connection);
                    String exercise2Select = "SELECT teachers.id,teachers.name,subjects.weekday, subjects.classroom\n" +
                            "FROM mydb.teachers\n" +
                            "inner join subjects on teachers.subjId = subjects.id \n" +
                            "WHERE teachers.subjId not in (select subjects.id from subjects where subjects.weekday = 'mon' )\n" +
                            "group by teachers.id , teachers.name;";
                    result += SelectQueryDriver.executeSelectQuery(2, exercise2Select, connection);
                    String exercise3Select =
                            "select weekday\n" +
                            "from subjects\n" +
                            "group by weekday\n" +
                            "having count(weekday) = 4;";
                    result += SelectQueryDriver.executeSelectQuery(3, exercise3Select, connection);
                    String exercise4Select =
                            "select weekday\n" +
                            "from subjects\n" +
                            "group by weekday\n" +
                            "having count(classroom) = 4;";
                    result += SelectQueryDriver.executeSelectQuery(4, exercise4Select, connection);
                    reportToHTML(result);
                    break;
                case 2:
                    String exercise5Update = "update subjects a\n" +
                            "\tinner join subjects b on a.id <> b.id\n" +
                            "\t\tset a.subjName = b.subjName,\n" +
                            "\t\t\ta.weekday = b.weekday,\n" +
                            "\t\t\ta.classroom = b.classroom\n" +
                            "\twhere a.id in (1,4) and b.id in (1,4);";
                    UpdateQueryDriver.executeUpdeteQuery(5, exercise5Update, connection);
                    break;
                case 3:
                    System.out.println("exit");
                    test = false;
                    connection.close();
                    break;
                default:
                    System.out.println("err input");
                    break;
            }
        } else {
            System.out.println("exit");
        }

    }

    public static void showMenu() {
        System.out.println("Please select query type : ");
        System.out.println("Select query : 1");
        System.out.println("Update query : 2");
        System.out.println("exit program : 3");
    }

    public static void reportToHTML(String result) {
        File f = new File("./resources/index.html");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f))) {
            System.out.println(">>> Creating report index.html file...");
            bufferedWriter.write(
                    "<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                            "   <head>" +
                            "       <meta charset=\"UTF-8\">" +
                            "       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                            "       <title>Результаты работы программы</title>" +
                            "   </head>" +
                            "   <body>" +
                            "       <h1>Результат работы программы:</h1>" +
                            "       <p>" + result + "</p>" +
                            "   </body>" +
                            "</html>"
            );
            System.out.println(">>> File saved to ./resources/index.html");
        } catch (Exception ex) {
            System.out.println(">>> Error occurred during markup save");
            ex.printStackTrace();
        }
    }
}
