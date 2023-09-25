package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.models.Cinema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {
    @Override
    public List<Cinema> parseCinemas(String filePath) throws Exception {
        List<Cinema> cinemas = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//newInstance - создает новую фабрику
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//создаем новый построитель
        Document doc = dBuilder.parse(filePath);//разбирает текст XML-документа и создает объект документа.

        NodeList nList = doc.getElementsByTagName("cinema");//возвращает список дочерних элементов с именем cinema

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                Cinema cinema = new Cinema();
                cinema.setCinema_name(eElement.getElementsByTagName("cinemaName").item(0).getTextContent());
                //getTextContent - возвращает одной строкой весь текст находящийся в данном узле
                cinema.setFilm(eElement.getElementsByTagName("film").item(0).getTextContent());
                cinema.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());
                cinema.setCost(eElement.getElementsByTagName("cost").item(0).getTextContent());

                cinemas.add(cinema);
            }
        }
        return cinemas;
    }
}
