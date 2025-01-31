package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.dto.MatchLeagueDto;
import com.semih.p04_controller.intr.IMatchLeagueController;
import com.semih.p05_service.intr.IMatchLeagueService;

@RestController
@RequestMapping(path = "/api/match")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class MatchLeagueController implements IMatchLeagueController {

	@Autowired
	private IMatchLeagueService matchLeagueService;

	@Override
	@GetMapping(path = "/league")
	public List<MatchLeagueDto> getMatchLeague() {
		return matchLeagueService.getMatchLeague();
	}

	@Override
	@PostMapping(path = "/generatefixture")
	public String generateFixture(@RequestParam String leagueName,@RequestParam int season) {
		try {
			matchLeagueService.generateFixture(leagueName, season);
			return "Fixture successfully generated and saved for league: " + leagueName;
		} catch (Exception e) {
			return "Error while generating fixture: " + e.getMessage();
		}
	}

	@Override
	@GetMapping(path = "/leaguefixture")
	public List<MatchLeagueDto> getOneLeagueFixture(@RequestParam String league,@RequestParam int season) {
		return matchLeagueService.getOneLeagueFixture(league, season);
	}

	@Override
	@PutMapping(path = "/updateMatch")
	public void updateMatchResult(@RequestParam int season,@RequestParam int week, @RequestParam String league) {
		matchLeagueService.updateMatchResult(season, week,league);
	}

}
