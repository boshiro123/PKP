package bsuir.labwork.Labwork.services;

import bsuir.labwork.Labwork.models.Cinema;
import bsuir.labwork.Labwork.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    private final ServiceRepository repository;

    @Autowired
    public CinemaService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<Cinema> getAllCinemas() {
        return repository.findAll();
    }

    public void saveCinema(Cinema cinema) {
        repository.save(cinema);
    }

    public void updateCinema(Cinema cinema) {
        repository.save(cinema);
    }

    public void deleteCinema(int id) {
        repository.deleteById(id);
    }

    public Cinema getCinemaById(int id) {
        return repository.findById(id).orElse(null);
    }
}
