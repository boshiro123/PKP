package com.example.corporateappdev_phones;

import com.example.corporateappdev_phones.configs.ParserConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ParserConfigTest {

    @Test
    public void testSingletonInstance() {
        ParserConfig instance1 = ParserConfig.getInstance();
        ParserConfig instance2 = ParserConfig.getInstance();
        assertSame(instance1, instance2, "Instances are not the same, Singleton violated");
    }

    @Test
    public void testFieldInitialization() {
        ParserConfig instance = ParserConfig.getInstance();
        assertEquals("phones.xml", instance.getPhonesFilePath(), "Field not initialized correctly");
    }
}
