package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardPlayerDto {

	private Long id;
	private Integer season;
	private String league;
	private Long playerId;
	private String fullName;
	private Integer goal;
	private Integer asist;
	private Integer onbir;
	private Integer score;
	private Long teamId;
	private String teamName;
	private String mevki;
	private Integer strong;
}
