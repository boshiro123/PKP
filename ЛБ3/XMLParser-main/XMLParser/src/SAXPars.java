import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SAXPars {

    public static List<Phone> parse(String filePath) throws Exception {
        List<Phone> phones = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            Phone phone;
            String currentElement;

            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElement = qName;
                if ("phone".equals(currentElement)) {
                    phone = new Phone();
                }
            }

            public void endElement(String uri, String localName, String qName) {
                if ("phone".equals(qName)) {
                    phones.add(phone);
                }
                currentElement = "";
            }

            public void characters(char[] ch, int start, int length) {
                String value = new String(ch, start, length).trim();
                if (value.isEmpty()) return;

                if ("brand".equals(currentElement)) {
                    phone.setBrand(value);
                } else if ("model".equals(currentElement)) {
                    phone.setModel(value);
                } else if ("yearOfIssue".equals(currentElement)) {
                    phone.setYearOfIssue(value);
                } else if ("memory".equals(currentElement)) {
                    phone.setMemory(value);
                }
            }
        };

        saxParser.parse(filePath, handler);
        return phones;
    }

    public static void main(String[] args) {
        try {
            List<Phone> phones = parse("phones.xml");
            writeToFile(phones);
            validateAndPrint(phones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrint(List<Phone> phones) {
        for (Phone phone : phones) {
            try {
                phone.validate();
                System.out.println("Valid card: " + phone);
            } catch (Exception e) {
                System.out.println("Invalid card detected: " + phone + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeToFile(List<Phone> cards) throws IOException {
        List<String> outputLines = cards.stream().map(Phone::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
