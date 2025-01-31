package com.semih.p04_controller.intr;

import java.util.List;

import org.springframework.data.domain.Page;

import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.TeamDto;

public interface ITeamController {

	public List<TeamDto> getAllTeamController();

	public List<TeamDto> getOneLeagueTeams(String league);

	public Page<TeamDto> getTeamPegable(int page, int size, String sirala);

	public Team updateTacticPlayers(String teamName);

}
