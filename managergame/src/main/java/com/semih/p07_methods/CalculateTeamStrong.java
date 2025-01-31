package com.semih.p07_methods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.semih.p02_entity.Player;
import com.semih.p02_entity.Tactic;
import com.semih.p06_repository.ITacticRepository;

@Component
public class CalculateTeamStrong {

	@Autowired
	private ITacticRepository tacticRepository;

	public List<Float> calculateOveral(List<Player> players, String tactic) {
		int totalDef = 0;
		int totalMid = 0;
		int totalFor = 0;
		int defSay = 0;
		int midSay = 0;
		int forSay = 0;
		List<Tactic> tactics = tacticRepository.findByName(tactic);

		for (Player player : players) {
			if (player.getSquad() <= 11) {
				for (Tactic tac : tactics) {
					if (player.getPosition().equals(tac.getPosition())) {
						if (tac.getBolge().equals("Defans")) {
							totalDef = totalDef + player.getStrong();
							defSay++;
						} else if (tac.getBolge().equals("Ortasaha")) {
							totalMid = totalMid + player.getStrong();
							midSay++;
						} else if (tac.getBolge().equals("Forvet")) {
							totalFor = totalFor + player.getStrong();
							forSay++;
						}
					}
				}
			}
		}
		float defanceTeam = totalDef / defSay;
		float middTeam = totalMid / midSay;
		float forwardTeam = totalFor / forSay;

		List<Float> strongsteam = new ArrayList<>();

		strongsteam.add(defanceTeam);
		strongsteam.add(middTeam);
		strongsteam.add(forwardTeam);
		strongsteam.add((defanceTeam + middTeam + forwardTeam) / 3);

		return strongsteam;
	}
}