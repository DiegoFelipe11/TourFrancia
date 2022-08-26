package com.sofka.TourFrancia.Service.interfaces;

import com.sofka.TourFrancia.Domain.CyclingTeam;

import java.util.List;
import java.util.Optional;

public interface ITeam {

    List<CyclingTeam> getListTeam ();

    Optional<CyclingTeam> getListTeamId(Long id);

    CyclingTeam saveTeam (CyclingTeam team);

    CyclingTeam updateTeam (CyclingTeam team , Long id);

    CyclingTeam deleteTeam (Long id);

    List<CyclingTeam> cyclingTeam(String code);

    List<CyclingTeam> teamNationality(String code);

}
