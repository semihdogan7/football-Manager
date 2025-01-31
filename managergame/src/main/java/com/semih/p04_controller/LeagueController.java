package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.dto.LeagueDto;
import com.semih.p04_controller.intr.ILeagueController;
import com.semih.p05_service.intr.ILeagueService;

@RestController
@RequestMapping(path = "/api/league")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class LeagueController implements ILeagueController {
	
	@Autowired
	private ILeagueService leaugeService;

	@Override
	@GetMapping(path = "/all")
	public List<LeagueDto> getAllLeaugeController() {
		return leaugeService.getAllLeaugeService();
	}
	
}
