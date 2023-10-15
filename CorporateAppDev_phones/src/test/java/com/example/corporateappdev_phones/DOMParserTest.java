package com.example.corporateappdev_phones;


import com.example.corporateappdev_phones.models.Phone;
import com.example.corporateappdev_phones.utils.DOMParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DOMParserTest {

    @Test
    public void testParseCreditCards() throws Exception {
        DOMParser parser = new DOMParser();
        String filePath = ".xml";

        List<Phone> phones = parser.parsePhones(filePath);

        assertEquals(1, phones.size());

        assertEquals("Apple", phones.get(0).getBrand());
        assertEquals("iPhone 13 pro max", phones.get(0).getModel());
        assertEquals("2021", phones.get(0).getYearOfIssue());
        assertEquals("256", phones.get(0).getMemory());
    }
}
