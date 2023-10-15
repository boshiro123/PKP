package com.example.corporateappdev_phones.interfaces;


import com.example.corporateappdev_phones.models.Phone;

public interface Visitor {
    void visit(Phone card);
}