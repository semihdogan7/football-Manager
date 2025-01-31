package com.semih.p05_service.intr;

import java.util.List;

import com.semih.p02_entity.dto.MatchLeagueDto;

public interface IMatchLeagueService {
	
	public List<MatchLeagueDto> getMatchLeague();
	
	public void generateFixture(String league,int season);
	
	public List<MatchLeagueDto> getOneLeagueFixture(String league, int season);
	
	public void updateMatchResult(int season, int week, String league);
	
}
