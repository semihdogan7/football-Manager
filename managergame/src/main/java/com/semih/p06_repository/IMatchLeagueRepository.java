package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.MatchLeague;

@Repository
public interface IMatchLeagueRepository extends JpaRepository<MatchLeague, Integer> {
	List<MatchLeague> findByLeagueAndSeason(String league, int season);
	List<MatchLeague> findBySeasonAndWeekAndLeagueAndIsPlayed(int season, int week, String league,String isPlayed);
	List<MatchLeague> findBySeasonAndWeekAndIsPlayed(int season, int week, String isplayed);
}
