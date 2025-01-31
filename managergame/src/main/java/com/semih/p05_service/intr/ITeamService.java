package com.semih.p05_service.intr;

import java.util.List;

import org.springframework.data.domain.Page;

import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.TeamDto;

public interface ITeamService {

	public List<TeamDto> getAllTeamService();
	
	public List<TeamDto> getOneLeaguesTeam(String league);
	
	public Page<TeamDto> getTeamPagable(int page, int size, String sirala);
	
	public TeamDto getOneTeamWithPlayers(String name);
	
	public Team updateTacticPlayersRole(String teamName);
	
}
