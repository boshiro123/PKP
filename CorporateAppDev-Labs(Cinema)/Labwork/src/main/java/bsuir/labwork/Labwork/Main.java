package bsuir.labwork.Labwork;

import bsuir.labwork.Labwork.configs.ParserConfig;
import bsuir.labwork.Labwork.factories.DOMParserFactory;
import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.factories.SAXParserFactory;
import bsuir.labwork.Labwork.models.Cinema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            String FilePath = config.getFilePath();

            List<Cinema> domCinema = domParser.parseCinemas(FilePath);
            List<Cinema> saxCinema = saxParser.parseCinemas(FilePath);

            writeCardsToFile(domCinema);
            validateAndPrintCards(saxCinema);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintCards(List<Cinema> cinemas) {
        for (Cinema cinema : cinemas) {
            try {
                cinema.validate();
                System.out.println("Valid cinema: " + cinema);
            } catch (Exception e) {
                System.out.println("Invalid cinema detected: " + cinema + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeCardsToFile(List<Cinema> cinemas) throws IOException {
        List<String> outputLines = cinemas.stream().map(Cinema::toString).collect(Collectors.toList());
        Files.write(Paths.get("Labwork/output.txt"), outputLines);
    }
}
