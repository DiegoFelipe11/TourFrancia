package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Country;
import com.sofka.TourFrancia.Domain.CyclingTeam;
import com.sofka.TourFrancia.Repository.TeamRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
@Slf4j
class TeamServiceTest {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private CyclingTeam cyclingTeam;
    private Country country;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        country = new Country(Long.valueOf(1), "Colombia", "COL", new ArrayList<>(), new ArrayList<>());
        cyclingTeam = new CyclingTeam(Long.valueOf(1), "Equipo todo terreno", "3A1", country, new ArrayList<>());
    }

    @Test
    void getListTeam() {
        when(teamRepository.findAll()).thenReturn(Arrays.asList(cyclingTeam));
        assertNotNull(teamService.getListTeam());
    }

    @Test
    void getListTeamId() {
        when(teamRepository.findById(cyclingTeam.getId())).thenReturn(Optional.ofNullable(cyclingTeam));
        var team = teamService.getListTeamId(cyclingTeam.getId());
        assertEquals(team.get().getTeamCode(), "3A1");

    }

    @Test
    void saveTeam() {
        when(teamRepository.save(any(CyclingTeam.class))).thenReturn(cyclingTeam);
        assertNotNull(teamService.saveTeam(cyclingTeam));
    }

    @Test
    void updateTeam() {
        when(teamRepository.save(any(CyclingTeam.class))).thenReturn(cyclingTeam);
        assertNotNull(teamService.updateTeam(cyclingTeam, cyclingTeam.getId()));
    }

    @Test
    void deleteTeam() {
        doThrow(new PersistenceException()).when(teamRepository).deleteById(cyclingTeam.getId());
        assertEquals(teamService.deleteTeam(cyclingTeam.getId()), null);

    }

    @Test
    void cyclingTeam() {
        when(teamRepository.findByTeamCode(cyclingTeam.getTeamCode())).thenReturn((Arrays.asList( cyclingTeam)));
        assertNotNull( teamService.cyclingTeam(cyclingTeam.getName()));
    }

    @Test
    void teamNationality() {
        when(teamRepository.findByCountry_Code(country.getCode())).thenReturn(Arrays.asList(cyclingTeam));
        var team = teamService.teamNationality(country.getCode());
        assertEquals(team.get(0).getTeamCode(), "3A1");

    }
}