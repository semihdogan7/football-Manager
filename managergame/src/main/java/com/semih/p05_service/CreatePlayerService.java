package com.semih.p05_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semih.p02_entity.Country;
import com.semih.p02_entity.Player;
import com.semih.p02_entity.PlayerAttributes;
import com.semih.p02_entity.PlayerPositions;
import com.semih.p02_entity.Team;
import com.semih.p05_service.intr.ICreatePlayerService;
import com.semih.p06_repository.ICountryRepository;
import com.semih.p06_repository.IMyDataRepository;
import com.semih.p06_repository.IPlayerAttributesRepository;
import com.semih.p06_repository.IPlayerNameRepository;
import com.semih.p06_repository.IPlayerPositionsRepository;
import com.semih.p06_repository.IPlayerRepository;
import com.semih.p06_repository.ITeamRepository;
import com.semih.p07_methods.CreatePlayerAttributes;
import com.semih.p07_methods.CreatePlayerPositions;

@Service
public class CreatePlayerService implements ICreatePlayerService {

	@Autowired
	private ITeamRepository teamRepository;

	@Autowired
	private IPlayerRepository playerRepository;

	@Autowired
	private IPlayerAttributesRepository playerAttributesRepository;

	@Autowired
	private IPlayerPositionsRepository playerPositionsRepository;

	@Autowired
	private CreatePlayerAttributes createPlayer;

	@Autowired
	private ICountryRepository countryRepository;

	@Autowired
	private IPlayerNameRepository playerNameRepository;

	@Autowired
	private IMyDataRepository myDataRepository;

	@Autowired
	private CreatePlayerPositions playerYanPozisyonlar;

	String[] defPosList = { "GK", "GK", "GK",
			"DC", "DC", "DC", "DC", "DC", "DC", "DR", "DR", "DR", "DL", "DL", "DL",
			"DM", "DM", "DM", "MC", "MC", "MC", "ML", "ML", "ML", "MR", "MR", "MR",
			"AMC", "AMC", "AMC", "AML", "AML", "AML", "AMR", "AMR", "AMR", "ST", "ST", "ST" };

	@Override
	public void fillAllTeam() {
		List<Team> teams = teamRepository.findAll();
		List<Player> savePlayers = new ArrayList<>();
		List<PlayerAttributes> saveAttributes = new ArrayList<>();
		List<PlayerPositions> savePositions = new ArrayList<>();

		for (Team team : teams) {
			for (int i = 0; i < defPosList.length; i++) {
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
					
				}
				
				player.setPosition(defPosList[i]);

				player.setTeam2(team.getName());
				player.setTeam(team);

				PlayerPositions playerPozisyon = playerYanPozisyonlar.setPositions(defPosList[i]);
				playerPozisyon.setPlayer(player);

				PlayerAttributes playerAttributes = createPlayer.setAttributes(defPosList[i], team.getItibar(), age, "all");
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

				savePlayers.add(player);
				saveAttributes.add(playerAttributes);
				savePositions.add(playerPozisyon);
				
				System.out.printf("%-25s %-25s %-25s %-25s %-10s %s%n",
						player.getName(),
						player.getSurname(),
						player.getTeam2(),
						player.getCountry(),
						player.getStrong(),
						sayName+" - "+saySurName
						);

			}
		}

		playerRepository.saveAll(savePlayers);
		playerAttributesRepository.saveAll(saveAttributes);
		playerPositionsRepository.saveAll(savePositions);

	}

}
