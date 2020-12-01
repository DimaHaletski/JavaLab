package by.gsu.pms;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StAXParser {
    private static ArrayList<Valute> valutes = new ArrayList<>();
    private static ValCurs valCursObj;

    public static ValCurs parse() throws FileNotFoundException, XMLStreamException {
        String file = "resourсes/cbr.xml";
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
        String numCode_t = null;
        String charCode_t = null;
        String nominal_t= null;
        String name_t= null;
        String value_t= null;
        String Date = null;
        String Name = null;

        while(reader.hasNext()){
            XMLEvent xmlEvent = reader.nextEvent();
            if(xmlEvent.isStartElement()){
                StartElement startElement = xmlEvent.asStartElement();
                if(startElement.getName().getLocalPart().equals("ValCurs")) {
                    valCursObj = new ValCurs();
                    Date = startElement.getAttributeByName(new QName("Date")).getValue();
                    Name = startElement.getAttributeByName(new QName("name")).getValue();
                } else if (startElement.getName().getLocalPart().equals("NumCode")) {
                    xmlEvent = reader.nextEvent();
                    numCode_t = xmlEvent.asCharacters().getData();
                } else if (startElement.getName().getLocalPart().equals("CharCode")) {
                    xmlEvent = reader.nextEvent();
                    charCode_t = xmlEvent.asCharacters().getData();
                } else if (startElement.getName().getLocalPart().equals("Nominal")) {
                    xmlEvent = reader.nextEvent();
                    nominal_t = xmlEvent.asCharacters().getData();
                } else if (startElement.getName().getLocalPart().equals("Name")) {
                    xmlEvent = reader.nextEvent();
                    name_t = xmlEvent.asCharacters().getData();
                } else if (startElement.getName().getLocalPart().equals("Value")) {
                    xmlEvent = reader.nextEvent();
                    value_t = xmlEvent.asCharacters().getData();
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("Valute")) {
                    valutes.add(new Valute(numCode_t, charCode_t, nominal_t, name_t, value_t));
                }
            }
            if  (xmlEvent.isEndElement()){
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("ValCurs")){
                    valCursObj = new ValCurs(Date, Name, valutes);
                }
            }
        }
        return valCursObj;
    }
}


