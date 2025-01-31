package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.Player;
import com.semih.p02_entity.Tactic;
import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.TeamDto;
import com.semih.p05_service.intr.ITeamService;
import com.semih.p06_repository.IPlayerRepository;
import com.semih.p06_repository.ITacticRepository;
import com.semih.p06_repository.ITeamRepository;
import com.semih.p07_methods.CalculateTeamStrong;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private IPlayerRepository playerRepository;

	@Autowired
	private ITacticRepository tacticRepository;

	CalculateTeamStrong calculateTeamStrong = new CalculateTeamStrong();

	@Override
	public List<TeamDto> getAllTeamService() {
		List<Team> teams = teamRepository.findAll();
		List<TeamDto> teamDtos = new ArrayList<>();

		for (Team team : teams) {
			TeamDto teamDto = new TeamDto();
			BeanUtils.copyProperties(team, teamDto);

			List<Player> players = playerRepository.findByTeam2(team.getName());
			teamDto.setDefStrong(calculateTeamStrong.calculateOveral(players, team.getName()).get(0));
			teamDto.setMidStrong(calculateTeamStrong.calculateOveral(players, team.getName()).get(1));
			teamDto.setForStrong(calculateTeamStrong.calculateOveral(players, team.getName()).get(2));
			teamDtos.add(teamDto);
		}
		teamDtos.sort((a, b) -> Float.compare(b.getTotalValue(), a.getTotalValue()));
		return teamDtos;
	}

	@Override
	public List<TeamDto> getOneLeaguesTeam(String league) {
		List<Team> teams = teamRepository.findByLeauge2(league);
		List<TeamDto> teamDtos = new ArrayList<>();

		for (Team team : teams) {
			TeamDto teamDto = new TeamDto();
			BeanUtils.copyProperties(team, teamDto);
			teamDtos.add(teamDto);
		}
		teamDtos.sort((a,b)->Float.compare(b.getTotalValue(), a.getTotalValue()));
		
		return teamDtos;
	}

	@Override
	public Page<TeamDto> getTeamPagable(int page, int size, String sirala) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sirala)));
		Page<Team> teamPage = teamRepository.findAll(pageable);

		return teamPage.map(team -> {
			TeamDto teamDto = new TeamDto();
			BeanUtils.copyProperties(team, teamDto);

//			List<Player> players = playerRepository.findByTeam2(team.getName());
//			teamDto.setDefStrong(calculateTeamStrong.calculateOveral(players).get(0));
//			teamDto.setMidStrong(calculateTeamStrong.calculateOveral(players).get(1));
//			teamDto.setForStrong(calculateTeamStrong.calculateOveral(players).get(2));
//			teamDto.setAvrStrong(calculateTeamStrong.calculateOveral(players).get(3));

			return teamDto;
		});
	}

	@Override
	public TeamDto getOneTeamWithPlayers(String name) {

		return null;
	}

	@Override
	public Team updateTacticPlayersRole(String teamName) {

		Team team = teamRepository.findByName(teamName);

		List<Player> players = playerRepository.findByTeam2(teamName);
		List<Tactic> tactics = tacticRepository.findByName(team.getTactic());

		players.sort((a, b) -> Integer.compare(b.getStrong(), a.getStrong()));
		for (Player player : players) {
			player.setRole("KD");
			player.setSquad(3);
		}

		int kadroSay = 0;
		int yedekSay = 0;
		int kdSay = 0;

		for (Tactic tactic : tactics) {
			for (Player player : players) {
				if (player.getRole().equals("KD") && tactic.getPosition().equals(player.getPosition())) {
					kadroSay++;
					player.setRole(tactic.getPosuniqe());
					player.setSquad(kadroSay);
					break;
				}
			}
		}

		for (Player player : players) {
			if (player.getRole().equals("KD") && yedekSay < 11) {
				yedekSay++;
				player.setRole("Y" + yedekSay);
				player.setSquad(11 + yedekSay);
			}
		}

		for (Player player : players) {
			if (player.getRole().equals("KD")) {
				kdSay++;
				player.setRole("KD" + kdSay);
				player.setSquad(22 + kdSay);
			}
		}

		playerRepository.saveAll(players);

		return team;
	}

}
