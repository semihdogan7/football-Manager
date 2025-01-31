package com.semih.p02_entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardTeamDto {
	private Long id;
	private Integer season;
	private Integer week;
	private String league;
	private String teamName;
	private Long teamId;
	private Integer match;
	private Integer winn;
	private Integer loss;
	private Integer draw;
	private Integer golScore;
	private Integer golCon;
	private Integer puan;
	private Integer averaj;
	private Float siralamaPuani;
	private Integer itibar;
	
	
}
