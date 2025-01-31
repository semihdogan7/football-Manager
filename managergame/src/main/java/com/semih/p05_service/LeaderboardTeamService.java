package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.LeaderboardTeam;
import com.semih.p02_entity.dto.LeaderboardTeamDto;
import com.semih.p05_service.intr.ILeaderboardTeamService;
import com.semih.p06_repository.ILeaderboardTeamRepository;
import com.semih.p06_repository.ITeamRepository;

@Service
public class LeaderboardTeamService implements ILeaderboardTeamService {

	@Autowired
	private ILeaderboardTeamRepository leaderboardTeamRepository;

	@Autowired
	private ITeamRepository teamRepository;

	@Override
	public List<LeaderboardTeamDto> getLeague(Integer season, String league) {
		List<LeaderboardTeam> leaderboardTeams = leaderboardTeamRepository.findBySeasonAndLeague(season, league);
		List<LeaderboardTeamDto> leaderboardTeamDtos = new ArrayList<>();
		for (LeaderboardTeam leaTeam : leaderboardTeams) {
			LeaderboardTeamDto dto = new LeaderboardTeamDto();
			BeanUtils.copyProperties(leaTeam, dto);
			dto.setMatch(dto.getWinn() + dto.getDraw() + dto.getLoss());
			dto.setPuan(dto.getWinn() * 3 + dto.getDraw());
			dto.setAveraj(dto.getGolScore() - dto.getGolCon());
			dto.setSiralamaPuani((float) (dto.getGolScore() / 500 + dto.getAveraj() + dto.getPuan() * 500));
			dto.setTeamId(teamRepository.findByName(dto.getTeamName()).getId());
			leaderboardTeamDtos.add(dto);
		}
		leaderboardTeamDtos.sort((a, b) -> Float.compare(b.getSiralamaPuani(), a.getSiralamaPuani()));
		return leaderboardTeamDtos;
	}

}
