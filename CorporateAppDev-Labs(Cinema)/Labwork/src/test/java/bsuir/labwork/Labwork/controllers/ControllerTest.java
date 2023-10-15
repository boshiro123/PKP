package bsuir.labwork.Labwork.controllers;

import static org.mockito.Mockito.*;

import bsuir.labwork.Labwork.entity.Cinema;
import bsuir.labwork.Labwork.services.CinemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    @InjectMocks
    private CinemaController controller;

    @Mock
    private CinemaService service;

    @Mock
    private Model model;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCinemas() {
        List<Cinema> cinemas = Arrays.asList(new Cinema(), new Cinema());
        when(service.getAllCinemas()).thenReturn(cinemas);
        String viewName = controller.getCinema(model);
        assertEquals("cinemas", viewName);
        verify(model, times(1)).addAttribute("cinemas", cinemas);
        verify(service, times(1)).getAllCinemas();
    }

//    @Test
//    public void testAddCinema() {
//        Cinema cinema = new Cinema();
//        String viewName = controller.addCinema(cinema);
//        assertEquals("redirect:/cinemas", viewName);
//        verify(service, times(1)).saveCinema(cinema);
//    }

    @Test
    public void testUpdateCinemaForm() {
        Cinema cinema = new Cinema();
        int id = 1;
        when(service.getCinemaById(id)).thenReturn(cinema);

        String viewName = controller.updateCinemaForm(id, model);
        assertEquals("updateCinema", viewName);
        verify(model, times(1)).addAttribute("cinema", cinema);
    }

    @Test
    public void testUpdateCinema() {
        Cinema cinema = new Cinema();
        String viewName = controller.updateCinema(cinema);
        assertEquals("redirect:/cinemas", viewName);
        verify(service, times(1)).updateCinema(cinema);
    }

    @Test
    public void testDeleteCinema() {
        int id = 1;
        String viewName = controller.deleteCinema(id);
        assertEquals("redirect:/cinemas", viewName);
        verify(service, times(1)).deleteCinema(id);
    }
}
