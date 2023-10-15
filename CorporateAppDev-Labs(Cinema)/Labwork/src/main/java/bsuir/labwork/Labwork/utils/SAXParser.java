package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.entity.Cinema;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements Parser {
    @Override
    public List<Cinema> parseCinemas(String filePath) throws Exception {
        List<Cinema> cinemas = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();//создает фабрику
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();//создает новый разборщик

        DefaultHandler handler = new DefaultHandler() {
            Cinema cinema;
            String currentElement;
            //вызывается, когда встретился начальный тег элемента
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElement = qName;
                if ("cinema".equals(currentElement)) {
                    cinema = new Cinema();
                }
            }
            //вызывается, когда встретился конечный тег элемента.
            public void endElement(String uri, String localName, String qName) {
                if ("cinema".equals(qName)) {
                    cinemas.add(cinema);
                }
                currentElement = "";
            }

            public void characters(char[] ch, int start, int length) {//вызывается, когда встретился текст.
                String value = new String(ch, start, length).trim();
                if (value.isEmpty()) return;

                if ("cinemaName".equals(currentElement)) {
                    cinema.setCinema_name(value);
                } else if ("film".equals(currentElement)) {
                    cinema.setFilm(value);
                } else if ("date".equals(currentElement)) {
                    cinema.setDate(value);
                } else if ("cost".equals(currentElement)) {
                    cinema.setCost(value);
                }
            }
        };

        saxParser.parse(filePath, handler);
        //разбирает текст XML-документа, вызывая в процессе разбора обработчик handler.
        return cinemas;
    }
}
