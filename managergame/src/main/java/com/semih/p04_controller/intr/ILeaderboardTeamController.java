package com.semih.p04_controller.intr;

import java.util.List;

import com.semih.p02_entity.dto.LeaderboardTeamDto;

public interface ILeaderboardTeamController {

	public List<LeaderboardTeamDto> getLeague(int season, String league);
	
	
}
