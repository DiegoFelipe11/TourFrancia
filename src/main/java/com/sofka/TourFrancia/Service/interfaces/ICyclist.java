package com.sofka.TourFrancia.Service.interfaces;

import com.sofka.TourFrancia.Domain.Cyclist;

import java.util.List;

public interface ICyclist {
    List<Cyclist> getListCyclist();

    Cyclist saveCyclist(Cyclist country);

    Cyclist updateCyclist(Cyclist country , Long id);

    Cyclist deleteCyclist(Long id);

    List<Cyclist> searchForNationality(String nationality );

    boolean equipmentSize(Long id);

}
