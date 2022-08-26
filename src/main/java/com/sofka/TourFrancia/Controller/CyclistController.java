package com.sofka.TourFrancia.Controller;

import com.sofka.TourFrancia.Domain.Cyclist;
import com.sofka.TourFrancia.Service.CyclistService;
import com.sofka.TourFrancia.Utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/")
public class CyclistController {
    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;
    @Autowired
    private CyclistService cyclistService;


    @GetMapping(path = "cyclist")
    public ResponseEntity<Response> listOfCyclists() {
        response.restart();
        try {
            response.data = cyclistService.getListCyclist();
            httpStatus = HttpStatus.OK;
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(path = "cyclist")
    public ResponseEntity<Response> newCyclists(@RequestBody Cyclist cyclist) {
        response.restart();
        try {
            if (cyclist.getCompetitorNumber().length() <= 3) {
                response.data = cyclistService.saveCyclist(cyclist);
                httpStatus = HttpStatus.CREATED;
            } else {
                response.message = "El codigo del ciclista debe contener 3 caracteres";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(path = "cyclist/{id}")
    public ResponseEntity<Response> changeCyclists(@PathVariable("id") Long id, @RequestBody Cyclist cyclist) {
        response.restart();
        try {
            response.data = cyclistService.updateCyclist(cyclist, id);
            if (response.data == null) {
                response.message = "El ciclista  no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "ciclista modificado correctamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(path = "cyclist/{id}")
    public ResponseEntity<Response> deleteCyclists(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = cyclistService.deleteCyclist(id);
            if (response.data == null) {
                response.message = "No se encontro este ciclista";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El ciclista fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(path = "cyclist/nationality/{nationality}")
    public ResponseEntity<Response> listNationality(@PathVariable("nationality") String nationality) {
        response.restart();
        try {
            response.data = cyclistService.searchForNationality(nationality);
            if (response.data == null) {
                response.message = "Los ciclistas con la " + nationality + " no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Listado de competidores con la nacionalidad  " + nationality;
                httpStatus = HttpStatus.OK;
            }

        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    public void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
