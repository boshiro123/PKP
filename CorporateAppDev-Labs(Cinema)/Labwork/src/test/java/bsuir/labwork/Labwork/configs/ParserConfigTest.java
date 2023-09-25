package bsuir.labwork.Labwork.configs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
        assertEquals("phones.xml", instance.getFilePath(), "Field not initialized correctly");
    }
}
