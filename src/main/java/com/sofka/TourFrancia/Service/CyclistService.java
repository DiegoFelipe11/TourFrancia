package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Cyclist;
import com.sofka.TourFrancia.Repository.CyclistRepository;
import com.sofka.TourFrancia.Service.interfaces.ICyclist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CyclistService implements ICyclist {
    @Autowired
    private CyclistRepository cyclistRepository;
    @Override
    public List<Cyclist> getListCyclist() {
        return (List<Cyclist>) cyclistRepository.findAll();
    }

    @Override
    public Cyclist saveCyclist(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Cyclist updateCyclist(Cyclist cyclist, Long id) {
        cyclist.setId(id);
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Cyclist deleteCyclist(Long id) {
       var ciclyst = cyclistRepository.findById(id);
       if(ciclyst.isPresent()){
           cyclistRepository.delete(ciclyst.get());
           return ciclyst.get();
       }else{
           return null;
       }
    }

    @Override
    public List<Cyclist> searchForNationality(String nationality) {
        return cyclistRepository.findByCountry_Name(nationality);
    }
}
