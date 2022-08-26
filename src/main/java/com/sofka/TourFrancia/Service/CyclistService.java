package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Cyclist;
import com.sofka.TourFrancia.Repository.CyclistRepository;
import com.sofka.TourFrancia.Repository.TeamRepository;
import com.sofka.TourFrancia.Service.interfaces.ICyclist;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CyclistService implements ICyclist {
    @Autowired
    private CyclistRepository cyclistRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Cyclist> getListCyclist() {
        return (List<Cyclist>) cyclistRepository.findAll();
    }

    @Override
    public Cyclist saveCyclist(Cyclist cyclist) {
        cyclist.setCompetitorNumber(cyclist.getCompetitorNumber().toUpperCase());
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Cyclist updateCyclist(Cyclist cyclist, Long id) {
        cyclist.setId(id);
        cyclist.setCompetitorNumber(cyclist.getCompetitorNumber().toUpperCase());
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Cyclist deleteCyclist(Long id) {
        var ciclyst = cyclistRepository.findById(id);
        if (ciclyst.isPresent()) {
            cyclistRepository.delete(ciclyst.get());
            return ciclyst.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Cyclist> searchForNationality(String nationality) {
        return cyclistRepository.findByCountry_Name(nationality);
    }

    @Override
    public boolean equipmentSize(Long id) {
        var team = teamRepository.findById(id);
        if (team.get().getCyclist().size() <= 7) {
            return true;
        }
        return false;
    }

}
