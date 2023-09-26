package com.example.corporateappdev_phones.factories;


import com.example.corporateappdev_phones.interfaces.Parser;
import com.example.corporateappdev_phones.interfaces.ParserFactory;
import com.example.corporateappdev_phones.utils.DOMParser;

public class DOMParserFactory implements ParserFactory {
    @Override
    public Parser createParser() {
        return new DOMParser();
    }
}
