package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.TeamDto;
import com.semih.p04_controller.intr.ITeamController;
import com.semih.p05_service.intr.ITeamService;
import com.semih.p06_repository.ITeamRepository;

@RestController
@RequestMapping(path = "/api/team")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class TeamController implements ITeamController {

	@Autowired
	private ITeamService teamService;

	@Autowired
	private ITeamRepository teamRepository;

	@Override
	@GetMapping(path = "/all")
	public List<TeamDto> getAllTeamController() {
		return teamService.getAllTeamService();
	}

	@Override
	@GetMapping(path = "/oneleague")
	public List<TeamDto> getOneLeagueTeams(@RequestParam String league) {
		return teamService.getOneLeaguesTeam(league);
	}

	@Override
	@GetMapping(path = "/teampageble")
	public Page<TeamDto> getTeamPegable(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "value") String sirala) {

		return teamService.getTeamPagable(page, size, sirala);
	}

	@Override
	@PutMapping(path = "/updatetactic")
	public Team updateTacticPlayers(@RequestParam String teamName) {
		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			teamService.updateTacticPlayersRole(team.getName());
		}

		return null;
	}

}
