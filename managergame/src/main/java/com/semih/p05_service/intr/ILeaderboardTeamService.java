package com.semih.p05_service.intr;

import java.util.List;

import com.semih.p02_entity.dto.LeaderboardTeamDto;

public interface ILeaderboardTeamService {
public List<LeaderboardTeamDto> getLeague(Integer season, String league);
}
