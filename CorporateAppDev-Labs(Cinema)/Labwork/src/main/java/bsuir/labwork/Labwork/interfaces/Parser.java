package bsuir.labwork.Labwork.interfaces;

import bsuir.labwork.Labwork.entity.Cinema;

import java.util.List;

public interface Parser {
    List<Cinema> parseCinemas(String filePath) throws Exception;
}

