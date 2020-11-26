package lab8;

import by.gsu.pms.DomParser;
import by.gsu.pms.SaxParser;
import by.gsu.pms.ValCurs;
import by.gsu.pms.Valute;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
    public static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static ValCurs valCurs;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        boolean isTrue = true;

        do {
            System.out.println("\n Выберите метод получения данных:");
            System.out.println("1:Получить данные при помощи Dom парсера");
            System.out.println("2:Получить данные при помощи SAX парсера");
            System.out.println("3:Получить данные при помощи StAX парсера");
            System.out.println("4:Выход из программы");
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1 -> {
                    valCurs = DomParser.parse(URL);

                    break;
                }
                case 2 -> {
                    SaxParser.parse();

                }
                //case 3 ->
                case 4 -> {

                    isTrue = false;
                }
                default -> System.out.println("Не правильный выбор!");
            }


        } while (isTrue);
    }
}

    public static void showResult(ValCurs) {
        System.out.println(valCurs.getDate());
        System.out.println(valCurs.getName());

        for (Valute valute : valCurs.getValutes()) {
            System.out.println(valute.getNumCode());
            System.out.println(valute.getCharCode());
            System.out.println(valute.getNominal());
            System.out.println(valute.getName());
            System.out.println(valute.getValue());
        }
    }