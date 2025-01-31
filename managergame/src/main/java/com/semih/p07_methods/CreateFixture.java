package com.semih.p07_methods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CreateFixture {

	public static List<String> generateFixture(List<String> teams) {
		List<String> fixtures = new ArrayList<>();

		if (teams.size() % 2 != 0) {
			teams.add("Bay");
		}

		int totalRounds = teams.size() - 1;
		int matchesPerRound = teams.size() / 2;

		for (int r = 0; r < totalRounds; r++) {
			for (int m = 0; m < matchesPerRound; m++) {
				String home = teams.get(m);
				String away = teams.get(teams.size() - 1 - m);
				fixtures.add((r + 1) + "," + home + "," + away);
			}
			teams.add(1, teams.remove(teams.size() - 1));
		}

		for (int i = 0; i < fixtures.size(); i++) {
			if (fixtures.get(i).split(",")[1].equals(teams.get(0)) && Integer.parseInt(fixtures.get(i).split(",")[0]) % 2 == 1) {
				fixtures.set(i, fixtures.get(i).split(",")[0] + "," + fixtures.get(i).split(",")[2] + "," + fixtures.get(i).split(",")[1]);
			}
		}
		int size = fixtures.size();
		for (int i = 0; i < size; i++) {
			fixtures.add(Integer.parseInt(fixtures.get(i).split(",")[0]) + 25 + "," + fixtures.get(i).split(",")[2] + "," + fixtures.get(i).split(",")[1]);
		}

		return fixtures;
	}

}
