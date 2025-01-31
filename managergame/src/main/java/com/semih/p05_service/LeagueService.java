package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.League;
import com.semih.p02_entity.dto.LeagueDto;
import com.semih.p05_service.intr.ILeagueService;
import com.semih.p06_repository.ILeagueRepository;

@Service
public class LeagueService implements ILeagueService {
	
	@Autowired
	private ILeagueRepository leagueRepository;

	@Override
	public List<LeagueDto> getAllLeaugeService() {
		List<League> leagues = leagueRepository.findAll();
		List<LeagueDto> leaguDtos = new ArrayList<>();
		
		for(League league:leagues) {
			LeagueDto leagueDto = new LeagueDto();
			BeanUtils.copyProperties(league, leagueDto);
			leaguDtos.add(leagueDto);
		}
		
		leaguDtos.sort((a,b)->Integer.compare(b.getValue(), a.getValue()));
		return leaguDtos;
	}

	

}
