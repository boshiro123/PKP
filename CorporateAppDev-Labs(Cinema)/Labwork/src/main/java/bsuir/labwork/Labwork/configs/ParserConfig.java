package bsuir.labwork.Labwork.configs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserConfig {
    private static ParserConfig instance;

    private String FilePath;

    private ParserConfig() {
        this.FilePath = "Labwork/Cinemas.xml";
    }

    public static ParserConfig getInstance() {
        if (instance == null) {
            instance = new ParserConfig();
        }
        return instance;
    }
}
