package com.example.corporateappdev_phones.utils;


import com.example.corporateappdev_phones.interfaces.Parser;
import com.example.corporateappdev_phones.models.Phone;
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
    public  List<Phone> parsePhones(String filePath) throws Exception {
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
}
