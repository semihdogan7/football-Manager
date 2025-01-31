package com.semih.p07_methods;

import org.springframework.stereotype.Component;

import com.semih.p02_entity.PlayerPositions;

@Component
public class CreatePlayerPositions {
	
	String[] pos = { "GK", "DC", "DL", "DR", "DM", "MC", "ML", "MR", "AMC", "AML", "AMR", "ST" };

	int[][] ekPos = {
			{ 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 4, 4, 3, 2, 0, 0, 0, 0, 0, 0 }, { 0, 3, 10, 3, 0, 0, 5, 2, 0, 0, 0, 0 },
			{ 0, 3, 3, 10, 0, 0, 2, 5, 0, 0, 0, 0 }, { 0, 6, 1, 1, 10, 5, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 6, 10, 1, 1, 4, 0, 0, 0 },
			{ 0, 0, 5, 0, 0, 0, 10, 3, 0, 5, 0, 0 }, { 0, 0, 0, 5, 0, 0, 3, 10, 0, 0, 5, 0 }, { 0, 0, 0, 0, 3, 5, 0, 0, 10, 1, 1, 3 },
			{ 0, 0, 0, 0, 0, 0, 3, 0, 3, 10, 3, 4 }, { 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 10, 4 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5, 4, 4, 10 } };

	public PlayerPositions setPositions(String position){
		PlayerPositions playerPositions = new PlayerPositions();
		
		for (int i = 0; i < 12; i++) {
			if (position.equals(pos[i])) {
				for (int j = 0; j < 12; j++) {
					int random = (int) (Math.random() * 10);
					if (ekPos[i][j] == 10) {
						if (j == 0) {
							playerPositions.setGk(1.0f);
						} else if (j == 1) {
							playerPositions.setDc(1.0f);
						} else if (j == 2) {
							playerPositions.setDl(1.0f);
						} else if (j == 3) {
							playerPositions.setDr(1.0f);
						} else if (j == 4) {
							playerPositions.setDm(1.0f);
						} else if (j == 5) {
							playerPositions.setMc(1.0f);
						} else if (j == 6) {
							playerPositions.setMl(1.0f);
						} else if (j == 7) {
							playerPositions.setMr(1.0f);
						} else if (j == 8) {
							playerPositions.setAmc(1.0f);
						} else if (j == 9) {
							playerPositions.setAml(1.0f);
						} else if (j == 10) {
							playerPositions.setAmr(1.0f);
						} else if (j == 11) {
							playerPositions.setSt(1.0f);
						}
					} else if (ekPos[i][j] > 0 && random < ekPos[i][j]) {
						if (j == 0) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setGk(1.0f);
							} else {
								playerPositions.setGk(0.9f);
							}
						} else if (j == 1) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setDc(1.0f);
							} else {
								playerPositions.setDc(0.9f);
							}
						} else if (j == 2) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setDl(1.0f);
							} else {
								playerPositions.setDl(0.9f);
							}
						} else if (j == 3) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setDr(1.0f);
							} else {
								playerPositions.setDr(0.9f);
							}
						} else if (j == 4) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setDm(1.0f);
							} else {
								playerPositions.setDm(0.9f);
							}
						} else if (j == 5) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setMc(1.0f);
							} else {
								playerPositions.setMc(0.9f);
							}
						} else if (j == 6) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setMl(1.0f);
							} else {
								playerPositions.setMl(0.9f);
							}
						} else if (j == 7) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setMr(1.0f);
							} else {
								playerPositions.setMr(0.9f);
							}
						} else if (j == 8) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setAmc(1.0f);
							} else {
								playerPositions.setAmc(0.9f);
							}
						} else if (j == 9) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setAml(1.0f);
							} else {
								playerPositions.setAml(0.9f);
							}
						} else if (j == 10) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setAmr(1.0f);
							} else {
								playerPositions.setAmr(0.9f);
							}
						} else if (j == 11) {
							int random2 = (int) (Math.random() * 10);
							if (random2 < ekPos[i][j]) {
								playerPositions.setSt(1.0f);
							} else {
								playerPositions.setSt(0.9f);
							}
						}
					} else {
						if (j == 0) {
							playerPositions.setGk(0.5f);
						} else if (j == 1) {
							playerPositions.setDc(0.5f);
						} else if (j == 2) {
							playerPositions.setDl(0.5f);
						} else if (j == 3) {
							playerPositions.setDr(0.5f);
						} else if (j == 4) {
							playerPositions.setDm(0.5f);
						} else if (j == 5) {
							playerPositions.setMc(0.5f);
						} else if (j == 6) {
							playerPositions.setMl(0.5f);
						} else if (j == 7) {
							playerPositions.setMr(0.5f);
						} else if (j == 8) {
							playerPositions.setAmc(0.5f);
						} else if (j == 9) {
							playerPositions.setAml(0.5f);
						} else if (j == 10) {
							playerPositions.setAmr(0.5f);
						} else if (j == 11) {
							playerPositions.setSt(0.5f);
						}
					}
				}
				break;
			}
		}
		
		return playerPositions;
	}
}
