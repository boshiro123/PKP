package bsuir.labwork.Labwork.utils;

import static org.junit.jupiter.api.Assertions.*;

import bsuir.labwork.Labwork.models.Cinema;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DOMParserTest {

    @Test
    public void testParseCreditCards() throws Exception {
        DOMParser parser = new DOMParser();
        String filePath = "Cinemas.xml";
        List<Cinema> cinemas = parser.parseCinemas(filePath);
        assertEquals(5, cinemas.size());
        assertEquals("Октябрь", cinemas.get(0).getCinema_name());
        assertEquals("Барби", cinemas.get(0).getFilm());
        assertEquals("01-12-2022", cinemas.get(0).getDate());
        assertEquals("12", cinemas.get(0).getCost());
    }
}
