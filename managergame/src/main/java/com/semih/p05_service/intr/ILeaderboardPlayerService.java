package com.semih.p05_service.intr;

import java.util.List;

import com.semih.p02_entity.LeaderboardPlayer;
import com.semih.p02_entity.dto.LeaderboardPlayerDto;

public interface ILeaderboardPlayerService {
	public List<LeaderboardPlayerDto> getLeaderPlayer(int season, String league);
	
	public List<LeaderboardPlayer> getPlayerGolTop10(int season, String league);
	
	public List<LeaderboardPlayer> getPlayerAsistTop10(int season, String league);
}
