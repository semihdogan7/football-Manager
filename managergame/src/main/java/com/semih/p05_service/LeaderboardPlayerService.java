package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.LeaderboardPlayer;
import com.semih.p02_entity.dto.LeaderboardPlayerDto;
import com.semih.p05_service.intr.ILeaderboardPlayerService;
import com.semih.p06_repository.ILeaderboardPlayerRepository;
import com.semih.p06_repository.IPlayerRepository;

@Service
public class LeaderboardPlayerService implements ILeaderboardPlayerService {

	@Autowired
	private ILeaderboardPlayerRepository leaderboardPlayerRepository;
	
	@Autowired IPlayerRepository playerRepository;

	@Override
	public List<LeaderboardPlayerDto> getLeaderPlayer(int season, String league) {
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerRepository.findBySeasonAndAndLeague(season,
				league);
		List<LeaderboardPlayerDto> leaderboardPlayerDtos = new ArrayList<>();

		for (LeaderboardPlayer lp : leaderboardPlayers) {
			LeaderboardPlayerDto dto = new LeaderboardPlayerDto();
			BeanUtils.copyProperties(lp, dto);
			dto.setScore(dto.getGoal()+dto.getAsist());			
			leaderboardPlayerDtos.add(dto);
		}
		
		leaderboardPlayerDtos.sort((a,b)->Integer.compare(b.getScore(), a.getScore()));
		
		return leaderboardPlayerDtos;
	}

	@Override
	public List<LeaderboardPlayer> getPlayerGolTop10(int season, String league) {
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerRepository.findTop10BySeasonAndLeagueOrderByGoalDesc(season, league);
		return leaderboardPlayers;
	}

	@Override
	public List<LeaderboardPlayer> getPlayerAsistTop10(int season, String league) {
		List<LeaderboardPlayer> leaderboardPlayers = leaderboardPlayerRepository.findTop10BySeasonAndLeagueOrderByAsistDesc(season, league);
		return leaderboardPlayers;
	}

}
