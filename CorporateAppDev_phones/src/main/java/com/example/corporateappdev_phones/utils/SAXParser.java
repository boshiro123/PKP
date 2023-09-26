package com.example.corporateappdev_phones.utils;


import com.example.corporateappdev_phones.interfaces.Parser;
import com.example.corporateappdev_phones.models.Phone;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements Parser {
    @Override
    public List<Phone> parsePhones(String filePath) throws Exception {
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
}
