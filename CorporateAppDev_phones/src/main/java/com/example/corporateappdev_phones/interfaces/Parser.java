package com.example.corporateappdev_phones.interfaces;


import com.example.corporateappdev_phones.models.Phone;

import java.util.List;

public interface Parser {
    List<Phone> parsePhones(String filePath) throws Exception;
}

