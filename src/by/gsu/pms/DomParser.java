package by.gsu.pms;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DomParser {
    public static ValCurs parse(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url);

        NamedNodeMap valCursAttributes = document.getDocumentElement().getAttributes();

        NodeList valuteList = document.getDocumentElement().getElementsByTagName("Valute");
        ArrayList<Valute> valutes = new ArrayList<>();
        for (int i = 0; i < valuteList.getLength(); i++) {
            Node valute = valuteList.item(i);
            NamedNodeMap valuteAttribures = valute.getAttributes();

            if (valuteAttribures.getNamedItem("ID").getTextContent().equals("R01035") ||
                    valuteAttribures.getNamedItem("ID").getTextContent().equals("R01235") ||
                    valuteAttribures.getNamedItem("ID").getTextContent().equals("R01239") ||
                    valuteAttribures.getNamedItem("ID").getTextContent().equals("R01090B")) {
                NodeList valuteDataList = valute.getChildNodes();
                valutes.add(new Valute(
                        valuteDataList.item(0).getTextContent(),
                        valuteDataList.item(1).getTextContent(),
                        valuteDataList.item(2).getTextContent(),
                        valuteDataList.item(3).getTextContent(),
                        valuteDataList.item(4).getTextContent()));
            }

        }

        return new ValCurs(
                valCursAttributes.getNamedItem("Date").getTextContent(),
                valCursAttributes.getNamedItem("name").getTextContent(),
                valutes);
    }
}
