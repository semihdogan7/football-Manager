package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.LeaderboardPlayer;
import com.semih.p02_entity.dto.LeaderboardPlayerDto;
import com.semih.p04_controller.intr.ILeaderboardPlayerController;
import com.semih.p05_service.intr.ILeaderboardPlayerService;

@RestController
@RequestMapping(path = "api/leaplayer")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")

public class LeaderboardPlayerController implements ILeaderboardPlayerController{
	
	@Autowired
	private ILeaderboardPlayerService leaderboardPlayerService;

	@Override
	@GetMapping(path = "/scored")
	public List<LeaderboardPlayerDto> getLeaderPlayer(@RequestParam int season,@RequestParam String league) {
		return leaderboardPlayerService.getLeaderPlayer(season, league);
	}

	@Override
	@GetMapping(path = "/goltop10")
	public List<LeaderboardPlayer> getPlayerGolTop10(int season, String league) {
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerService.getPlayerGolTop10(season, league);
		return leaderboardPlayers;
	}

	@Override
	@GetMapping(path = "/asisttop10")
	public List<LeaderboardPlayer> getPlayerAsistTop10(int season, String league) {
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerService.getPlayerAsistTop10(season, league);
		return leaderboardPlayers;
	}

	
	

}
