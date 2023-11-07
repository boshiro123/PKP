package bsuir.labwork.Labwork.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import bsuir.labwork.Labwork.entity.Cinema;
import bsuir.labwork.Labwork.repositories.ServiceRepository;
import bsuir.labwork.Labwork.services.CinemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {
    @Mock
    private ServiceRepository repository;

    @InjectMocks
    private CinemaService cinemaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCinemas_shouldReturnAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        when(repository.findAll()).thenReturn(cinemas);
        List<Cinema> result = cinemaService.getAllCinemas();
        assertEquals(cinemas, result);
    }

    @Test
    void saveCinema_shouldCallSaveMethod() {
        Cinema cinema = new Cinema();
        cinemaService.saveCinema(cinema);
        verify(repository).save(cinema);
    }

    @Test
    void updateCinema_shouldCallSaveMethod() {
        Cinema cinema = new Cinema();
        cinemaService.updateCinema(cinema);
        verify(repository).save(cinema);
    }

    @Test
    void deleteCinema_shouldCallDeleteByIdMethod() {
        int id = 1;
        cinemaService.deleteCinema(id);
        verify(repository).deleteById(id);
    }

    @Test
    void getCinemaById_shouldReturnCinemaWhenFound() {
        int id = 1;
        Cinema cinema = new Cinema();
        when(repository.findById(id)).thenReturn(Optional.of(cinema));
        Cinema result = cinemaService.getCinemaById(id);
        assertEquals(cinema, result);
    }

    @Test
    void getCinemaById_shouldReturnNullWhenNotFound() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());
        Cinema result = cinemaService.getCinemaById(id);
        assertNull(result);
    }
}
