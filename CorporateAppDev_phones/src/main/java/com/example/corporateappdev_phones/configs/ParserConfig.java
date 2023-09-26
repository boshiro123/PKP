package com.example.corporateappdev_phones.configs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserConfig {
    private static ParserConfig instance;

    private String phonesFilePath;

    private ParserConfig() {
        this.phonesFilePath = "phones.xml";
    }

    public static ParserConfig getInstance() {
        if (instance == null) {
            instance = new ParserConfig();
        }
        return instance;
    }
}
