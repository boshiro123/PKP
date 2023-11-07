package bsuir.labwork.Labwork;

import bsuir.labwork.Labwork.configs.ParserConfig;
import bsuir.labwork.Labwork.factories.DOMParserFactory;
import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.factories.SAXParserFactory;
import bsuir.labwork.Labwork.entity.Cinema;
import bsuir.labwork.Labwork.patterns.ValidationVisitor;

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

            writeToFile(domCinema);
            validateAndPrint(saxCinema, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Cinema> validateAndPrint(List<Cinema> cinemas, boolean isPrint) {
        ValidationVisitor visitor = new ValidationVisitor();
        for (Cinema cinema : cinemas) {
            cinema.accept(visitor);
        }
        return cinemas;
    }

    private static void writeToFile(List<Cinema> cinemas) throws IOException {
        cinemas.remove(4);
        List<String> outputLines = cinemas.stream().map(Cinema::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
