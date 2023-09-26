package com.example.corporateappdev_phones;


import com.example.corporateappdev_phones.controllers.PhoneController;
import com.example.corporateappdev_phones.models.Phone;
import com.example.corporateappdev_phones.services.PhonesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PhoneControllerTest {

    @InjectMocks
    private PhoneController controller;

    @Mock
    private PhonesService service;

    @Mock
    private Model model;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPhones() {
        List<Phone> phones = Arrays.asList(new Phone(), new Phone());
        when(service.getAllPhones()).thenReturn(phones);
        String viewName = controller.getPhones(model);
        assertEquals("phones", viewName);
        verify(model, times(1)).addAttribute("phones", phones);
        verify(service, times(1)).getAllPhones();
    }

    @Test
    public void testAddPhone() {
        Phone phone = new Phone();
        String viewName = controller.addPhone(phone);
        assertEquals("redirect:/phones", viewName);
        verify(service, times(1)).savePhone(phone);
    }

    @Test
    public void testUpdatePhoneForm() {
        Phone phone = new Phone();
        int id = 1;
        when(service.getPhoneById(id)).thenReturn(phone);

        String viewName = controller.updatePhoneForm(id, model);
        assertEquals("updatePhone", viewName);
        verify(model, times(1)).addAttribute("phone", phone);
    }

    @Test
    public void testUpdateCard() {
        Phone phone = new Phone();
        String viewName = controller.updatePhone(phone);
        assertEquals("redirect:/phones", viewName);
        verify(service, times(1)).updatePhone(phone);
    }

    @Test
    public void testDeletePhone() {
        int id = 1;
        String viewName = controller.deletePhone(id);
        assertEquals("redirect:/phones", viewName);
        verify(service, times(1)).deletePhone(id);
    }
}
