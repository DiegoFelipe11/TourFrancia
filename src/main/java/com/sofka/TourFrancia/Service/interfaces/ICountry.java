package com.sofka.TourFrancia.Service.interfaces;

import com.sofka.TourFrancia.Domain.Country;

import java.util.List;

public interface ICountry {

     List<Country> getListCountry();

     Country saveCountry(Country country);

     Country updateCountry(Country country , Long id);

     Country deleteCountry(Long id);


}
