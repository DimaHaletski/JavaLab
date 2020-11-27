package by.gsu.pms;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SaxParser {
    private static ArrayList<Valute> valutes = new ArrayList<>();
    private static ValCurs valCursObj;

    public static ValCurs parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("resourсes/cbr.xml"), handler);
        return valCursObj;

    }

    private static class XMLHandler extends DefaultHandler {
        private String lastElementName;
        private static ArrayList<Valute> valutes_s = new ArrayList<>();
        private String Date_s;
        private String Name_s;
        private String valuteId_s;
        private String numCode_s;
        private String charCode_s;
        private String nominal_s;
        private String name_s;
        private String value_s;


        @Override
        public void endDocument() throws SAXException {
            valutes = valutes_s;
            valutes_s = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (qName.equals("ValCurs")) {
                Date_s = attributes.getValue("Date");
                Name_s = attributes.getValue("name");
            }
            if (qName.equals("Valute")) {
                valuteId_s = attributes.getValue("ID");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("Valute")) {
                if ((numCode_s != null && !numCode_s.isEmpty())
                        && (charCode_s != null && !charCode_s.isEmpty())
                        && (nominal_s != null && !nominal_s.isEmpty())
                        && (name_s != null && !name_s.isEmpty())
                        && (value_s != null && !value_s.isEmpty())) {
                    valutes_s.add(new Valute(numCode_s, charCode_s, nominal_s, name_s, value_s));
                }
                numCode_s = null;
                charCode_s = null;
                nominal_s = null;
                name_s = null;
                value_s = null;
            }
            if (qName.equals("ValCurs")) {
                if ((Date_s != null && !Date_s.isEmpty()) &&
                        (name_s != null && !name_s.isEmpty())) {
                    valCursObj = new ValCurs(Date_s, name_s, valutes_s);


                }

            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                if (lastElementName.equals("NumCode")) {
                    numCode_s = information;
                }
                if (lastElementName.equals("CharCode")) {
                    charCode_s = information;
                }
                if (lastElementName.equals("Nominal")) {
                    nominal_s = information;
                }
                if (lastElementName.equals("Name")) {
                    Name_s = information;
                }
                if (lastElementName.equals("Value")) {
                    value_s = information;
                }
            }

        }


    }
}

