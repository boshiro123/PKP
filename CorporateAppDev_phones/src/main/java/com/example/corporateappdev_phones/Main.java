package com.example.corporateappdev_phones;


import com.example.corporateappdev_phones.configs.ParserConfig;
import com.example.corporateappdev_phones.factories.DOMParserFactory;
import com.example.corporateappdev_phones.factories.SAXParserFactory;
import com.example.corporateappdev_phones.interfaces.Parser;
import com.example.corporateappdev_phones.interfaces.ParserFactory;
import com.example.corporateappdev_phones.models.Phone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ParserFactory domFactory = new DOMParserFactory();
        Parser domParser = domFactory.createParser();

        ParserFactory saxFactory = new SAXParserFactory();
        Parser saxParser = saxFactory.createParser();

        try {
            ParserConfig config = ParserConfig.getInstance();
            String creditCardFilePath = config.getPhonesFilePath();

            List<Phone> domPhones = domParser.parsePhones(creditCardFilePath);
            List<Phone> saxPhones = saxParser.parsePhones(creditCardFilePath);

            writeToFile(domPhones);
            saxPhones = validateAndPrint(saxPhones,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Phone> validateAndPrint(List<Phone> phones, boolean isPrint) {
        List<Phone> list = new ArrayList<>();
        for (Phone phone : phones) {
            try {
                phone.validate();
                if(isPrint) System.out.println("Valid phone: " + phone);
            } catch (Exception e) {
                list.add(phone);
                if(isPrint)System.out.println("Invalid phone detected: " + phone + " Reason: " + e.getMessage());
            }
        }
        for (Phone i : list) {
         phones.remove(i);
        }
            return phones;
    }

    private static void writeToFile(List<Phone> phones) throws IOException {
        phones = validateAndPrint(phones,false);
        List<String> outputLines = phones.stream().map(Phone::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
