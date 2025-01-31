package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.Country;
import com.semih.p02_entity.LeaderboardPlayer;
import com.semih.p02_entity.Player;
import com.semih.p02_entity.PlayerAttributes;
import com.semih.p02_entity.PlayerPositions;
import com.semih.p02_entity.Team;
import com.semih.p02_entity.dto.PlayerDto;
import com.semih.p05_service.intr.IPlayerService;
import com.semih.p06_repository.ICountryRepository;
import com.semih.p06_repository.ILeaderboardPlayerRepository;
import com.semih.p06_repository.IMyDataRepository;
import com.semih.p06_repository.IPlayerAttributesRepository;
import com.semih.p06_repository.IPlayerNameRepository;
import com.semih.p06_repository.IPlayerPositionsRepository;
import com.semih.p06_repository.IPlayerRepository;
import com.semih.p06_repository.ITeamRepository;
import com.semih.p07_methods.CalculateTeamStrong;
import com.semih.p07_methods.CreatePlayerAttributes;
import com.semih.p07_methods.CreatePlayerPositions;

@Service
public class PlayerService implements IPlayerService {

	@Autowired
	private IPlayerRepository playerRepository;

	@Autowired
	private CreatePlayerAttributes createPlayer;

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private IMyDataRepository myDataRepository;

	@Autowired
	private IPlayerPositionsRepository playerPositionsRepository;

	@Autowired
	private IPlayerAttributesRepository playerAttributesRepository;

	@Autowired
	private IPlayerNameRepository playerNameRepository;

	@Autowired
	private ICountryRepository countryRepository;

	@Autowired
	private CreatePlayerPositions playerYanPozisyonlar;

	@Autowired
	private ILeaderboardPlayerRepository leaderboardPlayerRepository;

	@Override
	public List<PlayerDto> getAllPlayerService() {
		List<Player> players = playerRepository.findAll();
		List<PlayerDto> playerDtos = new ArrayList<>();

		for (Player player : players) {
			PlayerDto playerDto = new PlayerDto();
			BeanUtils.copyProperties(player, playerDto);
			playerDto.setTeam_id(player.getTeam().getId().toString());
			playerDtos.add(playerDto);
			playerDtos.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));
		}
		return playerDtos;
	}

	@Override
	public void savePlayer(String position, String teamName) {

		Team team = teamRepository.findByName(teamName);
		Player player = new Player();

		player.setBirth((Integer.parseInt(myDataRepository.findByName("season").getDescription()) - (int) (Math.random() * 26) - 14));
		Integer age = Integer.parseInt(myDataRepository.findByName("season").getDescription()) - player.getBirth();

		int randName = (int) (Math.random() * 2000) + 1;

		Country country = countryRepository.findCountryByValue(randName);

		String vatandaslik = country.getName();

		if (country.getName().equals("kendi")) {
			vatandaslik = team.getCountry();
		}

		player.setCountry(vatandaslik);

		Country country2 = countryRepository.findByName(vatandaslik);

		int sayName = (int) (Math.random() * country2.getNamSay()) + 1;
		int saySurName = (int) (Math.random() * country2.getSurSay()) + 1;

		try {
			player.setName(playerNameRepository.findByCountry(vatandaslik + "-Name-" + sayName).get(0).getNames());
			player.setSurname(playerNameRepository.findByCountry(vatandaslik + "-SurName-" + saySurName).get(0).getNames());
		} catch (Exception e) {
			// TODO: handle exception
		}
		player.setPosition(position);

		player.setTeam2(teamName);
		player.setTeam(team);

		PlayerPositions playerPozisyon = playerYanPozisyonlar.setPositions(position);
		playerPozisyon.setPlayer(player);

		PlayerAttributes playerAttributes = createPlayer.setAttributes(position, team.getAltyapi(), age, "one");
		playerAttributes.setPlayer(player);

		player.setStrong(playerAttributes.getStrong());

		float ageValue = (float) ((360 * Math.pow(1.1, -0.15 * (age - 14)) - 250) * 0.015);
		float strValue = (float) (0.01 * Math.pow(2.718, 0.102 * player.getStrong()));
		player.setValue(ageValue * strValue);

		player.setAyak("Sol Ayak");
		player.setPotansiyel(0.0f);
		player.setBoy(185);
		player.setAgirlik(78);

		player.setSquad(3);
		player.setRole("KD");

		player.setPlayerPositions(playerPozisyon);
		player.setPlayerAttributes(playerAttributes);

		playerRepository.save(player);
		playerPositionsRepository.save(playerPozisyon);
		playerAttributesRepository.save(playerAttributes);

	}

	@Override
	public Page<PlayerDto> getPlayerPage(int page, int size) {
		org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size,
				Sort.by(Sort.Order.desc("value")));

		Page<Player> playerPage = playerRepository.findAll(pageable);
		return playerPage.map(player -> {
			PlayerDto playerDto = new PlayerDto();
			playerDto.setTeam_id(player.getTeam().getId().toString());
			BeanUtils.copyProperties(player, playerDto);
			return playerDto;
		});
	}

	@Override
	public void updatePosition(String team, String position) {
		List<Player> players = playerRepository.findByTeam2AndPosition(team, position);
		if (players == null || players.isEmpty()) {
			return; // Eğer liste boşsa işlemi sonlandır
		}
		players.sort((a, b) -> Integer.compare(b.getStrong(), a.getStrong()));
		int i = 1;
		for (Player player : players) {
			player.setSquad(i);
			i++;
		}
		playerRepository.saveAll(players);
	}

	@Override
	public void updateAllPosition() {
		List<Player> players = playerRepository.findAll();

		Map<String, Map<String, List<Player>>> groupedPlayers = players.stream()
				.collect(Collectors.groupingBy(Player::getTeam2, Collectors.groupingBy(Player::getPosition)));

		String[] position = { "GK", "DC", "DR", "DL", "DM", "MC", "MR", "ML", "AMC", "AML", "AMR", "ST" };

		for (Map<String, List<Player>> positionMap : groupedPlayers.values()) {
			for (List<Player> positionPlayers : positionMap.values()) {
				positionPlayers.sort((a, b) -> Integer.compare(b.getStrong(), a.getStrong()));
				int i = 1;
				for (Player player : positionPlayers) {
					player.setSquad(i);
					for (int j = 0; j < 10; j++) {
						if (i == 1 && player.getPosition().equals(position[j])) {
							if (player.getPosition().equals("DC")) {
								player.setRole(position[j] + "R");
								break;
							} else {
								player.setRole(position[j]);
							}
						} else if (i == 2 && player.getPosition().equals("DC")) {

						}
					}

					i = i < 3 ? i + 1 : i;
				}
				playerRepository.saveAll(positionPlayers);
			}
		}
	}

	CalculateTeamStrong calculateTeamStrong = new CalculateTeamStrong();

	@Override
	public List<PlayerDto> getPlayerThisTeam(String team2) {
		List<Player> players = playerRepository.findByTeam2(team2);
		List<PlayerDto> playerDtos = new ArrayList<>();

		for (Player player : players) {
			PlayerDto playerDto = new PlayerDto();
			BeanUtils.copyProperties(player, playerDto);
			System.out.println(player.getId());
			
			LeaderboardPlayer leaderboardPlayer = leaderboardPlayerRepository.findByPlayerIdAndSeason(player.getId(),1);
			try {
				playerDto.setSezonMac(leaderboardPlayer.getOnbir());
				playerDto.setSezonGol(leaderboardPlayer.getGoal());
				playerDto.setSezonAsist(leaderboardPlayer.getAsist());
			} catch (Exception e) {
				playerDto.setSezonMac(0);
				playerDto.setSezonGol(0);
				playerDto.setSezonAsist(0);
			}
			
			playerDtos.add(playerDto);

		}
		playerDtos.sort((a, b) -> Integer.compare(a.getSquad(), b.getSquad()));
		return playerDtos;
	}

	@Override
	public Optional<Player> getPlayer(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		return player;
	}

}
