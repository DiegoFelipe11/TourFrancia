package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Country;
import com.sofka.TourFrancia.Domain.CyclingTeam;
import com.sofka.TourFrancia.Domain.Cyclist;
import com.sofka.TourFrancia.Repository.CyclistRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
class CyclistServiceTest {
    @Mock
    private CyclistRepository cyclistRepository;

    @InjectMocks
    private CyclistService cyclistService;

    private Cyclist cyclist;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        Country country = new Country(Long.valueOf(1), "Colombia", "col", new ArrayList<>(), new ArrayList<>());
        CyclingTeam cyclingTeam = new CyclingTeam(Long.valueOf(1),"Equipo solidaridad","ER2",country,new ArrayList<>());
        cyclist = new Cyclist(Long.valueOf(1), "Diego Felipe", "12E",cyclingTeam,country);

    }

    @Test
    void getListCyclist() {
        when(cyclistRepository.findAll()).thenReturn(Arrays.asList(cyclist));
        assertNotNull(cyclistService.getListCyclist());
    }

    @Test
    void saveCyclist() {
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);
        assertNotNull(cyclistService.saveCyclist(cyclist));
    }

    @Test
    void updateCyclist() {
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);
        assertNotNull(cyclistService.updateCyclist(cyclist,cyclist.getId()));
    }
    @Test
    void deleteCyclist() {
        doThrow(new PersistenceException()).when(cyclistRepository).deleteById(cyclist.getId());
        assertEquals(cyclistService.deleteCyclist(cyclist.getId()) , null);
    }

    @Test
    void searchForNationality() {
        when(cyclistRepository.findByCountry_Name(cyclist.getCountry().getName())).thenReturn(Arrays.asList( cyclist));
        assertNotNull(cyclistService.searchForNationality(cyclist.getCountry().getName()));
    }


}