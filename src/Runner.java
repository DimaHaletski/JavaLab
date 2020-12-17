import by.gsu.pms.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        boolean run = true;
        Mapper mapper = new Mapper();
        ArrayList<Object> queryParameters = new ArrayList<>();
        Connection connection = ConnectionDriver.openConnection();
//        ScriptRunner scriptRunner = new ScriptRunner(connection);
//        Reader readerCreate = new BufferedReader(new FileReader("./resources/create_script.sql"));
//        scriptRunner.runScript(readerCreate);
//        Reader readerInsert = new BufferedReader(new FileReader("./resources/insert_script.sql"));
//        scriptRunner.runScript(readerInsert);

        if (connection != null) {
            while (run) {
                showMenu();
                System.out.print("\nChoose menu item: ");
                userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        String result = "";
                        ArrayList<ProductsRecord> productsRecords = SelectQuaryDriver.selectProductsRecords(connection);
                        for (ProductsRecord productsRecord : productsRecords) {
                            Materials materials = SelectQuaryDriver.selectMaterialById(productsRecord.getMaterial_id(), connection);
                            Workers workers = SelectQuaryDriver.selectWorkerById(productsRecord.getWorker_id(), connection);
                            ProductsRecordDTO productsRecordDTO = mapper.mapProductsRecordDTO(productsRecord, materials, workers);
                        }
                        reportToHTML(result);
                        break;
                    case 2:
                        String result1 = "";
                        ArrayList<Materials> materials = SelectQuaryDriver.selectMaterials(connection);
                        reportToHTML(result1);
                        break;
                    case 3:
                        String result2 = "";
                        ArrayList<Workers> workers = SelectQuaryDriver.selectWorkers(connection);
                        reportToHTML(result2);
                        break;
                    case 4:
                        System.out.print("Enter material id: ");
                        String material_id = scanner.next();
                        queryParameters.add(material_id);
                        System.out.print("Enter worker id: ");
                        int worker_id = scanner.nextInt();
                        queryParameters.add(worker_id);
                        System.out.print("Enter making time: ");
                        String making_time = scanner.next();
                        queryParameters.add(making_time);
                        InsertQuaryDriver.insertProductsRecord(queryParameters, connection);
                        break;
                    case 5:
                        System.out.print("Enter worker name: ");
                        String worker_name = scanner.next();
                        queryParameters.add(worker_name);
                        System.out.print("Enter worker salary: ");
                        int worker_salary = scanner.nextInt();
                        queryParameters.add(worker_salary);
                        InsertQuaryDriver.insertWorker(queryParameters, connection);
                        break;
                    case 6:
                        System.out.print("Enter material name: ");
                        String material_name = scanner.next();
                        queryParameters.add(material_name);
                        System.out.print("Enter material cost: ");
                        int material_cost = scanner.nextInt();
                        queryParameters.add(material_cost);
                        InsertQuaryDriver.insertMaterial(queryParameters, connection);
                        break;

                    case 7:
                        System.out.println("\n>>> Exiting program...");
                        run = false;
                        connection.close();
                        break;
                    default:
                        System.out.println(">>> Unknown menu item! Choose another one.");
                        break;
                }
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n\t\tUSER MENU");
        System.out.println("------------------------------");
        System.out.println("1. SELECT products records");
        System.out.println("2. SELECT materials list");
        System.out.println("3. SELECT workers list");
        System.out.println("4. INSERT products record");
        System.out.println("5. INSERT worker");
        System.out.println("6. INSERT materials ");
        System.out.println("7. EXIT program");
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
