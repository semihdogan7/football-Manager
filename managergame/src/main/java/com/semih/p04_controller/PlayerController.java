package com.semih.p04_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.Player;
import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.PlayerDto;
import com.semih.p04_controller.intr.IPlayerController;
import com.semih.p05_service.intr.ICreatePlayerService;
import com.semih.p05_service.intr.IPlayerService;
import com.semih.p06_repository.ITeamRepository;

@RestController
@RequestMapping(path = "/api/player")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class PlayerController implements IPlayerController {

	String[] defPosList = { "GK", "GK", "GK",
			"DC", "DC", "DC", "DC", "DC", "DC", "DR", "DR", "DR", "DL", "DL", "DL",
			"DM", "DM", "DM", "MC", "MC", "MC", "ML", "ML", "ML", "MR", "MR", "MR",
			"AMC", "AMC", "AMC", "AML", "AML", "AML", "AMR", "AMR", "AMR", "ST", "ST", "ST" };

	@Autowired
	private IPlayerService playerService;

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private ICreatePlayerService createPlayerService;

	@Override
	@GetMapping(path = "/all")
	public List<PlayerDto> getAllPlayerController() {
		return playerService.getAllPlayerService();
	}

	@Override
	@PostMapping(path = "/save")
	public void savePlayer(@RequestParam String position, @RequestParam String teamName) {

		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			for (int i = 0; i < defPosList.length; i++) {
				playerService.savePlayer(defPosList[i], team.getName());
			}
		}
	}

	@Override
	@GetMapping(path = "/playerpage")
	public Page<PlayerDto> getPlayerPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "25") int size) {

		return playerService.getPlayerPage(page, size);
	}

	@Override
	@PutMapping(path = "/updateposition")
	public void updatePosition(@RequestParam String team, @RequestParam String position) {
		playerService.updatePosition(team, position);
	}

	@Override
	@PutMapping(path = "/updateallposition")
	public void updateAllPosition() {
		playerService.updateAllPosition();
	}

	@Override
	@GetMapping(path = "/playerthisteam")
	public List<PlayerDto> getPlayerThisTeam(@RequestParam String team2) {
		return playerService.getPlayerThisTeam(team2);
	}

	@Override
	@GetMapping(path = "/getplayer")
	public Optional<Player> getPlayer(@RequestParam Long id) {
		return playerService.getPlayer(id);
	}

	@Override
	@PostMapping(path = "/fillallteam")
	public void fillAllTeam() {
		createPlayerService.fillAllTeam();
	}

}
