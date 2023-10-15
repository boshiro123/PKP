package bsuir.labwork.Labwork.repositories;

import bsuir.labwork.Labwork.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Cinema, Integer> {
}
