package com.semih.p04_controller.intr;

import java.util.List;

import com.semih.p02_entity.dto.MatchLeagueDto;

public interface IMatchLeagueController {
	
	public List<MatchLeagueDto> getMatchLeague();
	
	public String generateFixture(String leagueName, int season);
	
	public List<MatchLeagueDto> getOneLeagueFixture(String league, int season);
	
	public void updateMatchResult(int season, int week, String league);
	
}
