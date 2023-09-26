package com.example.corporateappdev_phones.models;

import com.example.corporateappdev_phones.exceptions.PhoneException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Pattern;
@Getter
@Setter
@ToString
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String brand;
    private String model;
    private String yearOfIssue;
    private String memory;

    private static final String BRAND_PATTERN = "[A-Za-z\\s]+";
    private static final String YEAR_OF_ISSUE_PATTERN = "\\d{4}";
    private static final String MEMORY_PATTERN = "\\d{3}";

    public Phone() {
    }

    public void validate() throws PhoneException {
        if (!Pattern.matches(BRAND_PATTERN, brand)) {
            throw new PhoneException("Invalid brand name");
        }
        if (!Pattern.matches(YEAR_OF_ISSUE_PATTERN,yearOfIssue)) {
            throw new PhoneException("Invalid years if issue");
        }
        if (Integer.parseInt(memory)<0) {
            throw new PhoneException("Invalid memory");
        }
    }
    public Phone(String brand, String model, String yearOfIssue, String memory) {
        this.brand = brand;
        this.model = model;
        this.yearOfIssue = yearOfIssue;
        this.memory = memory;
    }
}
