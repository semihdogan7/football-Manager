package com.semih.p06_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semih.p02_entity.LeaderboardTeam;

@Repository
public interface ILeaderboardTeamRepository extends JpaRepository<LeaderboardTeam, Long> {
	LeaderboardTeam findBySeasonAndTeamNameAndLeague(Integer season, String teamName,String league);
	List<LeaderboardTeam> findBySeasonAndLeague(Integer seaason, String league);
	
}
