package com.semih.p04_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semih.p02_entity.MatchDeatils;
import com.semih.p04_controller.intr.IMatchDetailsController;
import com.semih.p05_service.intr.IMatchDetailsService;

@RestController
@RequestMapping(path = "/api/matchdetail")
//@CrossOrigin(origins = "http://localhost:5000")
@CrossOrigin(origins = "*")
public class MatchDetailsController implements IMatchDetailsController {
	
	@Autowired
	private IMatchDetailsService matchDetailsService;

	@Override
	@PostMapping(path = "/save")
	public void saveMatchDetails(List<MatchDeatils> matchDeatils) {
				matchDetailsService.saveMatchDetails(matchDeatils);
	}

}
