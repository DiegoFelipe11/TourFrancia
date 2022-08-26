package com.sofka.TourFrancia.Repository;

import com.sofka.TourFrancia.Domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
}
