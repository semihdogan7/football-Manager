package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.LeaderboardTeam;
import com.semih.p02_entity.League;
import com.semih.p02_entity.MatchLeague;
import com.semih.p02_entity.MyData;
import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.MatchLeagueDto;
import com.semih.p05_service.intr.IMatchLeagueService;
import com.semih.p06_repository.ILeaderboardTeamRepository;
import com.semih.p06_repository.ILeagueRepository;
import com.semih.p06_repository.IMatchLeagueRepository;
import com.semih.p06_repository.IMyDataRepository;
import com.semih.p06_repository.ITeamRepository;
import com.semih.p07_methods.CreateFixture;
import com.semih.p07_methods.MatchSimulation;

@Service
public class MatchLeagueService implements IMatchLeagueService {

	@Autowired
	private IMatchLeagueRepository matchLeagueRepository;

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private MatchSimulation matchSimulation;

	@Autowired
	private ILeaderboardTeamRepository leaderboardTeamRepository;

	@Autowired
	private ILeagueRepository leagueRepository;

	@Autowired
	private IMyDataRepository myDataRepository;

	@Override
	public List<MatchLeagueDto> getMatchLeague() {
		List<MatchLeague> matchLeagues = matchLeagueRepository.findAll();
		List<MatchLeagueDto> matchLeagueDtos = new ArrayList<>();

		for (MatchLeague matchLeague : matchLeagues) {
			MatchLeagueDto matchLeagueDto = new MatchLeagueDto();
			BeanUtils.copyProperties(matchLeague, matchLeagueDto);
			matchLeagueDtos.add(matchLeagueDto);
		}
		return matchLeagueDtos;
	}

	@Override
	public void generateFixture(String leagueName, int season) {
		List<Team> teams = teamRepository.findAll().stream().filter(team -> team.getLeauge2().equals(leagueName))
				.collect(Collectors.toList());

		List<String> teamNames = teams.stream().map(Team::getName).collect(Collectors.toList());
		List<String> fixture = CreateFixture.generateFixture(teamNames);

		League league = leagueRepository.findByName(leagueName);
		league.setSeason(season);
		league.setWeek(0);
		leagueRepository.save(league);
		System.out.println(league.getCountry() + " için fixtür çekilioyor. -" + leagueName);

		for (String fixtureEntry : fixture) {
			String[] parts = fixtureEntry.split(",");
			int week = Integer.parseInt(parts[0]);
			String homeName = parts[1];
			String awayName = parts[2];

			MatchLeague match = new MatchLeague();
			match.setWeek(week);
			match.setHomeName(homeName);
			match.setAvayName(awayName);
			match.setSeason(season);
			match.setIsPlayed("false");
			match.setLeague(leagueName);
			match.setHomeGol(null);
			match.setAwayGol(null);

			matchLeagueRepository.save(match);
		}

		System.out.println(leagueName + " fixtürü çekildi");

	}

	@Override
	public List<MatchLeagueDto> getOneLeagueFixture(String league, int season) {
		List<MatchLeague> matchLeagues = matchLeagueRepository.findByLeagueAndSeason(league, season);
		List<MatchLeagueDto> matchLeagueDtos = new ArrayList<>();

		for (MatchLeague matchLeague : matchLeagues) {
			MatchLeagueDto matchLeagueDto = new MatchLeagueDto();
			BeanUtils.copyProperties(matchLeague, matchLeagueDto);
			matchLeagueDtos.add(matchLeagueDto);
		}
		return matchLeagueDtos;
	}

	@Override
	public void updateMatchResult(int season, int week, String league) {
		List<MatchLeague> matchLeagues = matchLeagueRepository.findBySeasonAndWeekAndLeagueAndIsPlayed(season, week,
				league, "false");

		List<MatchLeague> matchResults = new ArrayList<>();
		List<LeaderboardTeam> leaderboardTeams = new ArrayList<>();
		System.out.println();
		System.out.printf("%-7s %-10s %-27s %-15s %-15s %-13s %-13s %-13s %-13s %-20s %s%n", "SKOR", "StaEnd", "STRONG",
				"H-İHTİMAL", "A-İHTİMAL", "H-ŞUT", "A-ŞUT", "H-ORTA", "A-ORTA", "FauFriPen", "OfsRedYek");

		for (MatchLeague ml : matchLeagues) {
			MatchLeague simuleResult = matchSimulation.matchSimulation(ml);

			LeaderboardTeam leaderboardHome = leaderboardTeamRepository.findBySeasonAndTeamNameAndLeague(ml.getSeason(),
					ml.getHomeName(), ml.getLeague());
			LeaderboardTeam leaderboardAway = leaderboardTeamRepository.findBySeasonAndTeamNameAndLeague(ml.getSeason(),
					ml.getAvayName(), ml.getLeague());

			if (leaderboardHome == null) {
				leaderboardHome = new LeaderboardTeam();
				leaderboardHome.setLeague(ml.getLeague());
				leaderboardHome.setSeason(ml.getSeason());
				leaderboardHome.setTeamName(ml.getHomeName());
				leaderboardHome.setWinn(0);
				leaderboardHome.setLoss(0);
				leaderboardHome.setDraw(0);
				leaderboardHome.setGolScore(0);
				leaderboardHome.setGolCon(0);
				Team team = teamRepository.findByName(ml.getHomeName());
				leaderboardHome.setItibar(team.getItibar());
			}
			if (leaderboardAway == null) {
				leaderboardAway = new LeaderboardTeam();
				leaderboardAway.setLeague(ml.getLeague());
				leaderboardAway.setSeason(ml.getSeason());
				leaderboardAway.setTeamName(ml.getHomeName());
				leaderboardAway.setWinn(0);
				leaderboardAway.setLoss(0);
				leaderboardAway.setDraw(0);
				leaderboardAway.setGolScore(0);
				leaderboardAway.setGolCon(0);
				Team team = teamRepository.findByName(ml.getAvayName());
				leaderboardAway.setItibar(team.getItibar());
			}

			leaderboardHome.setLeague(ml.getLeague());
			leaderboardAway.setLeague(ml.getLeague());

			leaderboardHome.setSeason(ml.getSeason());
			leaderboardAway.setSeason(ml.getSeason());

			leaderboardHome.setTeamName(ml.getHomeName());
			leaderboardAway.setTeamName(ml.getAvayName());

			leaderboardHome.setWeek(ml.getWeek());
			leaderboardAway.setWeek(ml.getWeek());

			leaderboardHome.setGolScore(leaderboardHome.getGolScore() + ml.getHomeGol());
			leaderboardAway.setGolScore(leaderboardAway.getGolScore() + ml.getAwayGol());
			leaderboardHome.setGolCon(leaderboardHome.getGolCon() + ml.getAwayGol());
			leaderboardAway.setGolCon(leaderboardAway.getGolCon() + ml.getHomeGol());

			if (ml.getMacRes() == 1) {
				leaderboardHome.setWinn(leaderboardHome.getWinn() + 1);
				leaderboardAway.setLoss(leaderboardAway.getLoss() + 1);
			} else if (ml.getMacRes() == 2) {
				leaderboardAway.setWinn(leaderboardAway.getWinn() + 1);
				leaderboardHome.setLoss(leaderboardHome.getLoss() + 1);
			} else {
				leaderboardHome.setDraw(leaderboardHome.getDraw() + 1);
				leaderboardAway.setDraw(leaderboardAway.getDraw() + 1);
			}

			matchResults.add(simuleResult);
			leaderboardTeams.add(leaderboardHome);
			leaderboardTeams.add(leaderboardAway);
		}

		leaderboardTeamRepository.saveAll(leaderboardTeams);
		matchLeagueRepository.saveAll(matchResults);
		MyData myData = myDataRepository.findByName("week");
		myData.setDescription(Integer.toString(week));
		myDataRepository.save(myData);
	}

}
