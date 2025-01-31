package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.LeaderboardPlayer;

@Repository
public interface ILeaderboardPlayerRepository extends JpaRepository<LeaderboardPlayer, Long> {
	List<LeaderboardPlayer> findBySeasonAndAndLeague(Integer season, String league);

	LeaderboardPlayer findBySeasonAndAndLeagueAndPlayerId(Integer season, String league, Long playerId);

	LeaderboardPlayer findByPlayerIdAndSeason(Long playerId, Integer season);

	List<LeaderboardPlayer> findTop10BySeasonAndLeagueOrderByGoalDesc(Integer gol, String league);
	List<LeaderboardPlayer> findTop10BySeasonAndLeagueOrderByAsistDesc(Integer asist, String league);
}
