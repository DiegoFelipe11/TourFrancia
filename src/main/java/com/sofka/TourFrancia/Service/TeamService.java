package com.sofka.TourFrancia.Service;

import com.sofka.TourFrancia.Domain.CyclingTeam;
import com.sofka.TourFrancia.Repository.TeamRepository;
import com.sofka.TourFrancia.Service.interfaces.ITeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService implements ITeam {
    @Autowired
    private  TeamRepository teamRepository;
    @Override
    public List<CyclingTeam> getListTeam() {
        return (List<CyclingTeam>) teamRepository.findAll();
    }

    @Override
    public Optional<CyclingTeam> getListTeamId(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public CyclingTeam saveTeam(CyclingTeam team) {
        team.setTeamCode(team.getTeamCode().toUpperCase());
        return teamRepository.save(team);
    }

    @Override
    public CyclingTeam updateTeam(CyclingTeam team, Long id) {
        team.setId(id);
        team.setTeamCode(team.getTeamCode().toUpperCase());
        return teamRepository.save(team);
    }

    @Override
    public CyclingTeam deleteTeam(Long id) {
        var team = teamRepository.findById(id);
        if(team.isPresent()) {
            teamRepository.delete(team.get());
            return team.get();
        }else {
            return null;
        }
    }

    @Override
    public List<CyclingTeam> cyclingTeam(String code) {
        return teamRepository.findByTeamCode(code.toUpperCase());
    }

    @Override
    public List<CyclingTeam> teamNationality(String code) {
        return teamRepository.findByCountry_Code(code);
    }


}
