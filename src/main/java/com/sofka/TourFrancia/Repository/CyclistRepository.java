package com.sofka.TourFrancia.Repository;

import com.sofka.TourFrancia.Domain.CyclingTeam;
import com.sofka.TourFrancia.Domain.Cyclist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CyclistRepository extends CrudRepository<Cyclist,Long> {

    @Transactional
    List<Cyclist> findByCountry_Name(String nationality);
}
