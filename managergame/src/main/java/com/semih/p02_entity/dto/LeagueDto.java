package com.semih.p02_entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {

	private Long id;
	private String name;
	private String country;
	private String continent;
	private String level;
	private String group;
	private int value;
	private int teamCount;
	private List<TeamDto> teams;

}
