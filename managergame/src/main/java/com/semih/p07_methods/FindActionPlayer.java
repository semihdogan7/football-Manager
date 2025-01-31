package com.semih.p07_methods;

import java.util.List;

import org.springframework.stereotype.Component;

import com.semih.p02_entity.Player;

@Component
public class FindActionPlayer {
	public Long golculer(List<Player> players) {
		String[] position = { "DCR", "DCL", "DR", "DL", "DM", "MC", "AMC", "AMR", "AML", "ST" };
		int[] ihtimal = { 2, 2, 4, 4, 6, 8, 15, 15, 15, 29 };
		int[] carpan = new int[10];
		for (Player player : players) {
			for (int i = 0; i < position.length; i++) {
				if (player.getRole().equals(position[i])) {
					if (i > 0) {
						carpan[i] = (int) (carpan[i - 1] + player.getStrong() * ihtimal[i]);
					} else {
						carpan[i] = (int) (player.getStrong() * ihtimal[i]);
					}
				}
			}
		}
		int random = (int) (Math.random() * carpan[9]);
		String mevki = random < carpan[0] ? position[0]
				: random < carpan[1] ? position[1]
						: random < carpan[2] ? position[2]
								: random < carpan[3] ? position[3]
										: random < carpan[4] ? position[4]
												: random < carpan[5] ? position[5]
														: random < carpan[6] ? position[6]
																: random < carpan[7] ? position[7]
																		: random < carpan[8] ? position[8]
																				: position[9];

		for (Player player : players) {
			if (player.getRole().equals(mevki)) {
				return player.getId();
			}
		}
		return null;
	}

	public Long asistciler(List<Player> players) {
		String[] position = { "DCR", "DCL", "DR", "DL", "DM", "MC", "AMC", "AMR", "AML", "ST" };
		int[] ihtimal = { 4, 4, 7, 7, 7, 12, 17, 17, 17, 8 };
		int[] carpan = new int[10];
		for (Player player : players) {
			for (int i = 0; i < position.length; i++) {
				if (player.getRole().equals(position[i])) {
					if (i > 0) {
						carpan[i] = (int) (carpan[i - 1] + player.getStrong() * ihtimal[i]);
					} else {
						carpan[i] = (int) (player.getStrong() * ihtimal[i]);
					}
				}
			}
		}
		int random = (int) (Math.random() * carpan[9]);
		String mevki = random < carpan[0] ? position[0]
				: random < carpan[1] ? position[1]
						: random < carpan[2] ? position[2]
								: random < carpan[3] ? position[3]
										: random < carpan[4] ? position[4]
												: random < carpan[5] ? position[5]
														: random < carpan[6] ? position[6]
																: random < carpan[7] ? position[7]
																		: random < carpan[8] ? position[8]
																				: position[9];

		for (Player player : players) {
			if (player.getRole().equals(mevki)) {
				return player.getId();
			}
		}
		return null;
	}

	public Long defansif(List<Player> players) {
		int r1 = (int) (Math.random() * 100 + 1);
		String position = r1 < 20 ? "DCL"
				: r1 < 40 ? "DCR"
						: r1 < 60 ? "DM"
								: r1 < 70 ? "DL"
										: r1 < 80 ? "DR"
												: r1 < 90 ? "MC"
														: r1 < 94 ? "AMC" : r1 < 97 ? "AML" : r1 < 99 ? "AMR" : "ST";

		for (Player player : players) {
			if (player.getRole().equals(position)) {
				return player.getId();
			}
		}
		return null;
	}

	public Long kanatlar(List<Player> players) {
		int r1 = (int) (Math.random() * 100 + 1);
		String position = r1 < 25 ? "AML"
				: r1 < 50 ? "AMR"
						: r1 < 67 ? "DR"
								: r1 < 84 ? "DL"
										: r1 < 90 ? "AMC"
												: r1 < 94 ? "MC"
														: r1 < 97 ? "DM" : r1 < 99 ? "ST" : r1 < 100 ? "DCR" : "DCL";

		for (Player player : players) {
			if (player.getRole().equals(position)) {
				return player.getId();
			}
		}
		return null;
	}

	public Long duranTop(List<Player> players) {
		int r1 = (int) (Math.random() * 100 + 1);
		String position = r1 < 25 ? "AMC"
				: r1 < 45 ? "AMR"
						: r1 < 65 ? "AML"
								: r1 < 85 ? "ST"
										: r1 < 90 ? "MC"
												: r1 < 94 ? "DM"
														: r1 < 97 ? "DR" : r1 < 99 ? "DL" : r1 < 100 ? "DCR" : "DCL";

		for (Player player : players) {
			if (player.getRole().equals(position)) {
				return player.getId();
			}
		}
		return null;
	}

}
