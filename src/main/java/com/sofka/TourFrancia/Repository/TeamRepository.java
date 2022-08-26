package com.sofka.TourFrancia.Repository;

import com.sofka.TourFrancia.Domain.CyclingTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface TeamRepository extends CrudRepository<CyclingTeam ,Long> {
    @Transactional
    List<CyclingTeam> findByTeamCode(String code);
    @Transactional
    List<CyclingTeam> findByCountry_Code(String code);

}
