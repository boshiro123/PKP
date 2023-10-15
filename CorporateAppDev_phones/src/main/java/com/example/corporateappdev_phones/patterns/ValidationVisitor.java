package com.example.corporateappdev_phones.patterns;


import com.example.corporateappdev_phones.interfaces.Visitor;
import com.example.corporateappdev_phones.models.Phone;

public class ValidationVisitor implements Visitor {
    @Override
    public void visit(Phone phone) {
        try {
            phone.validate();
            System.out.println("Valid phone: " + phone);
        } catch (Exception e) {
            System.out.println("Invalid phone detected: " + phone + " Reason: " + e.getMessage());
        }
    }
}
