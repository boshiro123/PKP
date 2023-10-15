package com.example.corporateappdev_phones.services;

import com.example.corporateappdev_phones.models.Phone;
import com.example.corporateappdev_phones.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PhonesService {
    private final ServiceRepository repository;
    @Autowired
    public PhonesService(ServiceRepository repository) {
        this.repository = repository;
    }
    public List<Phone> getAllPhones() {
        return repository.findAll();
    }
    public boolean savePhone(Phone phone) {
        repository.save(phone);
        return true;
    }

    public void updatePhone(Phone phone){
        repository.save(phone);
    }

    public void deletePhone(int id) {
        repository.deleteById(id);
    }

    public Phone getPhoneById(int id) {
        return repository.findById(id).orElse(null);
    }
}
