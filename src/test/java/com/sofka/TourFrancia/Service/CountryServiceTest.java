package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Country;
import com.sofka.TourFrancia.Repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CountryServiceTest {
    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    private Country country;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        country = new Country(Long.valueOf(1),"Colombia","col",new ArrayList<>(),new ArrayList<>());
    }

    @Test
    void getListCountry() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country));
        assertNotNull(countryService.getListCountry());
    }

    @Test
    void saveCountry() {
        when(countryRepository.save(any(Country.class))).thenReturn(country);
        assertNotNull(countryService.saveCountry(country));
    }

    @Test
    void updateCountry() {

        when(countryRepository.save(any(Country.class))).thenReturn(country);
        assertNotNull(countryService.updateCountry(country,country.getId()));
        assertEquals(country.getCode(), "COL");
    }

    @Test
    void deleteCountry() {
        doThrow(new PersistenceException()).when(countryRepository).deleteById(country.getId());
        assertEquals(countryService.deleteCountry(country.getId()) , null);
    }
}