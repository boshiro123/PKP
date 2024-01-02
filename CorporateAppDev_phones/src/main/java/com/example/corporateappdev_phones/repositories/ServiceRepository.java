package com.example.corporateappdev_phones.repositories;

import com.example.corporateappdev_phones.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
ServiceRepository extends JpaRepository<Phone, Integer> {
}
