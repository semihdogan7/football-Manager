package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.dto.LeaderboardTeamDto;
import com.semih.p04_controller.intr.ILeaderboardTeamController;
import com.semih.p05_service.intr.ILeaderboardTeamService;

@RestController
@RequestMapping(path = "/api/leateam")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class LeaderboardTeamController implements ILeaderboardTeamController {

	@Autowired
	private ILeaderboardTeamService leaderboardTeamService;

	@Override
	@GetMapping(path = "/getleague")
	public List<LeaderboardTeamDto> getLeague(@RequestParam int season, @RequestParam String league) {
		return leaderboardTeamService.getLeague(season, league);
	}

}
