package com.sofka.TourFrancia.Controller;

import com.sofka.TourFrancia.Domain.CyclingTeam;
import com.sofka.TourFrancia.Service.TeamService;
import com.sofka.TourFrancia.Utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/")
public class CyclingTeamController {
    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;
    @Autowired
    private TeamService teamService;

    @GetMapping(path = "team")
    public ResponseEntity<Response> listOfEquipment() {
        response.restart();
        try {
            response.data = teamService.getListTeam();
            httpStatus = HttpStatus.OK;
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(path = "team/list/{id}")
    public ResponseEntity<Response> listOfEquipmentId(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = teamService.getListTeamId(id);
            if (response.data == null) {
                response.message = "No existe un equipo con este id";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Equipo";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(path = "team")
    public ResponseEntity<Response> newEquipment(@RequestBody CyclingTeam team) {
        response.restart();
        try {
            if (team.getTeamCode().length() == 3) {
                response.data = teamService.saveTeam(team);
                httpStatus = HttpStatus.CREATED;
            } else {
                response.message = "El codigo del equipo solo debe contener 3 caracteres";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(path = "team/{id}")
    public ResponseEntity<Response> changeEquipment(@PathVariable("id") Long id, @RequestBody CyclingTeam team) {
        response.restart();
        try {
            response.data = teamService.updateTeam(team, id);
            if (response.data == null) {
                response.message = "El equipo de ciclistas  no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "equipo modificado correctamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(path = "team/{id}")
    public ResponseEntity<Response> deleteEquipment(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = teamService.deleteTeam(id);
            if (response.data == null) {
                response.message = "El equipo no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El equipo fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("team/code/{code}")
    public ResponseEntity<Response> teamCode(@PathVariable("code") String code) {
        response.restart();
        try {
            response.data = teamService.cyclingTeam(code);
            if (response.data == null ) {
                response.message = "No se encuentra equipos con el codigo ";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Lista de equipos";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception ex) {
            getErrorMessageInternal(ex);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("team/code/nationality/{code}")
    public ResponseEntity<Response> nationalityCode(@PathVariable("code") String code) {
        response.restart();
        try {
            response.data = teamService.teamNationality(code);
            if (response.data == null) {
                response.message = "No se encuentra equipos con la nacionalidad " + code;
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Lista de equipos";
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
