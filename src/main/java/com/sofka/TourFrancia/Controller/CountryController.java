package com.sofka.TourFrancia.Controller;

import com.sofka.TourFrancia.Domain.Country;
import com.sofka.TourFrancia.Service.CountryService;
import com.sofka.TourFrancia.Utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/")
public class CountryController {

    private final Response response = new Response();
    private  HttpStatus httpStatus = HttpStatus.OK;
    @Autowired
    private CountryService countryService;

    @GetMapping(path = "country")
    public ResponseEntity<Response> listOfCountries() {
        response.restart();
        try {
            response.data = countryService.getListCountry();
            httpStatus = HttpStatus.OK;
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(path = "country")
    public ResponseEntity<Response> newCountry(@RequestBody Country country) {
        response.restart();
        try {
            response.data = countryService.saveCountry(country);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(path = "country/{id}")
    public ResponseEntity<Response> changeCountry(@PathVariable("id") Long id, @RequestBody Country country) {
        response.restart();
        try {
            response.data = countryService.updateCountry(country, id);
            if (response.data == null) {
                response.message = "El pais no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            }else {
                response.message = "Pais modificado correctamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response,httpStatus);
    }

    @DeleteMapping(path = "country/{id}")
    public ResponseEntity<Response>  deleteCountry(@PathVariable("id") Long id){
        response.restart();
        try {
            response.data=countryService.deleteCountry(id);
            if(response.data==null){
                response.message = "El pais no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            }else{
                response.message = "El pais fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        }catch (Exception ex){
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response,httpStatus);
    }

    public void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
