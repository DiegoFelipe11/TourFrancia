package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.Country;
import com.sofka.TourFrancia.Repository.CountryRepository;
import com.sofka.TourFrancia.Service.interfaces.ICountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService implements ICountry {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getListCountry() {
        return (List<Country>) countryRepository.findAll();
    }

    @Override
    public Country saveCountry(Country country) {
        country.setCode(country.getCode().toUpperCase());
        country.setName(country.getName().toUpperCase());
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country, Long id) {
        country.setId(id);
        country.setCode(country.getCode().toUpperCase());
        country.setName(country.getName().toUpperCase());
        return countryRepository.save(country);
    }

    @Override
    public Country deleteCountry(Long id) {
        var country = countryRepository.findById(id);
        if(country.isPresent()) {
            countryRepository.delete(country.get());
            return country.get();
        }else {
            return null;
        }
    }
}
