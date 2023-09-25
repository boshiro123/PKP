import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DOMPars {

    public static List<Phone> parse(String filePath) throws Exception {
        List<Phone> phones = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);

        NodeList nList = doc.getElementsByTagName("phone");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                Phone phone = new Phone();
                phone.setBrand(eElement.getElementsByTagName("brand").item(0).getTextContent());
                phone.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
                phone.setYearOfIssue(eElement.getElementsByTagName("yearOfIssue").item(0).getTextContent());
                phone.setMemory(eElement.getElementsByTagName("memory").item(0).getTextContent());

                phones.add(phone);
            }
        }
        return phones;
    }

    public static void main(String[] args) {
        try {
            List<Phone> phones = parse("XMLParser/phones.xml");
            phones = validateAndPrint(phones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(List<Phone> phones) throws IOException {
        List<String> outputLines = phones.stream().map(Phone::toString).collect(Collectors.toList());
        Files.write(Paths.get("XMLParser/output.txt"), outputLines);
    }

    private static List<Phone> validateAndPrint(List<Phone> phones) throws IOException {
        List<Phone> list = new ArrayList<>();
        for (Phone phone : phones) {
            try {
                phone.validate();
                System.out.println("Valid card: " + phone);
            } catch (Exception e) {
                list.add(phone);
                System.out.println("Invalid card detected: " + phone + " Reason: " + e.getMessage());
            }
        }
        for(Phone phone: list){
            phones.remove(phone);
        }
        writeToFile(phones);
        return phones;
    }
}